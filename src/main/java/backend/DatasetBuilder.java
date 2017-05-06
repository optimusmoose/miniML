package backend;

import utils.Logging.MiniMLLogger;
import weka.core.Instances;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.ParameterContext;

import java.util.Arrays;
import java.util.Collections;

/**
 * Hold the master dataset Instance key and give out carefully prepared sections as needed.
 *
 * This should not only reinforce user expectations but also implement random feature search.
 *
 * THIS OBJECT HANDLES ITS OWN CONTEXT KEYS.
 */
public class DatasetBuilder {
    private Instances master;
    protected int[] userAttributeSelectionIndices;
    protected int classIndex;

    public DatasetBuilder(){
        MiniMLLogger.INSTANCE.info("in ctor");
        this.collect();
        this.log_all();
    }

    /**
     * Collect all of the context values this builder needs from keys.
     */
    private void collect(){
        //Get the master Instance of our data
        ParameterContext masterContext = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.RootWekaInstnace);
        this.master = (Instances) masterContext.getValue();

        //Get the class index
        ParameterContext classIndexContext= (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedClassifier);
        this.classIndex = (int) classIndexContext.getValue();

        //Get the selected attribute indices
        ParameterContext selectedAttributeIndices = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedAttributes);
        this.userAttributeSelectionIndices = (int[]) selectedAttributeIndices.getValue();
    }

    /**
     * Print out what's going on in here. Useful for debugging.
     */
    public void log_all(){
        MiniMLLogger.INSTANCE.info(this.master.toString());
        MiniMLLogger.INSTANCE.info("attributes:");
        for(int val: this.userAttributeSelectionIndices) {
            MiniMLLogger.INSTANCE.info(Integer.toString(val));
        }
        MiniMLLogger.INSTANCE.info("Class index");
        MiniMLLogger.INSTANCE.info(Integer.toString(this.classIndex));
        //System.exit(1);
    }

    /**
     * Create a new, smaller data Instances that contains only the attributes the user selected.
     *
     * It does this by counting out the indices that the user wanted and removing anything that doesn't match.
     * If it were elegant, it wouldn't be java. :/
     *
     * @return Instances newData
     */
    public Instances getUserSpecifiedDataset(){
        Instances newData = new Instances(this.master);
        newData.setClass(newData.attribute(this.classIndex));
        //need to reverse sort them so our array doesn't get screwed up.
        Integer[] keepAttributes = new Integer[this.userAttributeSelectionIndices.length];
        for (int i = 0; i < this.userAttributeSelectionIndices.length; i++) {
            keepAttributes[i] = Integer.valueOf(this.userAttributeSelectionIndices[i]);
        }
        Arrays.sort(keepAttributes, Collections.reverseOrder());
        for(Integer i = this.userAttributeSelectionIndices.length; i >= 0; --i){
            if(! Arrays.asList(keepAttributes).contains(i)){
                newData.deleteAttributeAt(i);
            }
        }
        return(newData);
    }
}
