package workflow.state;

import workflow.state.ProcessState;

public class ErrorState implements ProcessState {

    public boolean isError() {
        return true;
    }

    public boolean isWarning() {
        return false;
    }

    public boolean isReady() {
        return false;
    }
}
