package frontend;

import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.MiniMLContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

public class MiniMLContainerPanel extends JPanel {

    public static final int APP_WIDTH = 1200;
    public static final int APP_HEIGHT = 600;

    public static final int ICON_SIZE = 32;
    public static final String ICON_PATH = "img/miniml_icon.png";
    public static final String SETTINGS_ICON_PATH = "img/settings_icon.png";

    public MiniMLContainerPanel() {
        super(new GridLayout());
        this.setName(Keys.App);

        MiniMLContext context = new MiniMLContext(Keys.App);
        WorkflowManager.INSTANCE.registerContext(context);

        setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.addComponents();
    }

    private void addComponents()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(SwingConstants.LEFT);

        ImageIcon icon = new ImageIcon();
        ImageIcon settingsIcon = new ImageIcon();
        Image image;
        URL iconPath = this.getClass().getResource(ICON_PATH);
        URL settingsIconPath = this.getClass().getResource(SETTINGS_ICON_PATH);

        try {
            image = new ImageIcon(iconPath).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            image = new ImageIcon(settingsIconPath).getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
            settingsIcon = new ImageIcon(image);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }

        DatasetTab datasetTab = new DatasetTab();
        tabbedPane.addTab("Dataset", icon, datasetTab,"Select a dataset for analysis.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ModelTab modelTab = new ModelTab();
        tabbedPane.addTab("Model", icon, modelTab,"Configure MiniML's model usage.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        AnalyzeTab analyzeTab = new AnalyzeTab();
        tabbedPane.addTab("Analyze", icon, analyzeTab, "Begin analysis and view results.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        AdvancedTab advancedTab = new AdvancedTab();
        tabbedPane.addTab("Advanced Settings", settingsIcon, advancedTab,"Change system settings.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        this.add(tabbedPane);
    }

}
