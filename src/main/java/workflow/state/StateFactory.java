package workflow.state;

/**
 * A factory to consistently instantiate workflow objects
 */
public enum StateFactory {
    INSTANCE;

    public ProcessState empty()
    {
        return new NullState();
    }

    public ProcessState ready()
    {
        return new ReadyState();
    }

    public ProcessState warning()
    {
        return new WarningState();
    }

    public ProcessState error()
    {
        return new ErrorState();
    }
}
