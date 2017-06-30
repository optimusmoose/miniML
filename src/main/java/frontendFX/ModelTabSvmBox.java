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

public class ModelTabSvmBox extends HBox {

    private AbstractCompositeContext context;
    private ParameterContext gammaContext;
    private ParameterContext epsilonContext;
    private ParameterContext degreeContext;
    private ParameterContext nuContext;

    public ModelTabSvmBox() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.gammaContext = new ParameterContext(this.context, Keys.Gamma);
        this.epsilonContext = new ParameterContext(this.context, Keys.Epsilon);
        this.degreeContext = new ParameterContext(this.context, Keys.Degree);
        this.nuContext = new ParameterContext(this.context, Keys.Nu);

        this.setPadding(new Insets(10, 10, 10, 10));

        Label gamaLabel = new Label("Gama: ");
        this.getChildren().add(gamaLabel);

        Slider gamaSlider = new Slider();
        this.getChildren().add(gamaSlider);

        Label epsilonLabel = new Label("Epsilon: ");
        this.getChildren().add(epsilonLabel);

        Slider epsilonSlider = new Slider();
        this.getChildren().add(epsilonSlider);

        Label degreeLabel = new Label("Degree: ");
        this.getChildren().add(degreeLabel);

        Slider degreeSlider = new Slider();
        this.getChildren().add(degreeSlider);

        Label nuLabel = new Label("Nu: ");
        this.getChildren().add(nuLabel);

        Slider nuSlider = new Slider();
        this.getChildren().add(nuSlider);

        gamaSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Gamma);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        epsilonSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Epsilon);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        degreeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Degree);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        nuSlider.valueProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Nu);
                    ParameterContext.handleContext((ParameterContext) context, newValue);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

    }
}
