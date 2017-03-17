package workflow.context;

import workflow.state.StateFactory;

/**
 * The Context of the algorithm models configuration
 */
public class PreprocessContext extends AbstractCompositeContext {

    public static final String KEY = "PREPROCESS_CONFIG";

    /**
     * no pre-processing functionality yet, state is ready
     */
    PreprocessContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.ready(), parentContext);
    }
}
