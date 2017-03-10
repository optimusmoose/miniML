package state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Abstract context defines shared f
 */
abstract class AbstractContext implements ContextInterface {
    protected ProcessState state;
    protected List<ContextInterface> childContexts;

    AbstractContext(ProcessState state) {
        this.state = state;
        childContexts = new ArrayList<ContextInterface>();
    }

    //return the state of the context
    public ProcessState getState(){
        return this.state;
    }

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
                .collect( Collectors.toList() ).contains(true)// TODO: BUG! this contains needs to be changed, need to check for all true not just one
                ) {
            return ReadyState.class.cast(this.state);
        }
        return this.state;
    }
}
