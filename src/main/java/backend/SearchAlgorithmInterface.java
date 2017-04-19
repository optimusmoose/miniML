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

/**
 * Class implements truly random search of parameter space.
 */
class randomSearch implements SearchAlgorithmInterface {
    Dispatcher dispatcher;
    Random random = new Random();

    /**
     * Build our search algorithm and make it know who holds it.
     * This allows it to access other members of the dispatcher (like parameterIFace instances)
     *
     * @param dispatcher
     */
    public randomSearch(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    /**
     * Generate a random set of parameters for a model.
     *
     * Looks over all possible parameters in the ParameterIFace and assign values in their appropriate ranges.
     * Also handles flags that do not accept values (we call them 'empty' flags)
     *
     * @param parameterSet
     * @return an ArrayList of WrappedParamFinal to be unpacked and sent to a model.
     */
    public ArrayList<WrappedParamFinal> getNextParamSet(ParameterIFace parameterSet){
        ArrayList<WrappedParamFinal> outParams = new ArrayList<WrappedParamFinal>();
        ArrayList<WrappedParam> inParams = parameterSet.getParameters();
        for(WrappedParam p : inParams){
            switch(p.getType()) {
                case "int": {   //generate a random ranged int for this parameter
                    WrappedParamInt q = (WrappedParamInt) p;
                    int val = random.nextInt(q.getMaxValue() + 1 - q.getMinValue()) + q.getMinValue();
                    outParams.add(new WrappedParamFinal("int", q.getName(), q.getFlag(), String.valueOf(val)));
                    break;
                }
                case "float": { //generate a random ranged float for this parameter
                    WrappedParamFloat q = (WrappedParamFloat) p;
                    float val = q.getMinValue() + random.nextFloat() * (q.getMaxValue() - q.getMinValue());
                    outParams.add(new WrappedParamFinal("float", q.getName(), q.getFlag(), String.valueOf(val)));
                    break;
                }
                case "long": { //generate a random ranged long for this parameter
                    WrappedParamLong q = (WrappedParamLong) p;
                    boolean search = true;
                    long val;
                    do {
                        val = random.nextLong(); //why? because nextLong() is hilariously illogical.
                        if (val <= q.getMaxValue() && val >= q.getMinValue()) {
                            search = false;
                        }
                    } while(search);
                    outParams.add(new WrappedParamFinal("long", q.getName(), q.getFlag(), String.valueOf(val)));
                    break;
                }
                case "boolean": { //generate a random boolean for this parameter
                    WrappedParamBoolean q = (WrappedParamBoolean) p;
                    boolean val = random.nextBoolean();
                    outParams.add(new WrappedParamFinal("boolean", q.getName(), q.getFlag(), String.valueOf(val)));
                    break;
                }
                case "empty": { //add this flag, or do not add this flag
                    WrappedParamEmpty q = (WrappedParamEmpty) p;
                    boolean val = random.nextBoolean();
                    if (val) {
                        outParams.add(new WrappedParamFinal("empty", q.getName(), q.getFlag(), String.valueOf("")));
                    }
                    break;
                }
                default: {
                    log("Encountered an unknown parameter type: " + p.getType());
                }
            }
        }
        return outParams;
    }

    protected void log(String str) {
        MiniMLLogger.INSTANCE.debug("In SearchAlg: " + str);
    }
}
