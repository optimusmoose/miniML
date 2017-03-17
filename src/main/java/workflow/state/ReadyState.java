package workflow.state;

import workflow.state.ProcessState;

public class ReadyState implements ProcessState {

    public boolean isError() {
        return false;
    }

    public boolean isWarning() {
        return false;
    }

    public boolean isReady() {
        return true;
    }
}
