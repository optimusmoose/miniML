package workflow.state;

import workflow.state.ProcessState;

/**
 * Null State for when state is not yet defined
 */
public class NullState implements ProcessState {
    public boolean isError() {
        return false;
    }

    public boolean isWarning() {
        return false;
    }

    public boolean isReady() {
        return false;
    }
}
