package state;

/*
 * An interface to define the context of the workflow
 * Contexts consists of states and children contexts
 * States are updated based on that of child contexts
 * Contexts can perform actions dependent on state
 */
interface ContextInterface {
    ProcessState getState();
    void updateState();
}
