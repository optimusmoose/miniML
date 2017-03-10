package state;

interface ProcessState {
    boolean isError();
    boolean isWarning();
    boolean isReady();
}
