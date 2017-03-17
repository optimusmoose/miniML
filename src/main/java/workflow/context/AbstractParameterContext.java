package workflow.context;

import utils.TypeFactory;
import workflow.state.ProcessState;
import workflow.state.ReadyState;
import workflow.state.StateFactory;

/**
 * An abstract context for configuration objects
 * Will hold details about specific contextual objects
 * Validates and updates its own workflow
 */
abstract class AbstractParameterContext extends AbstractContext {

    private Object value; //object form of a primative or other type such as range

    /**
     * Instantiate context with a null workflow
     */
    AbstractParameterContext(ContextInterface parentContext) {
        super(StateFactory.INSTANCE.empty(), parentContext);
    }


    /**
     * an abstract function to be defined in concrete Context's
     * checks if the values meet the criteria of the specific context
     * to be used for updating workflow
     * @return boolean
     */
    abstract boolean isValid();

    /**
     * update the State of the Context based on the self defined isValid method
     * @return ProcessState
     */
    public void updateState()
    {
        if(this.isValid())
        {
            ReadyState.class.cast(this.state);
        }
    }

    /**
     * Set the details of the Context
     * @param type String
     * @param value String
     */
    void setContext(String type, String value) {
        this.value = TypeFactory.INSTANCE.get(type, value);
    }

    /**
     * getter fot the value object
     * @return Object
     */
    Object getValue() {
        return this.value;
    }
}
