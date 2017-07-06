package frontendFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.AnalyzeContext;

public class AnalyzeTab extends Tab{

    public static final String TABNAME = "Analyze";

    private final AbstractCompositeContext parentContext;
    private final AnalyzeContext context;
    private final Stage mainStage;

    GridPane gridPane;

    public AnalyzeTab(Stage stage) {
        super();

        this.mainStage = stage;

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new AnalyzeContext(parentContext, Keys.AnalyzeConfig);

        Label tabLabel = new Label(TABNAME);
        this.setGraphic(tabLabel);

        gridPane = new GridPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        HBox runBox = new HBox();

        Label timeRemainingLabel = new Label("Time Remaining:");
        runBox.getChildren().add(timeRemainingLabel);

        Button runButton = new Button("Run");
        runBox.getChildren().add(runButton);

        gridPane.getChildren().add(runBox);

        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }

}
