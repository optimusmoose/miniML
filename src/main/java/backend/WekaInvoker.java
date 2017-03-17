package backend;

import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * TODO finish commenting these parts before design pattern gets compromised
 */
public class WekaInvoker {
}

/**
 *
 *Receiver class.
 *This object accepts the instructions and parameters
 *to call Weka algorithms.
 *
 *Ideally, this object will be able to cache a
 *parameter/algorithm/result set for easy reporting to
 the main application.
 */
class WekaTaskManager{
    protected Instances data; //it holds the dataset, too.
    protected ArrayList<Evaluation> evals = new ArrayList<Evaluation>();
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

    /**
     * Untested.
     *
     * Write a Model to file for future loading.
     *
     * @param obj
     * @param fileName
     * @throws IOException
     */
    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        fos.close();
    }

    /**
     * Untested
     *
     * Read a previous Model in from file and make it accessible.
     *
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject(); //TODO recast as some concrete Model!
        ois.close();
        return obj;
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


