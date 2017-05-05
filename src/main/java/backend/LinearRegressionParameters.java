package backend;

import java.util.ArrayList;

public class LinearRegressionParameters implements ParameterIFace {
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
