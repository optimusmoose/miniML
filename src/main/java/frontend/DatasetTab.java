package frontend;

import utils.Logging.MiniMLLogger;
import utils.TypeFactory;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.DatasetContext;
import workflow.context.FileContext;
import workflow.context.ParameterContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DatasetTab extends JComponent {

    private AbstractCompositeContext parentContext;
    private DatasetContext context;
    private FileContext fileSelectContext;

    private String dataset;
    private String content;

    GridBagConstraints constraints;
    JPanel panel;

    private JTextPane previewDataset = new JTextPane();
    private JTextPane previewContent = new JTextPane();

    public DatasetTab() {
        super();

        parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        context = new DatasetContext(parentContext, Keys.DatasetConfig);

        this.setLayout(new GridLayout());
        this.constraints = new GridBagConstraints();

        this.panel = new JPanel(false);
        this.panel.setLayout(new GridBagLayout());
        fileSelectPanel();
        this.add(this.panel);
    }

    private void fileSelectPanel(){
        fileSelectContext = new FileContext(this.context, Keys.DatasetFile);

        JScrollPane datasetScrollPane = new JScrollPane(previewDataset);
        JScrollPane contentScrollPane = new JScrollPane(previewContent);
        JLabel browsLabel = new JLabel("Select a Dataset: ");
        JButton browseButton = new JButton("Browse...");

        browseButton.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.DatasetFile);
                    handleFileSelectContext((ParameterContext) context);
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.33;
        constraints.weighty = 0.1;
        this.panel.add(browsLabel, this.constraints);

        this.constraints.gridx = 1;
        constraints.weightx = 0.4;
        this.panel.add(browseButton, this.constraints);

        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 2;
        this.panel.add(datasetScrollPane, this.constraints);

        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.gridwidth = 3;
        this.constraints.weightx = 1;
        this.constraints.weighty = 0.9;

        this.panel.add(contentScrollPane, this.constraints);
    }

    public void handleFileSelectContext(ParameterContext context) {
        context.setValue(this.dataset, TypeFactory.STRING);
        context.updateState();
    }

    public void selectFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            this.dataset = selectedFile.getAbsolutePath();
            this.previewDataset.setText(this.dataset);
            this.previewData(selectedFile);

            MiniMLLogger.INSTANCE.info("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    public void previewData(File file) {
        String line;
        int lines = 0;
        int linesToRead = 250; //TODO: extract to a config
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            while(((line = reader.readLine()) != null) && lines < linesToRead) {
                this.content += line + '\n';
                lines++;
            }
            reader.close();
        } catch (IOException exception) {
            MiniMLLogger.INSTANCE.exception(exception);
        }

        previewContent.setText(this.content);
    }

}
