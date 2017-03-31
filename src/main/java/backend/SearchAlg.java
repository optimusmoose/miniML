package backend;

import java.util.ArrayList;
import java.util.Random;

import utils.TypeFactory;

/**
 * Explore the parameter space of an algorithm.
 */
public interface SearchAlg {
    ArrayList<WrappedParam> getNextParamSet(ParameterIFace parameterSet);
}

class randomSearch implements SearchAlg {
    Dispatcher dsp;
    Random random = new Random();

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
        ArrayList<WrappedParam> outParams = new ArrayList<WrappedParam>();
        ArrayList<WrappedParam> inParams = parameterSet.getParameters();
        //iterate through parameter set and generate some values for our weka call
        for(WrappedParam p : inParams){
            // TODO great, now do it for the rest of the types this may encounter...
            if(p.getType() == "int"){
                WrappedParamInt q = (WrappedParamInt) p;
                int val = random.nextInt(q.getMaxValue()+1 - q.getMinValue()) + q.getMinValue();
                outParams.add(new WrappedParamFinal("int",q.getName(),q.getFlag(),String.valueOf(val)));
            } else if (p.getType() == "float"){
                WrappedParamFloat q = (WrappedParamFloat) p;
                float val = q.getMinValue() + random.nextFloat() * (q.getMaxValue() - q.getMinValue());
                outParams.add(new WrappedParamFinal("float",q.getName(),q.getFlag(),String.valueOf(val)));
            } else if (p.getType() == "boolean"){
                WrappedParamBoolean q = (WrappedParamBoolean) p;
                boolean val = random.nextBoolean();
                outParams.add(new WrappedParamFinal("boolean",q.getName(),q.getFlag(),String.valueOf(val)));
            } else if (p.getType() == "empty") {
                WrappedParamEmpty q = (WrappedParamEmpty) p;
                boolean val = random.nextBoolean();
                if (val == true) { //in this case, the flag alone decide functionality; add the flag or do not!
                    outParams.add(new WrappedParamFinal("empty", q.getName(), q.getFlag(), String.valueOf("")));
                }
            }
        }
        return outParams;
    }
}
