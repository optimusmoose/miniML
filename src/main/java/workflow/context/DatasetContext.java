package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the dataset configuration
 */
class DatasetContext extends AbstractCompositeContext {

    private AbstractParameterContext fileName;

    /**
     * Instantiate with error state, as no dataset is selecton on construction
     */
    DatasetContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.error(), parentContext);
        this.addChildContext(new FileContext(parentContext));
    }

}
