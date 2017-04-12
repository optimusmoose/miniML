package backend;

import weka.core.Instances;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Big one. This class will be responsible for making calls to the Weka command system
 * and returning something useful and visible to the UI.
 */
public class Dispatcher {
    int minutes; //number of minutes for the run
    Instances data;
    SearchAlg searchType;
    WekaTaskManager mgr;
    WekaInvoker taskList;
    //TODO tasks are hardcoded for now; fix it in future iterations so user can specify which to make
    LR_Task lr;
    NN_Task nn;
    //TODO see TODO above
    ParameterIFace param_iface_nn;
    ParameterIFace param_iface_lr;

    /**
     * Construct the major parts of the backend so that the dispatcher can use them.
     */
    public Dispatcher(){
        mgr = new WekaTaskManager();
        taskList = new WekaInvoker();
        lr = new LR_Task(mgr);
        nn = new NN_Task(mgr);
        searchType = new randomSearch(this);
        param_iface_lr = new ParameterIFace_LR();
        param_iface_nn = new ParameterIFace_NN();
    }

    /**
     * Start the dispatcher. Your foes tremble!
     */
    public void launch(){
        mgr.setData(data);
        //calculates the timer
        long endTime = System.currentTimeMillis() + (this.minutes * 60 * 1000);
        //starts the dispatch loop
        while(System.currentTimeMillis() < endTime) {
            //calls each algorithm (iterative? threads? hmm.)
            //TODO: start with iterative, work up a threaded WTM
            //
            //LR
            ArrayList<WrappedParamFinal> LR_params = searchType.getNextParamSet(param_iface_lr);
            ArrayList<String> lr_pars = new ArrayList<String>();
            for (WrappedParamFinal p : LR_params) { //unpack wrapped params into
                lr_pars.add(p.getFlag());
                lr_pars.add(p.getValue());
            }
            System.out.println("1");
            String[] lr_array = new String[lr_pars.size()];
            lr_pars.toArray(lr_array);
            //lr.setParams(lr_array);
            mgr.manage_LR(lr_array);
            System.out.println("2");
            //taskList.addTask(lr);
            System.out.println("3");
            //taskList.doTask();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Set the time limit for the run. Actual counter is created when dispatcher gets to work.
     * @param mins
     */
    public void setTimeLimit(int mins){
        minutes = mins;
    }

    public void setData(Instances d){
        data = d;
    }


}
