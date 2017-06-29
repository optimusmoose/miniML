package frontendFX;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ModelContext;

public class ModelTab extends Tab {

    public static final String TABNAME = "Model";

    private final AbstractCompositeContext parentContext;
    private final ModelContext context;

    GridPane gridPane;

    public ModelTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new ModelContext(parentContext, Keys.ModelConfig);

        Label tabLabel = new Label(TABNAME);
        this.setGraphic(tabLabel);

        gridPane = new GridPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        runtimeRow();
        algorithmRow();
        linearRegressionRow();
        neuralNetworkRow();
        supportVectorMachineRow();
        decisionTreeRow();


        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }

    private void runtimeRow() {
        Label runtimeLabel = new Label("Maximum Runtime");
        GridPane.setConstraints(runtimeLabel, 0, 0);
        gridPane.getChildren().add(runtimeLabel);

        Slider runtimeSlider = new Slider();
        runtimeSlider.setMin(0.0);
        runtimeSlider.setMax(1440.0);
        runtimeSlider.setValue(10.0);
        runtimeSlider.setShowTickLabels(true);
        runtimeSlider.setShowTickMarks(true);
        runtimeSlider.setMajorTickUnit(60);
        runtimeSlider.setMinorTickCount(1);
        runtimeSlider.setBlockIncrement(10);
        GridPane.setConstraints(runtimeSlider, 1, 0, 3, 1);
        GridPane.setFillWidth(runtimeSlider, true);
        gridPane.getChildren().add(runtimeSlider);

        Label maximumRuntime = new Label("10 minutes");
        GridPane.setConstraints(maximumRuntime, 4, 0);
        GridPane.setHalignment(maximumRuntime, HPos.RIGHT);
        gridPane.getChildren().add(maximumRuntime);
    }

    private void algorithmRow() {
        Label algorithmToggleLable = new Label("Algorithms");
        GridPane.setConstraints(algorithmToggleLable, 0, 1);
        gridPane.getChildren().add(algorithmToggleLable);

        Button linearRegressionButton = new Button("Linear Regression");
        GridPane.setConstraints(linearRegressionButton, 1, 1);
        gridPane.getChildren().add(linearRegressionButton);

        Button neuralNetworkButton = new Button("Neural Network");
        GridPane.setConstraints(neuralNetworkButton, 2, 1);
        gridPane.getChildren().add(neuralNetworkButton);

        Button supportVectorMachineButton = new Button("Support Vector Machine");
        GridPane.setConstraints(supportVectorMachineButton, 3, 1);
        gridPane.getChildren().add(supportVectorMachineButton);

        Button decisionTreeButton = new Button("Decision Tree");
        GridPane.setConstraints(decisionTreeButton, 4, 1);
        gridPane.getChildren().add(decisionTreeButton);
    }

    private void linearRegressionRow() {
        Label linearRegressionLabel = new Label("Linear Regression");
        GridPane.setConstraints(linearRegressionLabel, 0, 2, 1, 2);
        gridPane.getChildren().add(linearRegressionLabel);

        Label methodLabel = new Label("Method:");
        GridPane.setConstraints(methodLabel, 1, 2);
        GridPane.setHalignment(methodLabel, HPos.RIGHT);
        gridPane.getChildren().add(methodLabel);

        ComboBox<String> methodSelect = new ComboBox<>();
        methodSelect.setMaxWidth(Double.MAX_VALUE);
        GridPane.setConstraints(methodSelect, 2, 2);
        gridPane.getChildren().add(methodSelect);


        Label ridgeLabel = new Label("Ridge:");
        GridPane.setConstraints(ridgeLabel, 3, 2);
        GridPane.setHalignment(ridgeLabel, HPos.RIGHT);
        gridPane.getChildren().add(ridgeLabel);

        Slider ridgeSlider = new Slider();
        GridPane.setConstraints(ridgeSlider, 4, 2);
        gridPane.getChildren().add(ridgeSlider);
    }

    private void neuralNetworkRow() {

    }

    private void supportVectorMachineRow() {

    }

    private void decisionTreeRow() {

    }
}
