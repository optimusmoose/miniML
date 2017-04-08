package frontend;

import utils.Logging.MiniMLLogger;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class ContainerPanel extends JPanel {

    private static final int APP_WIDTH = 1200;
    private static final int APP_HEIGHT = 600;

    public ContainerPanel() {
        super(new GridLayout());

        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.addComponents();
    }

    private void addComponents()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        ImageIcon icon = new ImageIcon("images/middle.gif");//TODO: where is this image? it is not in git? it should also live in resources, not just some iamge base directory

        DatasetTab panel1 = new DatasetTab();
        tabbedPane.addTab("Dataset", icon, panel1,
                          "Select a dataset for analysis.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        PreprocessTab panel2 = new PreprocessTab();
        tabbedPane.addTab("Preprocess", icon, panel2,
                          "Curate data to improve performance.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        ModelTab panel3 = new ModelTab();
        tabbedPane.addTab("Model", icon, panel3,
                          "Configure MiniML's model usage.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        AnalyzeTab panel4 = new AnalyzeTab();
        tabbedPane.addTab("Analyze", icon, panel4,
                              "Begin analysis and view results.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        AdvancedTab panel5 = new AdvancedTab();
        tabbedPane.addTab("Advanced Settings", icon, panel5,
                "Change system settings.");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        this.add(tabbedPane);
    }
}
