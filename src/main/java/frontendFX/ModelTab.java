package frontendFX;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ModelContext;

public class ModelTab extends Tab {

    public static final String TABNAME = "Dataset";

    private final AbstractCompositeContext parentContext;
    private final ModelContext context;

    public ModelTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new ModelContext(parentContext, Keys.ModelConfig);

        Label tabLabel = new Label(TABNAME);
        this.setGraphic(tabLabel);

        GridPane gridPane = new GridPane();

        Label runtimeLabel = new Label("Maximum Runtime");
        GridPane.setConstraints(runtimeLabel, 0, 0);
        gridPane.getChildren().add(runtimeLabel);

        Slider runtimeSlider = new Slider();
        runtimeSlider.setMin(0.0);
        runtimeSlider.setMax(10080.0);
        runtimeSlider.setValue(10.0);
        runtimeSlider.setShowTickLabels(true);
        runtimeSlider.setShowTickMarks(true);
        runtimeSlider.setMajorTickUnit(10);
        runtimeSlider.setMinorTickCount(1);
        runtimeSlider.setBlockIncrement(1);
        GridPane.setConstraints(runtimeSlider, 1, 0, 4, 1);
        gridPane.getChildren().add(runtimeSlider);


        gridPane.setAlignment(Pos.CENTER);
        this.setContent(gridPane);
    }
}
