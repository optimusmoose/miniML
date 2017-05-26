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
        JLabel hiddenLayersLabel = new JLabel("Hidden Layers: ");
        JLabel hiddenNodesLabel = new JLabel("Hidden Nodes: ");
        JLabel learningRateLabel = new JLabel("Learn Rate: ");
        JLabel epochsLabel = new JLabel("Epochs: ");

        JSlider hiddenLayersSlider = new JSlider(1,9,1);
        JSlider hiddenNodesSlider = new JSlider(1, 100, 10);
        JSlider learningRateSlider = new JSlider(0,100,20);
        JSlider epochsSlider = new JSlider(1, 1000, 100);

        hiddenLayersSlider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenLayers);
                    ParameterContext.handleContext((ParameterContext) context, hiddenLayersSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        hiddenNodesSlider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumHiddenNodes);
                    ParameterContext.handleContext((ParameterContext) context, hiddenNodesSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        learningRateSlider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.LearnRate);
                    ParameterContext.handleContext((ParameterContext) context, learningRateSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        epochsSlider.addChangeListener(new ChangeListener() {
            private AbstractCompositeContext context;

            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.NumEpochs);
                    ParameterContext.handleContext((ParameterContext) context, epochsSlider.getValue());
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        this.add(tip);
        this.add(hiddenLayersLabel);
        this.add(hiddenLayersSlider);
        this.add(hiddenNodesLabel);
        this.add(hiddenNodesSlider);
        this.add(learningRateLabel);
        this.add(learningRateSlider);
        this.add(epochsLabel);
        this.add(epochsSlider);
    }
}
