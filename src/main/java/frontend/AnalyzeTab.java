package frontend;

import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import utils.Logging.JTextAreaAppender;
import utils.Logging.MiniMLLogger;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

class AnalyzeTab extends JPanel {
    private AbstractCompositeContext parentContext;
    private AnalyzeContext context;

    private GridBagConstraints constraints;

    private JTextAreaAppender outputAppender;

    public AnalyzeTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new AnalyzeContext(parentContext, Keys.AnalyzeConfig);

        this.constraints = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        this.console();
        this.models();
        this.timeRemaining();

    }

    /**
     * Console panel holds a live feed of the commands being used by the WEKA
     * API and what they return.
     * @return console
     */
    private void console() {
        JTextArea consoleText = new JTextArea();
        consoleText.setLineWrap(false);
        consoleText.setEditable(false);

        JScrollPane consolePane = new JScrollPane(consoleText);

        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.weightx = 1.0;
        this.constraints.weighty = 0.4;
        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.anchor = GridBagConstraints.CENTER;
        this.add(new JScrollPane(consoleText), this.constraints);

        this.addAppender(consoleText);
    }

    private void addAppender(JTextArea jTextArea) {
        try {
            this.outputAppender = new JTextAreaAppender(jTextArea);
            this.outputAppender.setLayout(new PatternLayout("%d{ISO8601} %-5p [%t] %c{2} %x - %m%n")); //TODO: magic string
            this.outputAppender.setThreshold(Level.INFO);
            MiniMLLogger.INSTANCE.getLogger().addAppender(this.outputAppender);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
    }

    private void models() {
        AnalyzeLaunchContext startAnalysisButton = new AnalyzeLaunchContext(this.context, Keys.StartAnalysisButton);

        JLabel bestGraph = new JLabel("Some Graph Here");
        JLabel graphInfoAndSave = new JLabel("Important graph info goes here and so does the save button");
        JButton button = new JButton("Run");

        URL iconPath = this.getClass().getResource(MiniMLContainerPanel.ICON_PATH);

        try {
            Image image = new ImageIcon(iconPath).getImage().getScaledInstance(MiniMLContainerPanel.ICON_SIZE, MiniMLContainerPanel.ICON_SIZE, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image));
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }

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

        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.gridy++;
        this.constraints.weighty = 0.2;
        this.constraints.weightx = 0.5;
        this.constraints.anchor = GridBagConstraints.WEST;
        this.add(bestGraph, this.constraints);

        this.constraints.weightx = 0.5;
        this.constraints.anchor = GridBagConstraints.EAST;
        this.add(graphInfoAndSave, this.constraints);

        this.constraints.gridy++;
        this.constraints.weighty = 0.1;
        this.constraints.weightx = 0.33;
        this.constraints.anchor = GridBagConstraints.SOUTHEAST;
        this.add(button, this.constraints);
    }

    private void handleStartAnalysisButtonContext(AnalyzeLaunchContext context) {
        context.execute();
    }

    private void timeRemaining() {
        JLabel title = new JLabel("Time Remaining: ");
        JLabel timeRemaining = new JLabel("Elapsed time: ");

        this.constraints.anchor = GridBagConstraints.SOUTHWEST;
        this.add(title, this.constraints);
        this.constraints.anchor = GridBagConstraints.SOUTH;
        this.add(timeRemaining, this.constraints);

//        panel.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
