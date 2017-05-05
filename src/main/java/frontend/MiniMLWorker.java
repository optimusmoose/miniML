package frontend;

import utils.Logging.MiniMLLogger;
import workflow.builder.NoUserParameterDispatcherBuilder;

import javax.swing.*;
import java.util.List;

public class MiniMLWorker extends SwingWorker<List<Object>, Void> {
    @Override
    protected List<Object> doInBackground() throws Exception {
        MiniMLLogger.INSTANCE.info("Inside the job thread");
        NoUserParameterDispatcherBuilder job = new NoUserParameterDispatcherBuilder();
        job.launch();
        return null;
    }
}
