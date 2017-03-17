package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the dataset configuration
 */
class DatasetContext extends AbstractCompositeContext {

    public static final String KEY = "DATASET_CONFIG";

    private AbstractParameterContext fileName;

    /**
     * Instantiate with error state, as no dataset is selecton on construction
     */
    DatasetContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.error(), parentContext);
        this.addChildContext(FileContext.INPUT_FILE_KEY, new FileContext(parentContext));
    }

}
