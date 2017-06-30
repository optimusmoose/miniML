package frontendFX;

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

        Label runtimeLabel = new Label("Runtime");
        GridPane.setConstraints(runtimeLabel, 0, 0);
        gridPane.getChildren().add(runtimeLabel);

        ModelTabRuntimeBox runtimeBox = new ModelTabRuntimeBox();
        GridPane.setConstraints(runtimeBox, 1, 0, 4, 1);
        gridPane.getChildren().add(runtimeBox);

        Label algorithmToggleLable = new Label("Algorithms");
        GridPane.setConstraints(algorithmToggleLable, 0, 1);
        gridPane.getChildren().add(algorithmToggleLable);

        ModelTabAlgorithmBox algorithmBox = new ModelTabAlgorithmBox();
        GridPane.setConstraints(algorithmBox, 1,1, 4, 1);
        gridPane.getChildren().add(algorithmBox);

        Label linearRegressionLabel = new Label("Linear Regression");
        GridPane.setConstraints(linearRegressionLabel, 0, 2, 1, 1);
        gridPane.getChildren().add(linearRegressionLabel);

        ModelTabLrBox linearRegressBox = new ModelTabLrBox();
        GridPane.setConstraints(linearRegressBox, 1, 2, 4, 1);
        gridPane.getChildren().add(linearRegressBox);

        Label neuralNetworkLabel = new Label("Neural Network");
        GridPane.setConstraints(neuralNetworkLabel, 0, 3, 1, 1);
        gridPane.getChildren().add(neuralNetworkLabel);

        ModelTabNnBox neuralNetworkBox = new ModelTabNnBox();
        GridPane.setConstraints(neuralNetworkBox, 1, 3, 4, 1);
        gridPane.getChildren().add(neuralNetworkBox);

        Label supportVectorMachineLabel = new Label("Support Vector Machine");
        GridPane.setConstraints(supportVectorMachineLabel, 0, 4, 1, 1);
        gridPane.getChildren().add(supportVectorMachineLabel);

        ModelTabSvmBox supportVectorMachineBox = new ModelTabSvmBox();
        GridPane.setConstraints(supportVectorMachineBox, 1, 4, 4, 1);
        gridPane.getChildren().add(supportVectorMachineBox);

        Label decisionTreeLabel = new Label("Descision Tree");
        GridPane.setConstraints(decisionTreeLabel, 0, 5, 1, 1);
        gridPane.getChildren().add(decisionTreeLabel);

        ModelTabDtBox descisionTreeBox = new ModelTabDtBox();
        GridPane.setConstraints(descisionTreeBox, 1, 5, 4, 1);
        gridPane.getChildren().add(descisionTreeBox);

        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }
}
