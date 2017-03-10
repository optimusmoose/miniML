package state;

/**
 * An interface to define States that can be seen within the workflow
 * Contains a check for each primary State extension.
 * Only the matching extension should return true on any function
 */
interface ProcessState {
    boolean isError();
    boolean isWarning();
    boolean isReady();
}
