package workflow.state;

public class WarningState implements ProcessState {

    public boolean isError() {
        return false;
    }

    public boolean isWarning() {
        return true;
    }

    public boolean isReady() {
        return false;
    }
}
