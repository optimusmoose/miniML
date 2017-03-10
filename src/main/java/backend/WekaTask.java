package backend;

public interface WekaTask {
    public abstract void execute ( );
}

//ConcreteCommand Class.
class LR_Task implements WekaTask {
    private WekaTaskManager manager;
    private String[] params;
    public LR_Task (WekaTaskManager mgr) {
        manager = mgr;
    }
    public void execute() {
        manager.manage_LR(params);
    }
}

//ConcreteCommand Class.
class NN_Task implements WekaTask {
    private WekaTaskManager manager;
    private String[] params;
    public NN_Task (WekaTaskManager mgr) {
        manager = mgr;
    }
    public void execute() {
        manager.manage_NN(params);
    }
}
