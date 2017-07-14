package frontendFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.Logging.MiniMLLogger;
import utils.Logging.TextAreaAppender;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.AnalyzeContext;
import workflow.context.AnalyzeLaunchContext;

public class AnalyzeTab extends Tab{

    public static final String TABNAME = "Analyze";

    private final AbstractCompositeContext parentContext;
    private final AnalyzeContext context;
    private final AnalyzeLaunchContext analysisLaunchContext;
    private final Stage mainStage;

    private TextAreaAppender outputAppender;

    GridPane gridPane;

    public AnalyzeTab(Stage stage) {
        super();

        this.mainStage = stage;

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new AnalyzeContext(parentContext, Keys.AnalyzeConfig);
        this.analysisLaunchContext = new AnalyzeLaunchContext(this.context, Keys.StartAnalysisButton);

        Label tabLabel = new Label(TABNAME);
        this.setGraphic(tabLabel);

        gridPane = new GridPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        TextArea consoleText = new TextArea();
        GridPane.setConstraints(consoleText, 0, 0, 5, 5);
        gridPane.getChildren().add(consoleText);

        HBox runBox = new HBox();

        Label timeRemainingLabel = new Label("Time Remaining:");
        runBox.getChildren().add(timeRemainingLabel);

        Button runButton = new Button("Run");
        runBox.getChildren().add(runButton);

        GridPane.setConstraints(runBox, 0, 5, 5, 1);
        gridPane.getChildren().add(runBox);

        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);

        this.addAppender(consoleText);

        runButton.setOnAction(new EventHandler<ActionEvent>() {
            private AnalyzeLaunchContext context;

            @Override
            public void handle(ActionEvent event) {
                context = (AnalyzeLaunchContext) WorkflowManager.INSTANCE.getContextByKey(Keys.StartAnalysisButton);
                context.execute();
            }
        });
    }

    private void addAppender(TextArea textArea) {
        try {
            this.outputAppender = new TextAreaAppender(textArea);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
    }

}
