package workflow.context;

import workflow.state.StateFactory;

public class AnalyzeContext extends AbstractCompositeContext {
    private AbstractCompositeContext parentContext;

    public AnalyzeContext(ContextInterface parentContext, String key) {
        super(StateFactory.INSTANCE.empty(), parentContext, key);
    }

}
