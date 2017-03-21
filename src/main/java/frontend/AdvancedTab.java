package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mattpatera on 3/19/17.
 */
class AdvancedTab extends JLabel {

    AdvancedTab() {
        this.setLayout(new GridLayout(2, 0));
        this.add(generalPanel());
        this.add(advancedPanel());
    }

    private JPanel generalPanel() {
        JPanel panel = new JPanel();
        JLabel setting1 = new JLabel("Settings go here");
        panel.add(setting1);

        return panel;
    }

    private JPanel advancedPanel() {
        JPanel panel = new JPanel();
        JLabel setting1 = new JLabel("Advanced Settings go here");
        panel.add(setting1);

        return panel;
    }
}
