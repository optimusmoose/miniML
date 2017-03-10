package state;

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
        //map operation to check all child contexts for error state
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

        //and readies
        if(this.childContexts.stream()
                .map( context -> context.getState().isReady() )
                // TODO: BUG! this contains needs to be changed, need to check for all true not just one
                .collect( Collectors.toList() ).contains(true)
                ) {
            return ReadyState.class.cast(this.state);
        }

        //otherwise return the current state, i.e. if all children have a null state
        return this.state;
    }
}
