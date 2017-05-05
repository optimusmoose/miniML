package frontend;

import workflow.builder.NoUserParameterDispatcherBuilder;

import javax.swing.*;
import java.util.List;

public class MiniMLWorker extends SwingWorker<List<Object>, Void> {
    @Override
    protected List<Object> doInBackground() throws Exception {
        NoUserParameterDispatcherBuilder job = new NoUserParameterDispatcherBuilder();
        job.launch();
        return null;
    }
}
