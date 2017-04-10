package workflow.context;

import workflow.state.ProcessState;

/**
 * An interface to define the context of the state
 * Contexts consists of states and children contexts
 * States are updated based on that of child contexts
 * Contexts can perform actions dependent on state
 */
public interface ContextInterface {
    ProcessState getState();
    void updateState();
}
