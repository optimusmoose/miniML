package frontendFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.DatasetContext;

public class DatasetTab extends Tab {

    public static final String ICON_PATH = "img/miniml_icon.png";
    public static final String TABNAME = "Dataset";

    private final AbstractCompositeContext parentContext;
    private final DatasetContext context;

    public DatasetTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new DatasetContext(parentContext, Keys.DatasetConfig);

//        URL iconPath = this.getClass().getResource(ICON_PATH);
//        InputStream iconStream = this.getClass().getResourceAsStream(ICON_PATH); #TODO: returning null
//        Image image = new Image(iconStream);

        Label tabLabel = new Label(TABNAME);
//        tabLabel.setGraphic(new ImageView(image));
        this.setGraphic(tabLabel);

        GridPane gridPane = new GridPane();

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        HBox pathBox = new HBox();
        pathBox.setPadding(new Insets(10, 10, 10, 10));

        Label pathLabel = new Label("Path to Dataset:\t");
        pathBox.getChildren().add(pathLabel);

        TextField pathField = new TextField();
        pathBox.getChildren().add(pathField);

        Button findPathButton = new Button("Browse");
        pathBox.getChildren().add(findPathButton);

        //add the path components
        GridPane.setConstraints(pathBox, 0, 0, 2, 1);
        gridPane.getChildren().add(pathBox);

        ComboBox<String> classSelector = new ComboBox<>();
        classSelector.setMaxWidth(Double.MAX_VALUE);
        GridPane.setConstraints(classSelector, 2, 0);
        gridPane.getChildren().add(classSelector);



        TextArea previewArea = new TextArea();
        previewArea.setEditable(false);
        GridPane.setConstraints(previewArea, 0, 1, 2, 2);
        gridPane.getChildren().add(previewArea);

        ListView<String> attributesList = new ListView<>();
        attributesList.setMaxHeight(Double.MAX_VALUE);
        GridPane.setConstraints(attributesList, 2, 1);
        gridPane.getChildren().add(attributesList);




        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }

}
