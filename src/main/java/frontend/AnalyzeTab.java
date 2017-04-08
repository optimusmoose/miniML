package frontend;

import javax.swing.*;
import java.awt.*;

class AnalyzeTab extends JComponent{

    AnalyzeTab() {
        super();

        this.setLayout(new BorderLayout());
        this.add(consolePanel(), BorderLayout.PAGE_START);
        this.add(modelPanel(), BorderLayout.CENTER);
        this.add(timeRemainingPanel(), BorderLayout.PAGE_END);
    }

    /**
     * Console panel holds a live feed of the commands being used by the WEKA
     * API and what they return.
     * @return consolePanel
     */
    private JPanel consolePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Console Output");
        JTextArea consoleText = new JTextArea(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut faucibus consectetur accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum metus eget quam ornare, quis laoreet urna bibendum. Aliquam ut enim ut purus convallis blandit. Integer tellus est, consectetur sed leo at, fermentum viverra quam. Pellentesque fringilla neque eu arcu malesuada, at dictum neque sagittis. Praesent et efficitur nulla. Sed faucibus ornare quam, in blandit dolor. Donec quis erat sed neque ullamcorper pellentesque at ut ex. Nulla nulla turpis, iaculis in rutrum non, facilisis at erat. Nullam tempor nunc at vulputate congue. Vivamus vel porta nibh. Donec facilisis posuere turpis a scelerisque. Fusce pellentesque velit in ligula laoreet rhoncus. Aliquam condimentum id metus vitae sollicitudin.\n"
        );
        consoleText.setLineWrap(true);

        JScrollPane consolePane = new JScrollPane(consoleText);
        consolePane.setPreferredSize(new Dimension(panel.getWidth(), 100)); //TODO: consolepane height scale dynamically

        panel.add(title, BorderLayout.PAGE_START);
        panel.add(new JScrollPane(consoleText));

        MessageConsole console = new MessageConsole(consoleText);
        console.redirectOut();
        console.redirectError(Color.RED, null);

        return panel;
    }

    private JPanel modelPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel bestGraph = new JLabel("Some Graph Here", SwingConstants.CENTER);
        JLabel graphInfoAndSave = new JLabel("Important graph info goes here and so does the save button", SwingConstants.CENTER);

        panel.add(bestGraph);
        panel.add(graphInfoAndSave);

        return panel;
    }

    private JPanel timeRemainingPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel title = new JLabel("Time Remaining", SwingConstants.CENTER);
        JLabel timeRemaining = new JLabel("Time Goes Here", SwingConstants.CENTER);

        panel.add(title);
        panel.add(timeRemaining);

        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        return panel;
    }
}
