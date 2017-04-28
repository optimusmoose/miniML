package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
public class ModelContext extends AbstractCompositeContext {

    /**
     * dataset has not been set by user at start, state will be error
     */
    public ModelContext(ContextInterface parentContext, String key) {
        super(StateFactory.INSTANCE.error(), parentContext, key);
    }

}
