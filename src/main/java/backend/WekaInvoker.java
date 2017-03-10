package backend;

import weka.core.Instances;

import java.util.LinkedList;
import java.util.Queue;

public class WekaInvoker {
}


/*
Receiver class.
This object accepts the instructions and parameters
to call Weka algorithms.

Ideally, this object will be able to cache a
parameter/algorithm/result set for easy reporting to
the main application.
*/
class WekaTaskManager{
    private Instances data; //it holds the dataset, too.
    //private String[] params;
    public void manage_LR(String[] params) {
        System.out.println("Calling linear regression");
        try {
            new LR_Model(data, params);
        } catch (Exception e) {
            System.out.println("LR hit error: " + e);
        }
    }
    public void manage_NN(String[] params) {
        System.out.println("Calling neural network");
        try {
            new NN_Model(data, params);
        } catch (Exception e) {
            System.out.println("NN hit error: " + e);
        }
    }
    public void setData(Instances d){
        data = d;
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


