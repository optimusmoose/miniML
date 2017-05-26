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

public class ModelTabAlgorithmPanel extends JPanel {

    private AbstractCompositeContext context;
    private ParameterContext toggleLinRegContext;
    private ParameterContext toggleNeuralNetContext;
    private ParameterContext toggleSuppVecContext;
    private ParameterContext toggleDecTreeContext;

    public ModelTabAlgorithmPanel() {
        super();
        context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.toggleLinRegContext = new ParameterContext(this.context, Keys.ToggleLinReg);
        this.toggleNeuralNetContext = new ParameterContext(this.context, Keys.ToggleNeuralNet);
        this.toggleSuppVecContext = new ParameterContext(this.context, Keys.ToggleSuppVec);
        this.toggleDecTreeContext = new ParameterContext(this.context, Keys.ToggleDecTree);
        // :(
        this.toggleLinRegContext.setValue(false);
        this.toggleNeuralNetContext.setValue(false);
        this.toggleDecTreeContext.setValue(false);
        this.toggleSuppVecContext.setValue(false);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Algorithms: ");
        JToggleButton lr = new JToggleButton("Linear Regression");
        JToggleButton nn = new JToggleButton("Neural Network");
        JToggleButton svm = new JToggleButton("Support Vector Machine");
        JToggleButton dt = new JToggleButton("Decision Tree");
        //TODO: refactor these listeners into more sensible sub-method
        lr.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleLinReg);
                    ParameterContext.handleContext((ParameterContext) context, lr.isSelected());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        nn.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleNeuralNet);
                    ParameterContext.handleContext((ParameterContext) context, nn.isSelected());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        svm.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleSuppVec);
                    ParameterContext.handleContext((ParameterContext) context, svm.isSelected());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        dt.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.ToggleDecTree);
                    ParameterContext.handleContext((ParameterContext) context, dt.isSelected());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        this.add(tip);
        this.add(lr);
        this.add(nn);
        this.add(svm);
        this.add(dt);
    }
}
