package frontend;

import org.apache.log4j.PatternLayout;
import utils.Logging.JTextAreaAppender;
import utils.Logging.MiniMLLogger;
import utils.TypeFactory;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.builder.NoUserParameterDispatcherBuilder;
import workflow.context.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AnalyzeTab extends JComponent{
    private AbstractCompositeContext parentContext;
    private AnalyzeContext context;
    private AnalyzeLaunchContext startAnalysisButton;

    private JTextAreaAppender outputAppender;

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
        JTextArea consoleText = new JTextArea();
        consoleText.setLineWrap(true);

        JScrollPane consolePane = new JScrollPane(consoleText);
        consolePane.setPreferredSize(new Dimension(panel.getWidth(), 100)); //TODO: consolepane height scale dynamically

        panel.add(title, BorderLayout.PAGE_START);
        panel.add(new JScrollPane(consoleText));

        try {
            this.outputAppender = new JTextAreaAppender(consoleText);
            this.outputAppender.setLayout(new PatternLayout("%d{ISO8601} %-5p [%t] %c{2} %x - %m%n")); //TODO: magic string
            MiniMLLogger.INSTANCE.getLogger().addAppender(this.outputAppender);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }


        return panel;
    }

    //TODO this cannot possibly be the correct name for this object.
    private JPanel modelPanel() {
        this.startAnalysisButton = new AnalyzeLaunchContext(this.context, Keys.StartAnalysisButton);

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
                    handleStartAnalysisButtonContext((AnalyzeLaunchContext) context);
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

    public void handleStartAnalysisButtonContext(AnalyzeLaunchContext context) {
        context.execute();
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
