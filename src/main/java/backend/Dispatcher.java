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
    long endTime;
    Instances data;
    SearchAlgorithmInterface searchType;
    WekaTaskManager mgr;
    WekaInvoker taskList;
    DatasetBuilder datasetBuilder;
    //TODO ParameterIFaces are hardcoded until we can generalize parameter selection
    ParameterIFace neuralNetworkParameters;
    ParameterIFace linearRegressionParameters;
    ParameterIFace decisionTreeParameters;
    ParameterIFace smoParameters;
    //Parameter values stored here
    boolean useLR;
    boolean useNN;
    boolean useDT;
    boolean useSMO;

    /**
     * Construct the major parts of the backend so that the dispatcher can use them.
     */
    public Dispatcher(int maxThreads,
                      ParameterIFace neuralNetworkParameters,
                      ParameterIFace linearRegressionParameters,
                      ParameterIFace decisionTreeParameters,
                      ParameterIFace smoParameters,
                      SearchAlgorithmInterface searchType){
        this.neuralNetworkParameters = neuralNetworkParameters;
        this.linearRegressionParameters = linearRegressionParameters;
        this.decisionTreeParameters = decisionTreeParameters;
        this.smoParameters = smoParameters;
        this.searchType = searchType;
        this.maxThreads = maxThreads;
        this.datasetBuilder = new DatasetBuilder();
        mgr = new WekaTaskManager(this.maxThreads);
        taskList = new WekaInvoker();
        this.data = datasetBuilder.getUserSpecifiedDataset();
    }

    /**
     * Launches the dispatcher which starts generating parameter sets for models, dispatching models,
     * and more all while counting down the time it has to run.
     *
     * The WekaTaskManager is loaded up with models to evaluate. It empties this queue at the end
     * of every iteration of this main loop, pouring these models into its thread pool (which, in
     * turn, has a blocking queue which is used to populate the threads.
     *
     * The finished models are saved by the WekaTaskManager-- TODO do we cull them?
     */
    public void launch(){
        mgr.setData(data);
        long endTime = this.calculateTimer();
        while(System.currentTimeMillis() < endTime) {
            this.giveWorkToManager(); //make new jobs in mgr
            try { //give mgr jobs to threadpool
                mgr.runModel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try { //sleep; then check if we need new threads
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                MiniMLLogger.INSTANCE.exception(ex);
            }
        }
        MiniMLLogger.INSTANCE.info("Number of completed models: " + String.valueOf(this.mgr.finishedModels.size()));
        Model bestModel = mgr.findBestModel();
        MiniMLLogger.INSTANCE.info("Best model found: " +
                bestModel.eval.toSummaryString("\nResults\n======\n", false));
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
        MiniMLLogger.INSTANCE.info("Selected Parameters: " + Arrays.toString(parameterArray));
        return(parameterArray);
    }

    /**
     * Calculate the end time for the dispatcher from minutesToRun and current time.
     * @return the end time (a long)
     */
    private long calculateTimer(){
        this.endTime = System.currentTimeMillis() + (this.minutesToRun * 60 * 1000);
        return(this.endTime);
    }

    /**
     * Tell the Dispatcher which algorithms it should use.
     */
    public void setAlgorithmUsage(String useLinearRegression,
                                  String useNeuralNet,
                                  String useDecisionTree,
                                  String useSMO){
        this.useLR = Boolean.parseBoolean(useLinearRegression);
        this.useNN = Boolean.parseBoolean(useNeuralNet);
        this.useDT = Boolean.parseBoolean(useDecisionTree);
        this.useSMO = Boolean.parseBoolean(useSMO);
    }

    /**
     * Main loop for Dispatcher when choosing algorithms.
     */
    protected void giveWorkToManager(){
        if(this.useLR) {
            ArrayList<WrappedParamFinal> LR_params = searchType.getNextParamSet(linearRegressionParameters);
            String[] lr_array = unpackWrappedParams(LR_params, "lr");
            mgr.addModel(lr_array);
        }
        if(this.useNN) {
            ArrayList<WrappedParamFinal> NN_params = searchType.getNextParamSet(neuralNetworkParameters);
            String[] nn_array = unpackWrappedParams(NN_params, "nn");
            mgr.addModel(nn_array);
        }
        if(this.useDT) {
            ArrayList<WrappedParamFinal> DT_params = searchType.getNextParamSet(decisionTreeParameters);
            String[] dt_array = unpackWrappedParams(DT_params, "dt");
            mgr.addModel(dt_array);
        }
        if(this.useSMO) {
            ArrayList<WrappedParamFinal> SMO_params = searchType.getNextParamSet(smoParameters);
            String[] smo_array = unpackWrappedParams(SMO_params, "smo");
            mgr.addModel(smo_array);
        }
    }

}
