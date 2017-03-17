package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
public class PreprocessContext extends AbstractContext {

    /**
     * no pre-processing functionality yet, workflow is ready
     */
    PreprocessContext() {
        super(StateFactory.INSTANCE.ready());
    }
}
