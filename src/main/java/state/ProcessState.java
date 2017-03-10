package state;

/*
 * An interface to define State.
 */
interface ProcessState {
    boolean isError();
    boolean isWarning();
    boolean isReady();
}
