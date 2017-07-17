package frontendFX;

import javafx.concurrent.Task;
import utils.Logging.MiniMLLogger;
import workflow.builder.NoUserParameterDispatcherBuilder;

public class MiniMLTask extends Task{
    @Override
    protected Object call() throws Exception {
        //TODO: WIRE UP ACTUAL CONFIGS TO A JOB!
        try {//TODO: note this try catch will halt all work on an unhandled exception....
            NoUserParameterDispatcherBuilder job = new NoUserParameterDispatcherBuilder();
            job.launch();
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.error("Exception in thread...");
            MiniMLLogger.INSTANCE.exception(e);
        }

        return null;
    }
}
