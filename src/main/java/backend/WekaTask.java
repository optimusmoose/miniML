package backend;

/**
 * Interface to loosely couple Weka functions to the WekaTaskManager.
 * All it wants is an execute method (so elements in WTK are uniformly accessible).
 */
public interface WekaTask {
    /**
     * Execute the Task. Should tell the Model to perform its calculations and
     * return its analysis of the data as an interactive object.
     */
    void execute ( );

    /**
     * Set the parameters for this particular task.
     * @param pars a String[] of parameters for this task to use.
     */
    void setParams(String[] pars);
}

/**
 * LinearRegression task; the WekaTaskManager should be able to simply execute it.
 */
class LR_Task implements WekaTask {
    private WekaTaskManager manager;
    private String[] params;
    /**
     * Set up the model and ready for execution.
     * @param mgr
     */
    public LR_Task (WekaTaskManager mgr) {
        manager = mgr;
    }

    /**
     * See abstract method.
     */
    public void execute() {
        manager.manage_LR(params);
    }

    /**
     * See abstract method.
     */
    public void setParams(String[] pars){
        params = pars;
    }
}

/**
 * NeuralNetwork task; the WekaTaskManager should be able to simply execute it.
 */
class NN_Task implements WekaTask {
    private WekaTaskManager manager;
    private String[] params;
    /**
     * Set up the model and ready for execution.
     * @param mgr
     */
    public NN_Task (WekaTaskManager mgr) {
        manager = mgr;
    }
    /**
     * See abstract method.
     */
    public void execute() {
        manager.manage_NN(params);
    }

    /**
     * See abstract method.
     */
    public void setParams(String[] pars){
        params = pars;
    }
}
