package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
class ModelContext extends AbstractCompositeContext {

    /**
     * dataset has not been set by user at start, state will be error
     */
    ModelContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.error(), parentContext);
    }

}
