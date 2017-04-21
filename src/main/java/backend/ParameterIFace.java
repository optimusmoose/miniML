package backend;

import java.util.ArrayList;

/**
 * Provide a uniform interface for accessing sets of parameters for
 * a given algorithm.
 */
public interface ParameterIFace {
    ArrayList<WrappedParam> getParameters();
}

class NeuralNetworkParameters implements ParameterIFace {
    ArrayList<WrappedParam> parameters;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: E,G,A,B,C,I,R,D
     * @return
     */
    public NeuralNetworkParameters(){
        ArrayList<WrappedParam> parameters = new ArrayList<WrappedParam>();
        //learning rate
        WrappedParam learnRate = new WrappedParamFloat("float","learning rate","-L",0,1);
        parameters.add(learnRate);
        //momentum
        WrappedParam momentum = new WrappedParamFloat("float","momentum","-M",0,1);
        parameters.add(momentum);
        //number of epochs
        WrappedParam epochs = new WrappedParamInt("int","number of epochs","-N",1,1000);
        parameters.add(epochs);
        //validation set size
        WrappedParam valid = new WrappedParamInt("int","validation set size","-V",1,99);
        parameters.add(valid);
        //seed
        WrappedParam seed = new WrappedParamInt("int","RNG seed","-S",0, Integer.MAX_VALUE - 1);
        parameters.add(seed);
        //nodes each layer
        WrappedParam layer = new WrappedParamString("string","layer width","-H","aiot");
        parameters.add(layer);
        this.parameters = parameters;
    }

    /**
     * Get all those nice parameters we made above.
     * @return parameters   a list of WrappedParam
     */
    public ArrayList<WrappedParam> getParameters() {
        return this.parameters;
    }

}


class LinearRegressionParameters implements ParameterIFace {
    ArrayList<WrappedParam> parameters;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: minimal, additional stats, debug, capabilities
     * @return
     */
    public LinearRegressionParameters(){
        ArrayList<WrappedParam> parameters = new ArrayList<WrappedParam>();
        //selection method
        WrappedParam selection = new WrappedParamInt("int","Selection method","-S",0,2);
        parameters.add(selection);
        //ridge parameter TODO correct window after getting info on ridge
        WrappedParam ridge = new WrappedParamFloat("float","ridge","-R",0,1);
        parameters.add(ridge);
        //eliminate colinear variables
        WrappedParam colinear = new WrappedParamEmpty("empty","Eliminate Colinear","-C");
        parameters.add(colinear);
        this.parameters = parameters;
    }

    /**
     * Get all those nice parameters we made above.
     * @return parameters   a list of WrappedParam
     */
    public ArrayList<WrappedParam> getParameters() {
        return this.parameters;
    }

}
