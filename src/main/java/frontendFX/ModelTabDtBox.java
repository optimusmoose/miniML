package frontendFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;

public class ModelTabDtBox extends HBox {
    
    private AbstractCompositeContext context;
    private ParameterContext pruningConfidenceContext;
    private ParameterContext pruningFoldsContext;
    private ParameterContext decisionTreeInstancesContext;
    
    public ModelTabDtBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.pruningConfidenceContext = new ParameterContext(this.context, Keys.PruningConfidence);
        this.pruningFoldsContext = new ParameterContext(this.context, Keys.PruningFolds);
        this.decisionTreeInstancesContext = new ParameterContext(this.context, Keys.DecisionTreeInstances);

        this.setPadding(new Insets(10, 10, 10, 10));

        Label pruningConfidenceLabel = new Label("Pruning Confidence: ");
        this.getChildren().add(pruningConfidenceLabel);

        Slider pruningConfidenceSlider = new Slider();
        this.getChildren().add(pruningConfidenceSlider);

        Label pruningFoldsLabel = new Label("Pruning Folds: ");
        this.getChildren().add(pruningFoldsLabel);

        Slider pruningFoldsSlider = new Slider();
        this.getChildren().add(pruningFoldsSlider);

        Label instancesLabel = new Label("Instances: ");
        this.getChildren().add(instancesLabel);

        Slider instancesSlider = new Slider();
        this.getChildren().add(instancesSlider);

        pruningConfidenceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.PruningConfidence);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        pruningFoldsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.PruningFolds);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        instancesSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.DecisionTreeInstances);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });
        
    }
}
