package frontend;

import utils.Logging.MiniMLLogger;
import utils.TypeFactory;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DatasetTab extends JPanel {
    private AbstractCompositeContext parentContext;
    private DatasetContext context;
    private InstanceContext wekaInstance;

    private GridBagConstraints constraints;

    private String dataset;
    private String content;

    private JTextPane previewDataset = new JTextPane();
    private JTextPane previewContent = new JTextPane();

    public DatasetTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new DatasetContext(parentContext, Keys.DatasetConfig);
        this.wekaInstance = new InstanceContext(parentContext, Keys.RootWekaInstnace);

        this.constraints = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        this.fileSelectPanel();
    }

    private void fileSelectPanel(){
        FileContext fileSelectContext = new FileContext(this.context, Keys.DatasetFile);

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

        this.constraints.anchor = GridBagConstraints.WEST;
        this.constraints.gridy = 0;
        this.constraints.weightx = 1.0;
        this.add(browsLabel, this.constraints);
        this.add(browseButton, this.constraints);

        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.anchor = GridBagConstraints.EAST;
        this.add(datasetScrollPane, this.constraints);

        this.constraints.gridy++;
        this.constraints.gridwidth = GridBagConstraints.REMAINDER;
        this.constraints.weighty = 1.0;
        this.add(contentScrollPane, this.constraints);
    }

    private void handleFileSelectContext(ParameterContext context) {
        context.setValue(this.dataset, TypeFactory.STRING);
        context.updateState();
    }

    private void selectFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            this.dataset = selectedFile.getAbsolutePath();
            this.previewDataset.setText(this.dataset);
            this.previewData(selectedFile);
            this.loadWekaInstance(this.dataset);

            MiniMLLogger.INSTANCE.info("Selected file: " + selectedFile.getAbsolutePath());
        }
    }

    private void loadWekaInstance(String path) {
        try {
            this.wekaInstance.loadInstanceFromPath(path);
        } catch (Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
    }

    private void previewData(File file) {
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
