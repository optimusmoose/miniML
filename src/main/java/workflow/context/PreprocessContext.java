package workflow.context;

import workflow.Keys;
import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
public class PreprocessContext extends AbstractCompositeContext {

    /**
     * no pre-processing functionality yet, state is ready
     */
    PreprocessContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.ready(), parentContext, Keys.PreprocessConfig);
    }
}
