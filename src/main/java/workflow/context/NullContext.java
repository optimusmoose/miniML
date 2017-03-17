package workflow.context;

import workflow.state.ProcessState;

/**
 * A Null Context, to define behavior for when a context does not have a parent
 */
public class NullContext implements ContextInterface {

    @Override
    public ProcessState getState() {
        return null;
    }

    @Override
    public void updateState() {
        return;
    }
}
