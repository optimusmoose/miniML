package state;

class ReadyState implements ProcessState {

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
