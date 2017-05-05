package backend;

import java.util.ArrayList;

public class SmoParameters implements ParameterIFace {
    ArrayList<WrappedParam> parameters;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: M,V,W,K, and flags without 1-char names
     *
     * TODO: -K would be a very interesting flag to get working, but needs a new WrappedParam type
     * TODO: and possibly something to wrap the Kernel as well.
     * @return
     */
    public SmoParameters(){
        ArrayList<WrappedParam> parameters = new ArrayList<WrappedParam>();
        //complexity constant
        WrappedParam complexity = new WrappedParamFloat("float","complexity constant","-C",(float)0.1,(float)10.0);
        parameters.add(complexity);
        //tolerance
        WrappedParam tolerance = new WrappedParamFloat("float","tolerance","-L",(float)1.0e-6,(float)1.0e-1);
        //parameters.add(tolerance);
        //epsilon
        WrappedParam epsilon = new WrappedParamDouble("double","epsilon","-P",1.0e-16,1.0e-2);
        //parameters.add(epsilon);
        //normalize?
        WrappedParam normalize = new WrappedParamInt("int","normalize/standardize/none","-N",0,2);
        parameters.add(normalize);
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
