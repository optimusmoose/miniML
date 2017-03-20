package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mattpatera on 3/19/17.
 */
public class AnalyzeTab extends JComponent {

    public AnalyzeTab() {
        this.setLayout(new GridLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());
    }

    /**
     * Console panel holds a live feed of the commands being used by the WEKA
     * API and what they return.
     * @return consolePanel
     */
    JPanel consolePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextArea consoleText = new JTextArea();
        panel.add(new JScrollPane(consoleText));

        return panel;
    }

    JPanel timeRemainingPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    JPanel modelPanel() {
        JPanel panel = new JPanel();

        return panel;
    }
}
