package state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * The context of the entire process, state is dependent on that of it's child contexts
 */
@SuppressWarnings("Since15")
public class MiniMLContext extends AbstractContext {

    private List<ContextInterface> childContexts;
    private DatasetContext dc;
    private PreprocessContext pc;
    private ModelContext mc;

    MiniMLContext() {
        //assuming no config, state will be error
        super(StateFactory.INSTANCE.error());

        //instantiate context for each panel
        dc = new DatasetContext();
        pc = new PreprocessContext();
        mc = new ModelContext();
//        childContexts = new ContextInterface[]{dc, pc, mc};
        childContexts = new ArrayList<ContextInterface>();
        childContexts.add(dc);
        childContexts.add(pc);
        childContexts.add(mc);

        this.updateState();
    }

    void updateState(){
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

    private boolean checkChildrensStates(Class stateClass) {
        return this.childContexts.stream()
                .map( context -> context.getState().isWarning() )
                .collect( Collectors.toList() ).contains(true);
    }
}
