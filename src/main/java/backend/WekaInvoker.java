package backend;

import utils.Logging.MiniMLLogger;

import weka.core.Instances;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Delegates tasks to other objects. See abstract class javadoc for more information.
 *
 */
public class WekaInvoker extends TaskInvoker {
    private Queue<WekaTask> taskQueue = new LinkedList<WekaTask>();

    public WekaInvoker() {
    }

    /**
     * Add a task to our queue
     *
     * @param task
     */
    void addTask(WekaTask task) {
        taskQueue.add(task);
    }

    /**
     *Do the next task in our queue
     *TODO this should probably return something
     */
    void doTask() {
        taskQueue.poll().execute();
    }
}


/**
 * Abstract class from which any backend command-invoking object should be defined (most notably, WekaInvoker).
 * This may be how other (non-Weka) data-processing libraries can be added in the future.
 */
abstract class TaskInvoker {
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
    protected int maxThreads;
    protected Instances data;
    protected ArrayList<Model> finishedModels = new ArrayList<Model>();
    protected ArrayList<String[]> unsolvedModels = new ArrayList<String[]>();
    protected ThreadPoolExecutor executor;

    /**
     * Construct the WekaTaskManager. Accepts a number of threads to use.
     * @param maxThreads
     */
    public WekaTaskManager(int maxThreads){
        this.maxThreads = maxThreads;
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(this.maxThreads);
    }

    /**
     * Add a model to the unsolvedModels; it will be pulled into a thread soon.
     * @param unsolvedModel
     */
    public void addModel(String[] unsolvedModel){
       this.unsolvedModels.add(unsolvedModel);
    }

    /**
     * Dump the unsolvedModels arraylist into the thread pool, causing lots of models to be evaluated in short order.
     *
     * This could probably be handled more elegantly, but it seems to work fairly well.
     * @throws Exception
     */
    public void runModel() throws Exception {
        while(this.unsolvedModels.size() > 0) {
            String[] next_model = this.unsolvedModels.remove(0);
            String model_name = next_model[0];
            String[] parameters = Arrays.copyOfRange(next_model, 1, next_model.length);
            switch (model_name) {
                case ("lr"):
                    LR_Model lr_model = new LR_Model(data, parameters);
                    executor.execute(lr_model);
                    finishedModels.add(lr_model);
                    break;
                case ("nn"):
                    NN_Model nn_model = new NN_Model(data, parameters);
                    executor.execute(nn_model);
                    finishedModels.add(nn_model);
                    break;
                case ("dt"):
                    DT_Model dt_model = new DT_Model(data, parameters);
                    executor.execute(dt_model);
                    finishedModels.add(dt_model);
                    break;
                case ("smo"):
                    SMO_Model smo_model = new SMO_Model(data, parameters);
                    executor.execute(smo_model);
                    finishedModels.add(smo_model);
                    break;
                default:
                    MiniMLLogger.INSTANCE.debug("Encountered unknown model type" + model_name);
            }
        }
    }

    /**
     * Manage a Weka LinearRegression Model.
     *
     * @param params
     */
    public void manage_LR(String[] params) {
        MiniMLLogger.INSTANCE.info("Calling linear regression");
        try {
            LR_Model lr = new LR_Model(data, params);
            finishedModels.add(lr);
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.error("LR hit error: " + e);
        }
    }

    /**
     * Manage a Weka NeuralNetwork Model.
     *
     * @param params
     */
    public void manage_NN(String[] params) {
        MiniMLLogger.INSTANCE.info("Calling neural network");
        try {
            NN_Model nn = new NN_Model(data, params);
            finishedModels.add(nn);
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.error("NN hit error: " + e);
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

    public Model findBestModel(){
        double lowestError = 100;
        Model bestModel = null;
        for(Model model : finishedModels){
            if(model.eval.rootRelativeSquaredError() < lowestError){
                lowestError = model.eval.rootRelativeSquaredError();
                bestModel = model;
            }
        }
        return(bestModel);
    }
}


