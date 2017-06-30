package frontendFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;

public class ModelTabAlgorithmBox extends HBox {

    private AbstractCompositeContext context;
    private ParameterContext toggleLinRegContext;
    private ParameterContext toggleNeuralNetContext;
    private ParameterContext toggleSuppVecContext;
    private ParameterContext toggleDecTreeContext;

    public ModelTabAlgorithmBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.toggleLinRegContext = new ParameterContext(this.context, Keys.ToggleLinReg);
        this.toggleNeuralNetContext = new ParameterContext(this.context, Keys.ToggleNeuralNet);
        this.toggleSuppVecContext = new ParameterContext(this.context, Keys.ToggleSuppVec);
        this.toggleDecTreeContext = new ParameterContext(this.context, Keys.ToggleDecTree);

        ParameterContext.handleContext(toggleLinRegContext, false);
        ParameterContext.handleContext(toggleNeuralNetContext, false);
        ParameterContext.handleContext(toggleDecTreeContext, false);
        ParameterContext.handleContext(toggleSuppVecContext, false);

        this.setPadding(new Insets(10, 10, 10, 10));

        ToggleButton linearRegressionButton = new ToggleButton("Linear Regression");
        this.getChildren().add(linearRegressionButton);

        ToggleButton neuralNetworkButton = new ToggleButton("Neural Network");
        this.getChildren().add(neuralNetworkButton);

        ToggleButton supportVectorMachineButton = new ToggleButton("Support Vector Machine");
        this.getChildren().add(supportVectorMachineButton);

        ToggleButton decisionTreeButton = new ToggleButton("Decision Tree");
        this.getChildren().add(decisionTreeButton);

        linearRegressionButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            private AbstractCompositeContext context;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleLinReg);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        neuralNetworkButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            private AbstractCompositeContext context;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleNeuralNet);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        supportVectorMachineButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            private AbstractCompositeContext context;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleSuppVec);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        decisionTreeButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            private AbstractCompositeContext context;
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleDecTree);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });
    }
}
