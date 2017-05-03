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
 * what fresh hell is this
 */
abstract class AbstractDispatcherBuilder {
    public Dispatcher dispatcher;
    public int maxThreads = 8; //TODO
    public Instances data;
    public SearchAlgorithmInterface searchType;
    ParameterIFace neuralNetworkParameters;
    ParameterIFace linearRegressionParameters;
    ParameterIFace decisionTreeParameters;

    public AbstractDispatcherBuilder(){
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
