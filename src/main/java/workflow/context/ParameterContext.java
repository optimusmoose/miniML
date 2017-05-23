package workflow.context;

import workflow.state.StateFactory;

/**
 * An abstract context for configuration objects
 * Will hold details about specific contextual objects
 * Validates and updates its own state
 */
public class ParameterContext extends AbstractCompositeContext {

    protected Object value; //object form of a primitive or other type such as range

    /**
     * Instantiate context with a null state
     */
    public ParameterContext(ContextInterface parentContext, String key) {
        super(StateFactory.INSTANCE.empty(), parentContext, key);
    }

    /**
     * an abstract function to be defined in concrete Context's
     * checks if the values meet the criteria of the specific context
     * to be used for updating state
     * @return boolean
     */
    protected boolean isValid() {
        return this.value != null;
    }

    /**
     * update the State of the Context based on the self defined isValid method
     * @return ProcessState
     */
    public void updateState()
    {
        if(this.isValid())
        {
            this.state = StateFactory.INSTANCE.ready();
        }
        this.log();
        this.parent.updateState();
    }

    /**
     * getter for the value object
     * @return Object
     */
    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
