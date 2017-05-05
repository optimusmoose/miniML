package workflow.context;

import static workflow.state.StateFactory.*;

/**
 * An abstract context for UI elements that command
 * MiniML to change states (e.g. begin analysis).
 *
 * THIS CONTEXT IS ALWAYS A CHILD OF ANOTHER CONTEXT.
 */
abstract class ImperativeContext extends AbstractCompositeContext {
    protected AbstractCompositeContext parentContext;

    public ImperativeContext(AbstractCompositeContext parentContext, String key) {
        super(INSTANCE.empty(), parentContext, key);
        this.parentContext = parentContext;
    }

    /**
     * This context is always a child of another context.
     * This context needs to know that its parent is ready
     * for the imperative to be executed.
     * @return true/false depending on whether parent is ready.
     */
    protected boolean verifyParentIsReady(){
        this.parentContext.updateState();
        return(this.parentContext.getState().isReady());
    }

    /**
     * Perform whatever imperative this context handles.
     */
    abstract void execute();
}
