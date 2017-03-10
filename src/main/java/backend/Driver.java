package backend;

// Driver to test weka stuff.
public class Driver{
    public static void main(String[] args) {
        WekaTaskManager mgr = new WekaTaskManager();
        LR_Task lr = new LR_Task(mgr);
        NN_Task nn = new NN_Task(mgr);
        TaskInvoker taskList = new TaskInvoker();

        taskList.addTask(lr); // run LR
        taskList.addTask(nn); // run NN
        taskList.doTask();
        taskList.doTask();
    }
}

