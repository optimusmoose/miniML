package frontendFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;

public class ModelTabLrBox extends HBox {

    private AbstractCompositeContext context;
    private ParameterContext methodSelectContext;
    private ParameterContext ridgeContext;

    public ModelTabLrBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.methodSelectContext = new ParameterContext(this.context, Keys.MethodSelect);
        this.ridgeContext = new ParameterContext(this.context, Keys.Ridge);

        this.setPadding(new Insets(10, 10, 10, 10));

        Label methodLabel = new Label("Method: ");
        this.getChildren().add(methodLabel);

        ComboBox<String> methodSelect = new ComboBox<>();
        this.getChildren().add(methodSelect);


        Label ridgeLabel = new Label("Ridge: ");
        this.getChildren().add(ridgeLabel);

        Slider ridgeSlider = new Slider();
        GridPane.setConstraints(ridgeSlider, 4, 2);
        this.getChildren().add(ridgeSlider);

        methodSelect.valueProperty().addListener(new ChangeListener<String>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.MethodSelect);
                    ParameterContext.handleContext((ParameterContext) context, methodSelect.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        ridgeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Ridge);
                    ParameterContext.handleContext((ParameterContext) context, ridgeSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });
    }
}
