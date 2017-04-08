package frontend;

import javax.swing.*;
import java.awt.*;

class AdvancedTab extends JComponent {

    AdvancedTab() {
        super();

        this.setLayout(new GridLayout(2,0));
        this.add(runtimePanel());
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
