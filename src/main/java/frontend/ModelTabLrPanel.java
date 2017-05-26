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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JLabel methodLabel = new JLabel("Method: ");
        JLabel ridgeLabel = new JLabel("Ridge: ");

        JComboBox<String> methodSelect = new JComboBox<String>();
        methodSelect.addItem("M5");
        methodSelect.addItem("None");
        methodSelect.addItem("Greedy");

        JSlider ridgeSlider = new JSlider(0, 10, 1);

        methodSelect.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.MethodSelect);
                    ParameterContext.handleContext((ParameterContext) context, methodSelect.getSelectedIndex());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }



        });

        ridgeSlider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Ridge);
                    ParameterContext.handleContext((ParameterContext) context, ridgeSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        this.add(tip);
        this.add(methodLabel);
        this.add(methodSelect);
        this.add(ridgeLabel);
        this.add(ridgeSlider);
    }
}
