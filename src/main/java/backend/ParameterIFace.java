package backend;

import java.util.ArrayList;

/**
 * Provide a uniform interface for accessing sets of parameters for
 * a given algorithm.
 */
public interface ParameterIFace {
    ArrayList<WrappedParam> getParameters();
}
