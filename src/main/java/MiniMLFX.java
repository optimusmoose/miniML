import frontendFX.DatasetTab;
import frontendFX.ModelTab;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import utils.Logging.MiniMLLogger;

import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.MiniMLContext;


public class MiniMLFX extends Application{

    private static String APPNAME = "MiniML";

    public static void main(String args[]) {
        MiniMLLogger.INSTANCE.info("Starting " + APPNAME + ".");

        try {
            launch(args);
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.debug("Failed to load the MiniML FX GUI.");
            MiniMLLogger.INSTANCE.exception(e);
            System.exit(1);
        }

    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600, Color.GREY);
        stage.setTitle(APPNAME);

        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setSide(Side.LEFT);
        tabPane.setTabMinWidth(50);
        tabPane.setTabMaxWidth(50);
        tabPane.setTabMinHeight(150);
        tabPane.setTabMaxHeight(150);

        DatasetTab datasetTab = new DatasetTab();
        tabPane.getTabs().add(datasetTab);

        ModelTab modelTab = new ModelTab();
        tabPane.getTabs().add(modelTab);

        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);


        stage.setScene(scene);
        stage.show();

        MiniMLContext context = new MiniMLContext(Keys.App);
        WorkflowManager.INSTANCE.registerContext(context);

        MiniMLLogger.INSTANCE.info(APPNAME + " is Ready.");
    }


}
