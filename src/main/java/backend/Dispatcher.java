package backend;

import java.util.Timer;

/**
 * Big one. This class will be responsible for making calls to the Weka command system
 * and returning something useful and visible to the UI.
 */
public class Dispatcher {
    int minutes; //number of minutes for the run
    SearchAlg search_type;
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
    }

    /**
     * Start the dispatcher. Your foes tremble!
     */
    public void launch(){
        //calculates the timer
        //starts the dispatch loop
        //calls each algorithm (iterative? threads? hmm.)
        //exit when time is up
    }

    /**
     * Set the time limit for the run. Actual counter is created when dispatcher gets to work.
     * @param mins
     */
    public void setTimeLimit(int mins){
        minutes = mins;
    }


}
