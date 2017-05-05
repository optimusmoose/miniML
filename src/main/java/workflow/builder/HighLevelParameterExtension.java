package workflow.builder;
import backend.Dispatcher;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.ParameterContext;

/**
 * This Extension pulls keys that determine high-level Dispatcher behavior-- requested run time,
 * which models to use, and TODO advanced configs.
 *
 * Please don't model specific parameters in here (e.g. # of layers for neural net)...
 */
public class HighLevelParameterExtension extends AbstractParameterExtension {
    public NoUserParameterDispatcherBuilder builder;
    public Dispatcher dispatcher;
    String useLinearRegression;
    String useNeuralNetwork;
    String useDecisionTree;
    String useSMO;
    String useETA;


    public HighLevelParameterExtension(NoUserParameterDispatcherBuilder builder, Dispatcher dispatcher){
        super(builder);
        this.dispatcher = dispatcher;
        MiniMLLogger.INSTANCE.info("in extension...");
        this.collect();
        MiniMLLogger.INSTANCE.info("got keys...");
        this.extend();
    }

    public void collect(){
        //LR
        ParameterContext lr = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleLinReg);
        this.useLinearRegression = lr.getValue().toString();
        //NN
        ParameterContext nn = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleNeuralNet);
        this.useNeuralNetwork = nn.getValue().toString();
        //DT
        ParameterContext dt = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleDecTree);
        this.useDecisionTree = dt.getValue().toString();
        //SMO
        ParameterContext sm = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleSuppVec);
        this.useSMO = sm.getValue().toString();
        //ETA
        ParameterContext et = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.EstimatedTimeConfig);
        this.useETA = et.getValue().toString();
    }

    public void extend(){
        MiniMLLogger.INSTANCE.info("Use Linear Regressions: " + this.useLinearRegression);
        MiniMLLogger.INSTANCE.info("Use Neural Network: " +this.useNeuralNetwork);
        MiniMLLogger.INSTANCE.info("Use SMO: " +this.useSMO);
        MiniMLLogger.INSTANCE.info("Use Decision Tree: " +this.useDecisionTree);
        MiniMLLogger.INSTANCE.info("Time for run: " +this.useETA);
        int time = Integer.parseInt(this.useETA);
        dispatcher.setTimeLimit(time);
        dispatcher.setAlgorithmUsage(this.useLinearRegression,
                                                  this.useNeuralNetwork,
                                                  this.useDecisionTree,
                                                  this.useSMO);
    }
}
