package backend;

import java.util.ArrayList;

/**
 * Explore the parameter space of an algorithm.
 */
public interface SearchAlg {
    ArrayList<WrappedParam> getNextParamSet(ParameterIFace parameterSet);
}

class randomSearch implements SearchAlg {
    Dispatcher dsp;

    /**
     * Build our search algorithm and make it know who holds it.
     * This allows it to access other members of the dispatcher (like parameterIFace instances)
     *
     * @param owner
     */
    public randomSearch(Dispatcher owner){
        dsp = owner;
    }

    public ArrayList<WrappedParam> getNextParamSet(ParameterIFace parameterSet){
        ArrayList<WrappedParam> params = new ArrayList<WrappedParam>();
        return params;
    }
}
