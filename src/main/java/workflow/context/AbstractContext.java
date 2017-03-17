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
abstract class AbstractContext implements ContextInterface {
    protected ProcessState state;
    protected List<ContextInterface> childContexts;


    /**
     * Instantiate a Context with a State
     * @param state ProcessState
     */
    AbstractContext(ProcessState state) {
        this.state = state;
        childContexts = new ArrayList<ContextInterface>();
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
     * @return ProcessState
     */
    public ProcessState updateState(){
        //map operation to check all child contexts for error workflow
        if(this.childContexts.stream()
                .map( context -> context.getState().isError() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            return ErrorState.class.cast(this.state);
        }

        //likewise for warnings
        if(this.childContexts.stream()
                .map( context -> context.getState().isWarning() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            return WarningState.class.cast(this.state);
        }

        //and readies, note that all children must be ready for a parent to be ready
        if(! this.childContexts.stream()
                .map( context -> context.getState().isReady() )
                .collect( Collectors.toList() ).contains(false)
                ) {
            return ReadyState.class.cast(this.state);
        }

        //otherwise return the current workflow, i.e. if all children have a null workflow
        return this.state;
    }
}
