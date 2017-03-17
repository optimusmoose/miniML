package workflow.context;

import workflow.state.StateFactory;

/*
 * The context of the entire process
 */
public class MiniMLContext extends AbstractContext {

    private DatasetContext dc;
    private PreprocessContext pc;
    private ModelContext mc;

    /*
        TODO: I would really like a DI solution, would make things more testable
     */
    MiniMLContext() {
        //there is no config on launch, null object pattern
        super(StateFactory.INSTANCE.empty(), ContextFactory.INSTANCE.empty());

        //instantiate context for each panel
        dc = new DatasetContext(this);
        pc = new PreprocessContext(this);
        mc = new ModelContext(this);

        childContexts.add(dc);
        childContexts.add(pc);
        childContexts.add(mc);

        //note that the workflow is updated to something other than the nullstate within instantiation
        this.updateState();
    }

    //TODO: create and move to analise context
    public void run() {
        if(this.state.isReady())
        {
            //TODO: call run in Weka
            //might need a running workflow, complete workflow? Transition to running would occur here
        }
    }
}
