package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import weka.core.Instances;
import weka.core.Utils;


// Driver to test weka stuff.
public class Driver{
    public static void main(String[] args) {
        WekaTaskManager mgr = new WekaTaskManager();
        LR_Task lr = new LR_Task(mgr);
        NN_Task nn = new NN_Task(mgr);
        TaskInvoker taskList = new TaskInvoker();

        //load some data
        String path = "github/miniML/test_data/cpu.arff";
        Instances data = null;
        try {
            data = read_data(path);
        } catch (IOException e) {
            System.out.println("Error opening file: " + path +", exiting.");
            System.out.println("Error: " + e);
            System.out.println(System.getProperty("user.dir"));
            System.exit(0);
        }

        //designate the last column as the data's 'class'
        data.setClassIndex(data.numAttributes() - 1);
        //give data to the WekaTaskManager
        mgr.setData(data);

        //run some tests on the data.
        taskList.addTask(lr); // run LR
        taskList.addTask(nn); // run NN
        taskList.doTask();
        taskList.doTask();
    }

    public static Instances read_data(String path) throws IOException {
        System.out.println("Opening file: " + path);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        //create a weka data Instance
        Instances data = new Instances(reader);
        reader.close(); //done with this.
        return (data);
    }
}

