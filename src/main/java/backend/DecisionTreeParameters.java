package backend;

import java.util.ArrayList;

public class DecisionTreeParameters implements ParameterIFace {
    ArrayList<WrappedParam> parameters;
    /**
     * Creates an array of all useful parameters, their mins/maxes, flags, etc.
     * For now, we are skipping: Q,L
     *
     * TODO: some of these parameters interact in nasty ways. which do we actually want?
     * @return
     */
    public DecisionTreeParameters(){
        ArrayList<WrappedParam> parameters = new ArrayList<WrappedParam>();
        //Min instances per leaf
        WrappedParam minInstances= new WrappedParamInt("int","Min instances per leaf","-M",1,20);
        parameters.add(minInstances);
        //confidence threshold for pruning.
        WrappedParam pruneConfidence= new WrappedParamFloat("float","prune confidence","-C", (float)0.01, (float)1.0);
        parameters.add(pruneConfidence);
        //use unpruned tree
        //WrappedParam unpruned = new WrappedParamEmpty("empty","Unpruned tree","-U");
        //parameters.add(unpruned);
        //use reduced error pruning
        //WrappedParam errorPruning = new WrappedParamEmpty("empty","reduced error pruning","-R");
        //parameters.add(errorPruning);
        //number of folds for reduced error pruning TODO is this ok?
        //WrappedParam numberFolds = new WrappedParamInt("int","reduced error folds","-N",1,5);
        //parameters.add(numberFolds);
        //use only binary splits
        WrappedParam binarySplits= new WrappedParamEmpty("empty","binary splits","-B");
        parameters.add(binarySplits);
        //no subtree raising
        WrappedParam noSubtreeRaise= new WrappedParamEmpty("empty","no subtree raising","-S");
        parameters.add(noSubtreeRaise);
        //laplace smoothing on predicted probabilities
        WrappedParam useLaplace = new WrappedParamEmpty("empty","laplace smoothing","-A");
        parameters.add(useLaplace);
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
