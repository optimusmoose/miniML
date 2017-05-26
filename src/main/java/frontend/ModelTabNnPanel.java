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

public class ModelTabNnPanel extends JPanel {

    private AbstractCompositeContext context;
    private ParameterContext hiddenLayerContext;
    private ParameterContext hiddenNodesContext;
    private ParameterContext learnRateContext;
    private ParameterContext epochContext;

    public ModelTabNnPanel() {
        super();
        this.context = WorkflowManager.INSTANCE.getContextByKey(Keys.ModelConfig);

        this.hiddenLayerContext = new ParameterContext(this.context, Keys.NumHiddenLayers);
        this.hiddenNodesContext = new ParameterContext(this.context, Keys.NumHiddenNodes);
        this.learnRateContext = new ParameterContext(this.context, Keys.LearnRate);
        this.epochContext = new ParameterContext(this.context, Keys.NumEpochs);

        this.setLayout(new GridLayout());
        JLabel tip = new JLabel("Neural Network: ");
        JProgressBar pbar = new JProgressBar(0,0,100);
        JLabel par1 = new JLabel("Hidden Layers: ");
        JLabel par2 = new JLabel("Hidden Nodes: ");
        JLabel par3 = new JLabel("Learn Rate: ");
        JLabel par4 = new JLabel("Epochs: ");
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);
        JSlider slider4 = new JSlider(1, 1000, 100);

        slider1.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenLayers);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenNodes);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.LearnRate);
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
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumEpochs);
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
