package workflow.context;

import workflow.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract context defines shared functionality amongst all context, nesting context
 */
abstract class AbstractCompositeContext implements ContextInterface {
    protected ProcessState state;
    protected ContextInterface parent;
    protected HashMap<String, ContextInterface> childContexts;

    /**
     * Instantiate a Context with a State
     * @param state ProcessState
     */
    AbstractCompositeContext(ProcessState state, ContextInterface parentContext) {
        this.state = state;
        this.parent = parentContext;
        this.childContexts = new HashMap<String, ContextInterface>();//TODO: should be a dictionary? will require streams work
    }

    /**
     * return the State of the Context
     * @return ProcessState
     */
    public ProcessState getState(){
        return this.state;
    }

    /**
     * Update the State of the contextual object based on the states of its children
     * TODO: these can maybe all be map.reductions w Boolean::logicalOr/logicalAnd vs map.collect.contains
     */
    public void updateState(){
        //map operation to check all child contexts for error state
        if(this.childContexts.values().stream()
                .map( context -> context.getState().isError() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            this.state = StateFactory.INSTANCE.error();
            this.parent.updateState();
            return;
        }

        //likewise for warnings
        if(this.childContexts.values().stream()
                .map( context -> context.getState().isWarning() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            this.state = StateFactory.INSTANCE.warning();
            this.parent.updateState();
            return;
        }

        //and readies, note that all children must be ready for a parent to be ready
        if(! this.childContexts.values().stream()
                .map( context -> context.getState().isReady() )
                .collect( Collectors.toList() ).contains(false)
                ) {
            this.state = StateFactory.INSTANCE.ready();
            this.parent.updateState();
            return;
        }
    }

    public void addChildContext(String key, ContextInterface childContext) {
        this.childContexts.put(key, childContext);
    }

    public void removeChildContext(ContextInterface childContext) {
        //TODO: implement
    }

    public ContextInterface getChildContextByKey(String key) {
        return this.childContexts.get(key);
    }
}
