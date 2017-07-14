package frontendFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Logging.MiniMLLogger;
import weka.core.Instances;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.DatasetContext;
import workflow.context.InstanceContext;
import workflow.context.ParameterContext;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DatasetTab extends Tab {

    private final Stage mainStage;

    public static final String ICON_PATH = "img/miniml_icon.png";
    public static final String TABNAME = "Dataset";

    private final AbstractCompositeContext parentContext;
    private final DatasetContext context;

    private InstanceContext wekaInstance;
    ParameterContext selectedAttributes;
    ParameterContext classifier;

    private String dataset = "";
    private String content = "";

    TextField pathField;
    ComboBox<String> classSelector;
    TextArea previewArea;
    ListView<String> attributesList;

    public DatasetTab(Stage stage) {
        super();

        this.mainStage = stage;

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new DatasetContext(parentContext, Keys.DatasetConfig);

        this.wekaInstance = new InstanceContext(parentContext, Keys.RootWekaInstnace);
        this.selectedAttributes = new ParameterContext(this.context, Keys.SelectedAttributes);
        this.classifier = new ParameterContext(this.context, Keys.SelectedClassifier);

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

        pathField = new TextField();
        pathField.setEditable(false);
        HBox.setHgrow(pathBox, Priority.ALWAYS);
        pathBox.getChildren().add(pathField);

        Button findPathButton = new Button("Browse");
        pathBox.getChildren().add(findPathButton);

        //add the path components
        GridPane.setConstraints(pathBox, 0, 0, 2, 1);
        GridPane.setHgrow(pathBox, Priority.ALWAYS);
        gridPane.getChildren().add(pathBox);

        classSelector = new ComboBox<>();
        classSelector.setMaxWidth(Double.MAX_VALUE);
        GridPane.setConstraints(classSelector, 2, 0);
        gridPane.getChildren().add(classSelector);

        previewArea = new TextArea();
        previewArea.setEditable(false);
        GridPane.setConstraints(previewArea, 0, 1, 2, 2);
        gridPane.getChildren().add(previewArea);

        attributesList = new ListView<>();
        attributesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        attributesList.setMaxHeight(Double.MAX_VALUE);
        GridPane.setConstraints(attributesList, 2, 1);
        gridPane.getChildren().add(attributesList);

        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);

        findPathButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectFile();
            }
        });

        //TODO: even listeners to set context for class and attributes!
        classSelector.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            private AbstractCompositeContext context;

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                context = WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedClassifier);
                ParameterContext.handleContext((ParameterContext) context, newValue);
            }
        });

        attributesList.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            private AbstractCompositeContext context;

            @Override
            public void onChanged(Change<? extends Integer> c) {
                context = WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedAttributes);
                ParameterContext.handleContext((ParameterContext) context, c.getList());
            }
        });
    }

    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Dataset File");

        //open in user home dir
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        fileChooser.setInitialDirectory(userDirectory);

        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if (selectedFile != null) {
            this.dataset = selectedFile.getAbsolutePath();
            this.pathField.setText(this.dataset);

            this.previewData(selectedFile);
            this.loadWekaInstance(this.dataset);
        }
    }

    // preview the first 250 lines of the file
    private void previewData(File file) {
        String line;
        int lines = 0;
        int linesToRead = 250; //TODO: extract to a config
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while(((line = reader.readLine()) != null) && lines < linesToRead) {
                this.content += line + '\n';
                lines++;
            }
            reader.close();
        } catch (IOException exception) {
            MiniMLLogger.INSTANCE.exception(exception);
        }

        previewArea.setText(this.content);
    }

    // load the weka instance and populate attributes and calsses
    private void loadWekaInstance(String path) {
        try {
            this.wekaInstance.loadInstanceFromPath(path);
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }

        this.classSelector.getItems().clear();
        this.attributesList.getItems().clear();
        Instances data = (Instances) this.wekaInstance.getValue();
        for (int i = 0; i < data.numAttributes(); i++)
        {
            String name = data.attribute(i).name();
            this.classSelector.getItems().add(i, name);
            this.attributesList.getItems().add(name);
        }
    }

}
