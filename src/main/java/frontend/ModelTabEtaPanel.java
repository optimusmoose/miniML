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

public class ModelTabEtaPanel extends JPanel {

    private AbstractCompositeContext context;
    private ParameterContext estimatedRuntimeContext;

    public ModelTabEtaPanel() {
        super();
        context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.estimatedRuntimeContext = new ParameterContext(this.context, Keys.EstimatedTimeConfig);
        this.estimatedRuntimeContext.setValue(10);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Estimated Runtime: ");
        JSlider slider = new JSlider(1,60,10);
        slider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.EstimatedTimeConfig);
                    ParameterContext.handleContext((ParameterContext) context, slider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        this.add(tip);
        this.add(slider);
    }
}
