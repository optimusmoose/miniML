package workflow.context;

import workflow.state.StateFactory;

/*
 * The context of the entire process
 */
public class MiniMLContext extends AbstractCompositeContext {

    /*
        TODO: I would really like a DI solution, would make things more testable
     */
    public MiniMLContext(String key) {
        super(StateFactory.INSTANCE.empty(), ContextFactory.INSTANCE.empty(), key);
    }

    //TODO: create and move to analise context
    public void run() {
        if(this.state.isReady())
        {
            //TODO: call run in Weka
            //might need a running state, complete state? Transition to running would occur here
        }
    }
}
