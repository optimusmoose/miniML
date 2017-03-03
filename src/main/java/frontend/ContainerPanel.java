package frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class ContainerPanel extends JPanel {

    public ContainerPanel() {
        super(new GridLayout());
        //TODO: configuration import
        setPreferredSize(new Dimension(1200, 600));
        System.out.println("is it here?");
        //this.setSize(this.getParent().getSize());

        this.addComponents();
    }

    protected JComponent makeBasePanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        JPanel cont = new JPanel();
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout());
        panel.add(cont);
        cont.add(filler);
        return panel;
    }

    private void addComponents()
    {
        //Label myLabel = new Label("Hello World!");
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(2);
        ImageIcon icon = new ImageIcon("images/middle.gif");

        //JComponent panel1 = makeBasePanel("Dataset");
        DatasetTab panel1 = new DatasetTab();
        tabbedPane.addTab("Dataset", icon, panel1,
                          "Select a dataset for analysis.");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        //JComponent panel2 = makeBasePanel("Preprocess");
        PreprocessTab panel2 = new PreprocessTab();
        tabbedPane.addTab("Preprocess", icon, panel2,
                          "Curate data to improve performance.");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //JComponent panel3 = makeBasePanel("Model");
        ModelTab panel3 = new ModelTab();
        tabbedPane.addTab("Model", icon, panel3,
                          "Configure MiniML's model usage.");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeBasePanel(
                "Analyze");
        //panel4.setPreferredSize(new Dimension(410, 500));
        tabbedPane.addTab("Analyze", icon, panel4,
                              "Begin analysis and view results.");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        JComponent panel5 = makeBasePanel(
                "Advanced Settings");
        tabbedPane.addTab("Advanced Settings", icon, panel5,
                "Change system settings.");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        this.add(tabbedPane);
    }
}
