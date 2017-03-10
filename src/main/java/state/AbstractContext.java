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

    public void updateState(){
        //map operation to check all child contexts for error state
        if(this.childContexts.stream()
                .map( context -> context.getState().isError() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            ErrorState.class.cast(this.state);
            return;
        }

        //likewise for warnings
        if(this.childContexts.stream()
                .map( context -> context.getState().isWarning() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            WarningState.class.cast(this.state);
            return;
        }

        //and readies
        if(this.childContexts.stream()
                .map( context -> context.getState().isReady() )
                .collect( Collectors.toList() ).contains(true)
                ) {
            ReadyState.class.cast(this.state);
            return;
        }
    }
}
