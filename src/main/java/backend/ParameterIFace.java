package backend;

import java.util.ArrayList;

/**
 * Provide a uniform interface for accessing sets of parameters for
 * a given algorithm.
 */
public interface ParameterIFace {
    ArrayList<WrappedParam> getParameters();
}

class ParameterIFace_NN implements ParameterIFace {
    ArrayList<WrappedParam> params;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: E,G,A,B,C,I,R,D
     * @return
     */
    public ParameterIFace_NN(){
        ArrayList<WrappedParam> params = new ArrayList<WrappedParam>();
        //learning rate
        WrappedParam learnRate = new WrappedParamFloat("float","learning rate","-L",0,1);
        params.add(learnRate);
        //momentum
        WrappedParam momentum = new WrappedParamFloat("float","momentum","-M",0,1);
        params.add(momentum);
        //number of epochs
        WrappedParam epochs = new WrappedParamInt("int","number of epochs","-N",1,1000);
        params.add(epochs);
        //validation set size
        WrappedParam valid = new WrappedParamInt("int","validation set size","-V",1,99);
        params.add(valid);
        //seed
        WrappedParam seed = new WrappedParamFloat("float","RNG seed","-S",0,1);
        params.add(seed);
        //nodes each layer
        WrappedParam layer = new WrappedParamString("String","layer width","-H","aiot");
        params.add(layer);
        this.params = params;
    }

    /**
     * Get all those nice parameters we made above.
     * @return params   a list of WrappedParam
     */
    public ArrayList<WrappedParam> getParameters() {
        return this.params;
    }

}


class ParameterIFace_LR implements ParameterIFace {
    ArrayList<WrappedParam> params;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: minimal, additional stats, debug, capabilities
     * @return
     */
    public ParameterIFace_LR(){
        ArrayList<WrappedParam> params = new ArrayList<WrappedParam>();
        //selection method
        WrappedParam selection = new WrappedParamInt("int","Selection method","-S",0,2);
        params.add(selection);
        //ridge parameter TODO correct window after getting info on ridge
        WrappedParam ridge = new WrappedParamFloat("float","ridge","-R",0,1);
        params.add(ridge);
        //eliminate colinear variables
        WrappedParam colinear = new WrappedParamEmpty("empty","Eliminate Colinear","-C");
        params.add(colinear);
        this.params = params;
    }

    /**
     * Get all those nice parameters we made above.
     * @return params   a list of WrappedParam
     */
    public ArrayList<WrappedParam> getParameters() {
        return this.params;
    }

}
