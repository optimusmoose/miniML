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
}
