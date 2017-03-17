package workflow.context;

public enum ContextFactory {
    INSTANCE;

    public ContextInterface empty()
    {
        return new NullContext();
    }
}