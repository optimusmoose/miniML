package workflow.builder;

import backend.Dispatcher;
import backend.ParameterIFace;
import backend.SearchAlgorithmInterface;
import utils.Logging.MiniMLLogger;
import weka.core.Instances;
import weka.core.WekaEnumeration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * A DispatcherBuilder is an object that pulls in all of the required parameters/defaults/whatever
 * to prepare the Dispatcher, which in turn barks out orders to the thread pool.
 *
 * This is kind of a hack (and kind of has to be a hack) because we need a way to pull in all of
 * the ParameterContexts and other little details of the run.
 *
 * Currently, only the NoUserParameterDispatcherBuilder is supported but with a little suffering
 * it should be possible to make other builders.
 */
abstract class AbstractDispatcherBuilder {
    public Dispatcher dispatcher;
    public int maxThreads = 8; //TODO
    public Instances data;
    public SearchAlgorithmInterface searchType;
    ParameterIFace neuralNetworkParameters;
    ParameterIFace linearRegressionParameters;
    ParameterIFace decisionTreeParameters;
    ParameterIFace smoParameters;

    public AbstractDispatcherBuilder(){
    }

    /**
     * TODO: this obviously doesn't belong here and it won't be here long. Leave it alone, Zach.
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
        //TODO: this is the basic approach to getting column names-- use it to populate a selector for the dataset
        //TODO: tab. FWIW it probably doesn't even belong here. sorry zach.
        /*
        Enumeration atts = data.enumerateAttributes();
        while (atts.hasMoreElements()){
            System.out.println(atts.nextElement());
        }
        */
        reader.close();
        return (data);
    }

}
