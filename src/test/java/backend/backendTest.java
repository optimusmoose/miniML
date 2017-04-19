package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import weka.core.Instances;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * Driver for testing backend functionality with Weka libraries.
 * Should be able to demonstrate Instance, Evaluation, and function Weka operations
 *
 * @return void     null
 */
public class backendTest extends TestCase{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDownStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testMain()
    {
        String[] args = new String[]{};
        main(args);
    }

    /**
     * Run a test case of the backend.
     * Instantiates the command pattern, loads data into instance, and runs some tests.
     *
     * @param args
     */
    public static void main(String[] args) {
        Dispatcher dispatch = new Dispatcher();

        //load some data
        String path = "src/test/resources/data/cpu.arff";
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
        //mgr.setData(data);
        dispatch.setData(data);
        dispatch.setTimeLimit(1);
        dispatch.launch();

        //run some tests on the data.
        //taskList.addTask(lr); // run LR
        //taskList.addTask(nn); // run NN
        //taskList.doTask();
        //taskList.doTask();
    }

    /**
     * Returns a Weka Instance of the data for testing.
     * This will not be used in the finished product, but may be adapted to that end.
     *
     * @param  path     the local path to the data file (at this time, only .arff is supported)
     * @return data     a Weka Instance of the data
     */
    public static Instances read_data(String path) throws IOException {
        System.out.println("Opening file: " + path);
        BufferedReader reader = new BufferedReader(new FileReader(path));
        //create a weka data Instance
        Instances data = new Instances(reader);
        reader.close();
        return (data);
    }
}

