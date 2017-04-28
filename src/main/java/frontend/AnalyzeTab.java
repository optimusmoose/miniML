package frontend;

import utils.Logging.MiniMLLogger;
import utils.TypeFactory;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.AnalyzeContext;
import workflow.context.ImperativeContext;
import workflow.context.ParameterContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnalyzeTab extends JComponent{
    private AbstractCompositeContext parentContext;
    private AnalyzeContext context;
    private ImperativeContext startAnalysisButton;

    AnalyzeTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new AnalyzeContext(parentContext, Keys.AnalyzeConfig);

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

        return panel;
    }

    //TODO this cannot possibly be the correct name for this object.
    private JPanel modelPanel() {
        this.startAnalysisButton = new ImperativeContext(this.context, Keys.StartAnalysisButton);

        JPanel panel = new JPanel(new GridLayout(1, 3));

        JLabel bestGraph = new JLabel("Some Graph Here", SwingConstants.CENTER);
        JLabel graphInfoAndSave = new JLabel("Important graph info goes here and so does the save button", SwingConstants.CENTER);
        JButton button = new JButton();

        button.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.StartAnalysisButton);
                    handleStartAnalysisButtonContext((ImperativeContext) context);
                } catch(Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        panel.add(bestGraph);
        panel.add(graphInfoAndSave);
        panel.add(button);

        return panel;
    }

    public void handleStartAnalysisButtonContext(ImperativeContext context) {
        context.execute();
        //context.updateState();
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
