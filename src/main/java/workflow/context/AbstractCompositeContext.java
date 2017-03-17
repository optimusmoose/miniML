package workflow.context;

import workflow.state.ErrorState;
import workflow.state.ProcessState;
import workflow.state.ReadyState;
import workflow.state.WarningState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract context defines shared functionality amongst all context, nesting context
 */
abstract class AbstractCompositeContext implements ContextInterface {
    protected ProcessState state;
    protected ContextInterface parent;
    protected List<ContextInterface> childContexts;


    /**
     * Instantiate a Context with a State
     * @param state ProcessState
     */
    AbstractCompositeContext(ProcessState state, ContextInterface parentContext) {
        this.state = state;
        this.parent = parentContext;
        this.childContexts = new ArrayList<ContextInterface>();
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
        //map operation to check all child contexts for error workflow
        if(this.childContexts.stream()
                .map( context -> context.getState().isError() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            this.state =  ErrorState.class.cast(this.state);
            this.parent.updateState();
            return;
        }

        //likewise for warnings
        if(this.childContexts.stream()
                .map( context -> context.getState().isWarning() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            this.state = WarningState.class.cast(this.state);
            this.parent.updateState();
            return;
        }

        //and readies, note that all children must be ready for a parent to be ready
        if(! this.childContexts.stream()
                .map( context -> context.getState().isReady() )
                .collect( Collectors.toList() ).contains(false)
                ) {
            this.state = ReadyState.class.cast(this.state);
            this.parent.updateState();
            return;
        }
    }

    public void addChildContext(ContextInterface childContext) {
        this.childContexts.add(childContext);
    }

    public void removeChildContext(ContextInterface childContext) {
        //TODO: implement
    }

    public void getChildContext(String id) {
        //TODO: implement
    }
}
