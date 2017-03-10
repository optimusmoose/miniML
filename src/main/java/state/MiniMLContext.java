package state;

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
        super(StateFactory.INSTANCE.empty());

        //instantiate context for each panel
        dc = new DatasetContext();
        pc = new PreprocessContext();
        mc = new ModelContext();

        childContexts.add(dc);
        childContexts.add(pc);
        childContexts.add(mc);

        //note that the state is updated to something other than the nullstate within instantiation
        this.updateState();
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
