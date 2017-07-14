package frontendFX;

import javafx.concurrent.Task;
import utils.Logging.MiniMLLogger;
import workflow.builder.NoUserParameterDispatcherBuilder;

public class MiniMLTask extends Task{
    @Override
    protected Object call() throws Exception {
        MiniMLLogger.INSTANCE.info("Inside the job thread");
        //TODO: WIRE UP ACTUAL CONFIGS TO A JOB!
        NoUserParameterDispatcherBuilder job = new NoUserParameterDispatcherBuilder();
        job.launch();
        return null;
    }
}
