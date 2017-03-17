package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
class ModelContext extends AbstractContext {

    /**
     * dataset has not been set by user at start, workflow will be error
     */
    ModelContext() {
        super(StateFactory.INSTANCE.error());
    }

}
