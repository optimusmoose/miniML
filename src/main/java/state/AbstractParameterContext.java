package state;

/**
 * An abstract context for configuration objects
 * Will hold details about specific contextual objects
 * Validates and updates its own state
 */
abstract class AbstractParameterContext extends AbstractContext {

    private Object value; //object form of a primative or other type such as range

    AbstractParameterContext() {
        super(StateFactory.INSTANCE.empty());
    }

    abstract boolean isValid();

    public ProcessState updateState()
    {
        if(this.isValid())
        {
            ReadyState.class.cast(this.state);
        }
        return this.state;
    }

    void setContext(String type, String value) {
        TypeFactory.INSTANCE.get(type, value);
    }

    Object getValue() {
        return this.value;
    }
}
