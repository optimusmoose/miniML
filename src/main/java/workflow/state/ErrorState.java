package workflow.state;

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

    public String toString() {
        return "Error";
    }
}
