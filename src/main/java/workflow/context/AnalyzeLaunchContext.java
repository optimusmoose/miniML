package workflow.context;

import workflow.builder.NoUserParameterDispatcherBuilder;

/**
 * The ImperativeContext that handles launching the analysis.
 *
 */
public class AnalyzeLaunchContext extends ImperativeContext {
    protected AbstractCompositeContext parentContext;

    public AnalyzeLaunchContext(AbstractCompositeContext parentContext, String key) {
        super(parentContext, key);
    }

    /**
     * Launch the analysis.
     *
     * Right now, the run sort of 'floats' since it isn't actually connected to anything. This is probably
     * a really bad idea and should be fixed the instant someone has a better idea of what we're trying to
     * do here.
     *
     * Launches a Builder that pulls together the environment for a Dispatcher and then sets it in motion.
     */
    public void execute(){
        if(this.verifyParentIsReady()){
            //TODO do something.
            this.log("Parent Ready. Executing workflow.");
            NoUserParameterDispatcherBuilder run = new NoUserParameterDispatcherBuilder();
            run.launch();
        } else {
            this.log("Parent not ready. Check settings.");
        }

    }
}
