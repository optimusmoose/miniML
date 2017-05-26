package frontend;

import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ModelTabDtPanel extends JPanel {

    private AbstractCompositeContext context;
    private ParameterContext pruningConfidenceContext;
    private ParameterContext pruningFoldsContext;
    private ParameterContext decisionTreeInstancesContext;

    public ModelTabDtPanel() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.pruningConfidenceContext = new ParameterContext(this.context, Keys.PruningConfidence);
        this.pruningFoldsContext = new ParameterContext(this.context, Keys.PruningFolds);
        this.decisionTreeInstancesContext = new ParameterContext(this.context, Keys.DecisionTreeInstances);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Decision Tree: ");
        JLabel par1 = new JLabel("Pruning Confidence: ");
        JLabel par2 = new JLabel("Pruning Folds: ");
        JLabel par3 = new JLabel("Instances: ");
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);

        slider1.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.PruningConfidence);
                    ParameterContext.handleContext((ParameterContext) context, slider1.getValue());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.PruningFolds);
                    ParameterContext.handleContext((ParameterContext) context, slider2.getValue());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        slider3.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.DecisionTreeInstances);
                    ParameterContext.handleContext((ParameterContext) context, slider3.getValue());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

       this.add(tip);
       this.add(par1);
       this.add(slider1);
       this.add(par2);
       this.add(slider2);
       this.add(par3);
       this.add(slider3);
    }
}
