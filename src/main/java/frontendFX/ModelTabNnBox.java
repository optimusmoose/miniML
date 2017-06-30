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

public class ModelTabNnBox extends HBox {
    private AbstractCompositeContext context;
    private ParameterContext hiddenLayerContext;
    private ParameterContext hiddenNodesContext;
    private ParameterContext learnRateContext;
    private ParameterContext epochContext;
    
    public ModelTabNnBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.hiddenLayerContext = new ParameterContext(this.context, Keys.NumHiddenLayers);
        this.hiddenNodesContext = new ParameterContext(this.context, Keys.NumHiddenNodes);
        this.learnRateContext = new ParameterContext(this.context, Keys.LearnRate);
        this.epochContext = new ParameterContext(this.context, Keys.NumEpochs);

        this.setPadding(new Insets(10, 10, 10, 10));

        Label hiddenLayersLabel = new Label("Hidden Layers: ");
        this.getChildren().add(hiddenLayersLabel);

        Slider hiddenLayersSlider = new Slider();
        this.getChildren().add(hiddenLayersSlider);

        Label hiddenNodesLabel = new Label("Hidden Nodes: ");
        this.getChildren().add(hiddenNodesLabel);

        Slider hiddenNodesSlider = new Slider();
        this.getChildren().add(hiddenNodesSlider);

        Label learnRateLabel = new Label("Learning Rate: ");
        this.getChildren().add(learnRateLabel);

        Slider learningRateSlider = new Slider();
        this.getChildren().add(learningRateSlider);

        Label epochsLabel = new Label("Epochs: ");
        this.getChildren().add(epochsLabel);

        Slider epochsSlider = new Slider();
        this.getChildren().add(epochsSlider);

        hiddenLayersSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenLayers);
                    ParameterContext.handleContext((ParameterContext) context, hiddenLayersSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        hiddenNodesSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenNodes);
                    ParameterContext.handleContext((ParameterContext) context, hiddenNodesSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        learningRateSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.LearnRate);
                    ParameterContext.handleContext((ParameterContext) context, learningRateSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        epochsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumEpochs);
                    ParameterContext.handleContext((ParameterContext) context, epochsSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });
    }
}
