package frontend;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mattpatera on 3/19/17.
 */
class AdvancedTab extends JLabel {

    AdvancedTab() {
        this.setLayout(new GridLayout(2,0));
        this.add(runtimePanel());
        // this.add(new JSeparator());
        this.add(hardwarePanel());
    }

    private JPanel runtimePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Runtime Settings go here");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 14f));
        panel.add(title, BorderLayout.PAGE_START);

        return panel;
    }

    private JPanel hardwarePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Advanced Settings go here");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 14f));
        panel.add(title, BorderLayout.PAGE_START);

        JPanel contentPanel = new JPanel();

        return panel;
    }
}
