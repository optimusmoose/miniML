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

public class ModelTabSvmPanel extends JPanel{

    private AbstractCompositeContext context;
    private ParameterContext gammaContext;
    private ParameterContext epsilonContext;
    private ParameterContext degreeContext;
    private ParameterContext nuContext;

    public ModelTabSvmPanel() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.gammaContext = new ParameterContext(this.context, Keys.Gamma);
        this.epsilonContext = new ParameterContext(this.context, Keys.Epsilon);
        this.degreeContext = new ParameterContext(this.context, Keys.Degree);
        this.nuContext = new ParameterContext(this.context, Keys.Nu);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Support Vector Machine: ");
        JLabel par1 = new JLabel("Gamma: ");
        JLabel par2 = new JLabel("Epsilon: ");
        JLabel par3 = new JLabel("Degree: ");
        JLabel par4 = new JLabel("Nu: ");
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);
        JSlider slider4 = new JSlider(1, 1000, 100);

        slider1.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Gamma);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Epsilon);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Degree);
                    ParameterContext.handleContext((ParameterContext) context, slider3.getValue());
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        slider4.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.Nu);
                    ParameterContext.handleContext((ParameterContext) context, slider4.getValue());
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
        this.add(par4);
        this.add(slider4);
    }
}
