package frontend;

import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ModelContext;

import javax.swing.*;
import java.awt.*;

public class ModelTab extends JPanel {

    private AbstractCompositeContext parentContext;
    private ModelContext context;

    public ModelTab(){
        super();

        parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        context = new ModelContext(parentContext, Keys.ModelConfig);

        this.setLayout(new GridLayout());
        this.setLayout(new GridLayout(6,0));

        JPanel etaPanel = new ModelTabEtaPanel();
        JPanel algorithmPanel = new ModelTabAlgorithmPanel();
        JPanel linearRegressionPanel = new ModelTabLrPanel();
        JPanel neuralNetPanel = new ModelTabNnPanel();
        JPanel supportVectorMachinePanel = new ModelTabSvmPanel();
        JPanel decisionTreePanel = new ModelTabDtPanel();

        this.add(etaPanel);
        this.add(algorithmPanel);
        this.add(linearRegressionPanel);
        this.add(neuralNetPanel);
        this.add(supportVectorMachinePanel);
        this.add(decisionTreePanel);
    }
}
