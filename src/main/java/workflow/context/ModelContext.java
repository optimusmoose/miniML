package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
class ModelContext extends AbstractCompositeContext {

    public static final String KEY = "MODEL_CONFIG";

    /**
     * dataset has not been set by user at start, state will be error
     */
    ModelContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.error(), parentContext);
    }

}
