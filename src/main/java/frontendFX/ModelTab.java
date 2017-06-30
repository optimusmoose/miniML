package frontendFX;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        ToggleButton linearRegressionButton = new ToggleButton("Linear Regression");
        GridPane.setConstraints(linearRegressionButton, 1, 1);
        gridPane.getChildren().add(linearRegressionButton);

        ToggleButton neuralNetworkButton = new ToggleButton("Neural Network");
        GridPane.setConstraints(neuralNetworkButton, 2, 1);
        gridPane.getChildren().add(neuralNetworkButton);

        ToggleButton supportVectorMachineButton = new ToggleButton("Support Vector Machine");
        GridPane.setConstraints(supportVectorMachineButton, 3, 1);
        gridPane.getChildren().add(supportVectorMachineButton);

        ToggleButton decisionTreeButton = new ToggleButton("Decision Tree");
        GridPane.setConstraints(decisionTreeButton, 4, 1);
        gridPane.getChildren().add(decisionTreeButton);
    }

    private void linearRegressionRow() {
        Label linearRegressionLabel = new Label("Linear Regression");
        GridPane.setConstraints(linearRegressionLabel, 0, 2, 1, 1);
        gridPane.getChildren().add(linearRegressionLabel);

        HBox linearRegressBox = new HBox();
        linearRegressBox.setPadding(new Insets(10, 10, 10, 10));

        Label methodLabel = new Label("Method: ");
        linearRegressBox.getChildren().add(methodLabel);

        ComboBox<String> methodSelect = new ComboBox<>();
        linearRegressBox.getChildren().add(methodSelect);


        Label ridgeLabel = new Label("Ridge: ");
        linearRegressBox.getChildren().add(ridgeLabel);

        Slider ridgeSlider = new Slider();
        GridPane.setConstraints(ridgeSlider, 4, 2);
        linearRegressBox.getChildren().add(ridgeSlider);

        GridPane.setConstraints(linearRegressBox, 1, 2, 4, 1);
        gridPane.getChildren().add(linearRegressBox);
    }

    private void neuralNetworkRow() {
        Label neuralNetworkLabel = new Label("Neural Network");
        GridPane.setConstraints(neuralNetworkLabel, 0, 3, 1, 1);
        gridPane.getChildren().add(neuralNetworkLabel);

        HBox neuralNetworkBox = new HBox();
        neuralNetworkBox.setPadding(new Insets(10, 10, 10, 10));

        Label hiddenLayersLabel = new Label("Hidden Layers: ");
        neuralNetworkBox.getChildren().add(hiddenLayersLabel);

        Slider hiddenLayersSlider = new Slider();
        neuralNetworkBox.getChildren().add(hiddenLayersSlider);

        Label hiddenNodesLabel = new Label("Hidden Nodes: ");
        neuralNetworkBox.getChildren().add(hiddenNodesLabel);

        Slider hiddenNodesSlider = new Slider();
        neuralNetworkBox.getChildren().add(hiddenNodesSlider);

        Label learnRateLabel = new Label("Learning Rate: ");
        neuralNetworkBox.getChildren().add(learnRateLabel);

        Slider learningRateSlider = new Slider();
        neuralNetworkBox.getChildren().add(learningRateSlider);

        Label epochsLabel = new Label("Epochs: ");
        neuralNetworkBox.getChildren().add(epochsLabel);

        Slider epochsSlider = new Slider();
        neuralNetworkBox.getChildren().add(epochsSlider);

        GridPane.setConstraints(neuralNetworkBox, 1, 3, 4, 1);
        gridPane.getChildren().add(neuralNetworkBox);
    }

    private void supportVectorMachineRow() {
        Label supportVectorMachineLabel = new Label("Support Vector Machine");
        GridPane.setConstraints(supportVectorMachineLabel, 0, 4, 1, 1);
        gridPane.getChildren().add(supportVectorMachineLabel);

        HBox supportVectorMachineBox = new HBox();
        supportVectorMachineBox.setPadding(new Insets(10, 10, 10, 10));

        Label gamaLabel = new Label("Gama: ");
        supportVectorMachineBox.getChildren().add(gamaLabel);

        Slider gamaSlider = new Slider();
        supportVectorMachineBox.getChildren().add(gamaSlider);

        Label epsilonLabel = new Label("Epsilon: ");
        supportVectorMachineBox.getChildren().add(epsilonLabel);

        Slider epsilonSlider = new Slider();
        supportVectorMachineBox.getChildren().add(epsilonSlider);

        Label degreeLabel = new Label("Degree: ");
        supportVectorMachineBox.getChildren().add(degreeLabel);

        Slider degreeSlider = new Slider();
        supportVectorMachineBox.getChildren().add(degreeSlider);

        Label nuLabel = new Label("Nu: ");
        supportVectorMachineBox.getChildren().add(nuLabel);

        Slider nuSlider = new Slider();
        supportVectorMachineBox.getChildren().add(nuSlider);

        GridPane.setConstraints(supportVectorMachineBox, 1, 4, 4, 1);
        gridPane.getChildren().add(supportVectorMachineBox);
    }

    private void decisionTreeRow() {
        Label decisionTreeLabel = new Label("Descision Tree");
        GridPane.setConstraints(decisionTreeLabel, 0, 5, 1, 1);
        gridPane.getChildren().add(decisionTreeLabel);

        HBox descisionTreeBox = new HBox();
        descisionTreeBox.setPadding(new Insets(10, 10, 10, 10));

        Label pruningConfidenceLabel = new Label("Pruning Confidence: ");
        descisionTreeBox.getChildren().add(pruningConfidenceLabel);

        Slider pruningConfidenceSlider = new Slider();
        descisionTreeBox.getChildren().add(pruningConfidenceSlider);

        Label pruningFoldsLabel = new Label("Pruning Folds: ");
        descisionTreeBox.getChildren().add(pruningFoldsLabel);

        Slider pruningFoldsSlider = new Slider();
        descisionTreeBox.getChildren().add(pruningFoldsSlider);

        Label instancesLabel = new Label("Instances: ");
        descisionTreeBox.getChildren().add(instancesLabel);

        Slider instancesSlider = new Slider();
        descisionTreeBox.getChildren().add(instancesSlider);

        GridPane.setConstraints(descisionTreeBox, 1, 5, 4, 1);
        gridPane.getChildren().add(descisionTreeBox);
    }
}
