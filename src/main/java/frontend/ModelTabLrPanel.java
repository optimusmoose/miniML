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

public class ModelTabLrPanel extends JPanel {

    private AbstractCompositeContext context;
    private ParameterContext methodSelectContext;
    private ParameterContext ridgeContext;

    public ModelTabLrPanel() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.methodSelectContext = new ParameterContext(this.context, Keys.MethodSelect);
        this.ridgeContext = new ParameterContext(this.context, Keys.Ridge);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Linear Regression: ");
        JLabel par1 = new JLabel("Select Method: ");
        JLabel par2 = new JLabel("Ridge: ");
        JSlider slider1 = new JSlider(0,2,1);
        JSlider slider2 = new JSlider(0, 1, 1);

        slider1.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.MethodSelect);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Ridge);
                    ParameterContext.handleContext((ParameterContext) context, slider2.getValue());
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
    }
}
