package backend;

import utils.Logging.MiniMLLogger;

import weka.core.Instances;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Big one. This class will be responsible for making calls to the Weka command system
 * and returning something useful and visible to the UI.
 */
public class Dispatcher {
    int maxThreads;
    int minutesToRun;
    Instances data;
    SearchAlgorithmInterface searchType;
    WekaTaskManager mgr;
    WekaInvoker taskList;
    //TODO tasks are hardcoded for now; fix it in future iterations so user can specify which to make
    LR_Task lr;
    NN_Task nn;
    //TODO ParameterIFaces are hardcoded until we can generalize parameter selection
    ParameterIFace param_iface_nn;
    ParameterIFace param_iface_lr;
    ParameterIFace param_iface_dt;

    /**
     * Construct the major parts of the backend so that the dispatcher can use them.
     */
    public Dispatcher(int maxThreads){
        this.maxThreads = maxThreads;
        mgr = new WekaTaskManager(this.maxThreads);
        taskList = new WekaInvoker();
        lr = new LR_Task(mgr);
        nn = new NN_Task(mgr);
        searchType = new randomSearch(this);
        param_iface_lr = new LinearRegressionParameters();
        param_iface_nn = new NeuralNetworkParameters();
        param_iface_dt = new DecisionTreeParameters();
    }

    /**
     * Launches the dispatcher which starts generating parameter sets for models, dispatching models,
     * and more all while counting down the time it has to run.
     *
     * A lot happens here and a flurry of changes are imminent. TODO
     */
    public void launch(){
        mgr.setData(data);
        long endTime = this.calculateTimer();
        //starts the dispatch loop
        while(System.currentTimeMillis() < endTime) {
            //calls each algorithm (iterative? threads? hmm.)
            //TODO: start with iterative, work up a threaded WekaTaskManager
            //LR
            ArrayList<WrappedParamFinal> LR_params = searchType.getNextParamSet(param_iface_lr);
            String[] lr_array = unpackWrappedParams(LR_params,"lr");
            //mgr.manage_LR(lr_array);
            mgr.addModel(lr_array);
            //NN
            ArrayList<WrappedParamFinal> NN_params = searchType.getNextParamSet(param_iface_nn);
            String[] nn_array = unpackWrappedParams(NN_params, "nn");
            mgr.addModel(nn_array);
            //mgr.manage_NN(nn_array);
            //TODO: SVM
            //TODO: decision tree
            ArrayList<WrappedParamFinal> DT_params = searchType.getNextParamSet(param_iface_dt);
            String[] dt_array = unpackWrappedParams(DT_params, "dt");
            mgr.addModel(dt_array);
            //sleep; then check if we need new threads
            try {
                mgr.runModel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                log(ex.toString(), "error");
            }
        }
        log("Number of completed models: " + String.valueOf(this.mgr.done_models.size()), "debug");
    }

    /**
     * Set the time limit for the run. Actual counter is created when dispatcher gets to work.
     * @param minutes
     */
    public void setTimeLimit(int minutes){
        this.minutesToRun = minutes;
    }

    /**
     * Set the data set that the dispatcher will be using.
     * @param d
     */
    public void setData(Instances d){
        this.data = d;
    }

    /**
     * Unpack an arraylist of wrapped params into a string array for passing to model.
     * There is probably a better place for this method, but it escapes me at the moment.
     *
     * @param packed : an  ArrayList<WrappedParamFinal> with all parameters for one run.
     * @return a String[] of flags and their values.
     */
    public String[] unpackWrappedParams(ArrayList<WrappedParamFinal> packed, String model){
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(model);
        for (WrappedParamFinal p : packed) { //unpack wrapped params into
            parameters.add(p.getFlag());
            parameters.add(p.getValue());
        }
        String[] parameterArray = new String[parameters.size()];
        parameters.toArray(parameterArray);
        log("Selected Parameters: " + Arrays.toString(parameterArray), "debug");
        return(parameterArray);
    }

    /**
     * Logs a string using the util.log
     * @param str some message we want to log
     * @param type the type of message (debug, info, error, etc)
     */
    protected void log(String str, String type) {
        switch(type) {
            case "debug": {
                MiniMLLogger.INSTANCE.debug("In dispatcher: " + str);
                break;
            }
            case "exception": {
                MiniMLLogger.INSTANCE.error("In dispatcher: " + str);
                break;
            }
            default: {
                MiniMLLogger.INSTANCE.debug("Dispatcher encountered an unhandled message type: " + type);
            }
        }
    }

    /**
     * Calculate the end time for the dispatcher from minutesToRun and current time.
     * @return the end time (a long)
     */
    private long calculateTimer(){
        long endTime = System.currentTimeMillis() + (this.minutesToRun * 60 * 1000);
        return(endTime);
    }

}
