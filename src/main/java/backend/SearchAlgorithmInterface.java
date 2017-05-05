package backend;

import utils.Logging.MiniMLLogger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Explore the parameter space of an algorithm.
 */
public interface SearchAlgorithmInterface {
    ArrayList<WrappedParamFinal> getNextParamSet(ParameterIFace parameterSet);
}

