package workflow.state;

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

    public String toString() {
        return "Ready";
    }
}
