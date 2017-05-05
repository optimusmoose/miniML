package workflow.builder;

/**
 * Descendants of this class collect several parameters from Context keys and inject them into a Builder or
 * Dispatcher directly. Use these to get new parameters into new places!
 */
abstract class AbstractParameterExtension {
    public AbstractDispatcherBuilder builder;

    public AbstractParameterExtension(AbstractDispatcherBuilder builder){
        this.builder = builder;
    }

    /**
     * Tell the extension to collect all of the parameters it needs from context keys.
     */
    abstract void collect();

    /**
     * Tell the extension to make its changes to the builder/dispatcher-- whatever they may be.
     */
    abstract void extend();
}
