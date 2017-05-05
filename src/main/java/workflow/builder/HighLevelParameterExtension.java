package workflow.builder;
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
    protected AbstractDispatcherBuilder builder;
    String useLinearRegression;
    String useNeuralNetwork;
    String useDecisionTree;
    String useSMO;
    String useETA;


    public HighLevelParameterExtension(AbstractDispatcherBuilder builder){
        super(builder);
        this.collect();
    }

    public void collect(){
        //this.useLinearRegression = Keys.ToggleLinReg;
        ParameterContext lr = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleLinReg);
        this.useLinearRegression = lr.getValue().toString();
        //this.useNeuralNetwork = Keys.ToggleNeuralNet;
        ParameterContext nn = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleNeuralNet);
        this.useNeuralNetwork = nn.getValue().toString();
        ParameterContext dt = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleDecTree);
        this.useDecisionTree = dt.getValue().toString();
        //this.useSMO = Keys.ToggleSuppVec;
        ParameterContext sm = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleSuppVec);
        this.useSMO = sm.getValue().toString();
        //this.useETA = Keys.EstimatedTimeConfig;
        ParameterContext et = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.EstimatedTimeConfig);
        this.useETA = et.getValue().toString();
    }

    public void extend(){
        MiniMLLogger.INSTANCE.info("Here is some extension data:");
        MiniMLLogger.INSTANCE.info(this.useLinearRegression);
        MiniMLLogger.INSTANCE.info(this.useNeuralNetwork);
        MiniMLLogger.INSTANCE.info(this.useSMO);
        MiniMLLogger.INSTANCE.info(this.useDecisionTree);
        MiniMLLogger.INSTANCE.info(this.useETA);
    }
}
