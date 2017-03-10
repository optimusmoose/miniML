package backend;

import java.util.LinkedList;
import java.util.Queue;

public class WekaInvoker {
}


// Receiver class.
class WekaTaskManager{
    public void manage_LR() {
        System.out.println("Calling linear regression");
    }
    public void manage_NN() {
        System.out.println("Calling neural network");
    }
}

// Invoker.
class TaskInvoker {
    private Queue<WekaTask> taskQueue = new LinkedList<WekaTask>();

    public TaskInvoker() {
        //Maybe later.
    }

    /*
    Add a task to our queue
     */
    void addTask(WekaTask task) {
        taskQueue.add(task);
        //task.execute(taskQueue.poll());
    }

    /*
    Do the next task in our queue
    TODO this should probably return something
     */
    void doTask() {
        taskQueue.poll().execute();
    }
}


