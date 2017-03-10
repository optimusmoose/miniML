package backend;

public interface WekaTask {
    public abstract void execute ( );
}

//ConcreteCommand Class.
class LR_Task implements WekaTask {
    private WekaTaskManager manager;
    public LR_Task (WekaTaskManager mgr) {
        manager = mgr;
    }
    public void execute() {
        manager.manage_LR();
    }
}

//ConcreteCommand Class.
class NN_Task implements WekaTask {
    private WekaTaskManager manager;
    public NN_Task (WekaTaskManager mgr) {
        manager = mgr;
    }
    public void execute() {
        manager.manage_NN();
    }
}
