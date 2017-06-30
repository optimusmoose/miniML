package frontendFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;

public class ModelTabRuntimeBox extends HBox {

    private AbstractCompositeContext context;
    private ParameterContext estimatedRuntimeContext;

    public ModelTabRuntimeBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.estimatedRuntimeContext = new ParameterContext(this.context, Keys.EstimatedTimeConfig);
        ParameterContext.handleContext(estimatedRuntimeContext, 10);

        this.setPadding(new Insets(10, 10, 10, 10));

        Label runtimeLabel = new Label("Maximum Runtime: ");
        this.getChildren().add(runtimeLabel);

        Slider runtimeSlider = new Slider();
        runtimeSlider.setMin(0.0);
        runtimeSlider.setMax(1440.0);
        runtimeSlider.setValue(10.0);
        runtimeSlider.setShowTickLabels(true);
        runtimeSlider.setShowTickMarks(true);
        runtimeSlider.setMajorTickUnit(60);
        runtimeSlider.setMinorTickCount(1);
        runtimeSlider.setBlockIncrement(10);
        HBox.setHgrow(runtimeSlider, Priority.ALWAYS);
        this.getChildren().add(runtimeSlider);

        Label maximumRuntime = new Label("10 minutes");
        GridPane.setConstraints(maximumRuntime, 4, 0);
        GridPane.setHalignment(maximumRuntime, HPos.RIGHT);
        this.getChildren().add(maximumRuntime);

        runtimeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.EstimatedTimeConfig);
                    Double value = runtimeSlider.getValue();
                    maximumRuntime.setText(Double.toString(Math.floor(value)) + " minutes");
                    ParameterContext.handleContext((ParameterContext) context, value);
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });
    }
}
