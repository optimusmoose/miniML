package frontend;

import utils.Logging.MiniMLLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MiniMLContainerPanel extends JPanel {

    private static final int APP_WIDTH = 1200;
    private static final int APP_HEIGHT = 600;

    public MiniMLContainerPanel() {
        super(new GridLayout());

        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.addComponents();
    }

    private void addComponents()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        ImageIcon icon = new ImageIcon();

        try {
//            icon = new ImageIcon(this.getClass().getResource("img/miniml_logo.png"));//TODO: fix resource acccess for this
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }

        DatasetTab datasetTab = new DatasetTab();
        tabbedPane.addTab("Dataset", icon, datasetTab,"Select a dataset for analysis.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        PreprocessTab preprocessTab = new PreprocessTab();
        tabbedPane.addTab("Preprocess", icon, preprocessTab, "Curate data to improve performance.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        ModelTab modelTab = new ModelTab();
        tabbedPane.addTab("Model", icon, modelTab,"Configure MiniML's model usage.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        AnalyzeTab analyzeTab = new AnalyzeTab();
        tabbedPane.addTab("Analyze", icon, analyzeTab,"Begin analysis and view results.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        AdvancedTab advancedTab = new AdvancedTab();
        tabbedPane.addTab("Advanced Settings", icon, advancedTab,"Change system settings.");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        this.add(tabbedPane);
    }

}
