package frontend;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.GridLayout;

public class ModelTab extends JComponent {

    public ModelTab(){
        super();

        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6,0));
        JPanel etaPanel = getETAPanel();
        JPanel algorithmPanel = getAlgorithmPanel();
        JPanel linearRegressionPanel = getLinearRegressionPanel();
        JPanel neuralNetPanel = getNeuralNetPanel();
        JPanel supportVectorMachinePanel = getSupportVectorMachinePanel();
        JPanel decisionTreePanel = getDecisionTreePanel();

        panel.add(etaPanel);
        panel.add(algorithmPanel);
        panel.add(linearRegressionPanel);
        panel.add(neuralNetPanel);
        panel.add(supportVectorMachinePanel);
        panel.add(decisionTreePanel);

        this.add(panel);
    }

    private JPanel getETAPanel(){
        JPanel e_panel = new JPanel();
        e_panel.setLayout(new GridLayout());
        String val = new String();
        JLabel tip = new JLabel("Estimated Runtime: " + val);
        JSlider slider = new JSlider(1,60,10);
        val = String.valueOf(slider.getValue());
        e_panel.add(tip);
        e_panel.add(slider);
        return e_panel;
    }

    private JPanel getAlgorithmPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JLabel tip = new JLabel("Algorithms: ");
        JToggleButton lr = new JToggleButton("Linear Regression");
        JToggleButton nn = new JToggleButton("Neural Network");
        JToggleButton svm = new JToggleButton("Support Vector Machine");
        JToggleButton dt = new JToggleButton("Decision Tree");
        panel.add(tip);
        panel.add(lr);
        panel.add(nn);
        panel.add(svm);
        panel.add(dt);
        return panel;
    }

    private JPanel getLinearRegressionPanel(){
        JPanel panel = new JPanel();
        String val1 = new String();
        String val2 = new String();
        panel.setLayout(new GridLayout());
        JLabel tip = new JLabel("Linear Regression: ");
        JProgressBar pbar = new JProgressBar(0,0,100);
        JLabel par1 = new JLabel("Select Method: " + val1);
        JLabel par2 = new JLabel("Ridge: " + val2);
        JSlider slider1 = new JSlider(0,2,1);
        JSlider slider2 = new JSlider(0, 1, 1);
        val1 = String.valueOf(slider1.getValue());
        val2 = String.valueOf(slider2.getValue());
        panel.add(tip);
        //getLinearRegressionPanel.add(pbar);
        panel.add(par1);
        panel.add(slider1);
        panel.add(par2);
        panel.add(slider2);
        return panel;
    }

    private JPanel getNeuralNetPanel(){
        JPanel panel = new JPanel();
        String val1 = new String();
        String val2 = new String();
        String val3 = new String();
        String val4 = new String();
        panel.setLayout(new GridLayout());
        JLabel tip = new JLabel("Neural Network: ");
        JProgressBar pbar = new JProgressBar(0,0,100);
        JLabel par1 = new JLabel("Hidden Layers: " + val1);
        JLabel par2 = new JLabel("Hidden Nodes: " + val2);
        JLabel par3 = new JLabel("Learn Rate: " + val3);
        JLabel par4 = new JLabel("Epochs: " + val4);
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);
        JSlider slider4 = new JSlider(1, 1000, 100);
        val1 = String.valueOf(slider1.getValue());
        val2 = String.valueOf(slider2.getValue());
        val3 = String.valueOf(slider3.getValue());
        val4 = String.valueOf(slider4.getValue());
        panel.add(tip);
        //getNeuralNetPanel.add(pbar);
        panel.add(par1);
        panel.add(slider1);
        panel.add(par2);
        panel.add(slider2);
        panel.add(par3);
        panel.add(slider3);
        panel.add(par4);
        panel.add(slider4);
        return panel;
    }

    public JPanel getSupportVectorMachinePanel(){
        JPanel panel = new JPanel();
        String val1 = new String();
        String val2 = new String();
        String val3 = new String();
        String val4 = new String();
        panel.setLayout(new GridLayout());
        JLabel tip = new JLabel("Support Vector Machine: ");
        JProgressBar pbar = new JProgressBar(0,0,100);
        JLabel par1 = new JLabel("Gamma: " + val1);
        JLabel par2 = new JLabel("Epsilon: " + val2);
        JLabel par3 = new JLabel("Degree: " + val3);
        JLabel par4 = new JLabel("Nu: " + val4);
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);
        JSlider slider4 = new JSlider(1, 1000, 100);
        val1 = String.valueOf(slider1.getValue());
        val2 = String.valueOf(slider2.getValue());
        val3 = String.valueOf(slider3.getValue());
        val4 = String.valueOf(slider4.getValue());
        panel.add(tip);
        //panel.add(pbar);
        panel.add(par1);
        panel.add(slider1);
        panel.add(par2);
        panel.add(slider2);
        panel.add(par3);
        panel.add(slider3);
        panel.add(par4);
        panel.add(slider4);
        return panel;
    }

    public JPanel getDecisionTreePanel(){
        JPanel panel = new JPanel();
        String val1 = new String();
        String val2 = new String();
        String val3 = new String();
        panel.setLayout(new GridLayout());
        JLabel tip = new JLabel("Decision Tree: ");
        JProgressBar pbar = new JProgressBar(0,0,100);
        JLabel par1 = new JLabel("Pruning Confidence: " + val1);
        JLabel par2 = new JLabel("Pruning Folds: " + val2);
        JLabel par3 = new JLabel("Instances: " + val3);
        JSlider slider1 = new JSlider(1,9,1);
        JSlider slider2 = new JSlider(1, 100, 10);
        JSlider slider3 = new JSlider(0,100,20);
        val1 = String.valueOf(slider1.getValue());
        val2 = String.valueOf(slider2.getValue());
        val3 = String.valueOf(slider3.getValue());
        panel.add(tip);
        panel.add(par1);
        panel.add(slider1);
        panel.add(par2);
        panel.add(slider2);
        panel.add(par3);
        panel.add(slider3);
        return panel;
    }

}
