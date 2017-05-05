package workflow.builder;

import backend.*;
import weka.core.Instances;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.ParameterContext;

import java.io.IOException;

public class NoUserParameterDispatcherBuilder extends AbstractDispatcherBuilder {

    public NoUserParameterDispatcherBuilder() {
        super();
        ParameterContext fileContext = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.DatasetFile);
        try {
            this.data = this.read_data((String) fileContext.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.searchType = new randomSearch();
        this.data.setClassIndex(this.data.numAttributes() - 1);
        this.linearRegressionParameters = new LinearRegressionParameters();
        this.neuralNetworkParameters = new NeuralNetworkParameters();
        this.decisionTreeParameters = new DecisionTreeParameters();
        this.smoParameters = new SmoParameters();
        this.dispatcher = new Dispatcher(data,
                                         maxThreads,
                                         neuralNetworkParameters,
                                         linearRegressionParameters,
                                         decisionTreeParameters,
                                         smoParameters,
                                         searchType);
    }

    public void launch(){
        this.dispatcher.setTimeLimit(1); //TODO
        this.dispatcher.launch();
    }


}
