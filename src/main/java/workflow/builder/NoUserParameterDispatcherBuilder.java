package workflow.builder;

import backend.*;
import utils.Logging.MiniMLLogger;
import weka.core.Instances;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.ParameterContext;

import java.io.IOException;

public class NoUserParameterDispatcherBuilder extends AbstractDispatcherBuilder {
    HighLevelParameterExtension highLevelExtension;
    public Dispatcher dispatcher;

    public NoUserParameterDispatcherBuilder() {
        super();
        ParameterContext fileContext = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.DatasetFile);
        this.searchType = new randomSearch();
        this.linearRegressionParameters = new LinearRegressionParameters();
        this.neuralNetworkParameters = new NeuralNetworkParameters();
        this.decisionTreeParameters = new DecisionTreeParameters();
        this.smoParameters = new SmoParameters();
        this.dispatcher = new Dispatcher(maxThreads,
                                         neuralNetworkParameters,
                                         linearRegressionParameters,
                                         decisionTreeParameters,
                                         smoParameters,
                                         searchType);
    }

    public void launch(){
        this.getHighLevelExtension();
        this.dispatcher.launch();
    }

    public void getHighLevelExtension(){
        this.highLevelExtension = new HighLevelParameterExtension(this, dispatcher);
        this.highLevelExtension.collect();
        this.highLevelExtension.extend();
    }


}
