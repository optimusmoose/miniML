package workflow.context;

import workflow.Keys;
import workflow.state.StateFactory;

/**
 * The Context of the dataset configuration
 */
public class DatasetContext extends AbstractCompositeContext {

    private AbstractParameterContext fileName;

    /**
     * Instantiate with error state, as no dataset is selecton on construction
     */
    public DatasetContext(ContextInterface parentContext, String key) {
        super(StateFactory.INSTANCE.error(), parentContext, key);
//        this.addChildContext(FileContext.INPUT_FILE_KEY, new FileContext(parentContext, ));
    }

}
