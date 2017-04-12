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

    private JTextPane previewDataset = new JTextPane();
    private JTextPane previewContent = new JTextPane();

    public DatasetTab() {
        super();

        parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        context = new DatasetContext(parentContext, Keys.DatasetConfig);

        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout());
        panel.add(fileSelectPanel());
        this.add(panel);
    }

    private JPanel fileSelectPanel(){
        fileSelectContext = new FileContext(this.context, Keys.DatasetFile);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JScrollPane datasetScrollPane = new JScrollPane(previewDataset);
        JScrollPane contentScrollPane = new JScrollPane(previewContent);
        JButton fileSelect = new JButton("Browse...");
        JLabel tip = new JLabel("Select a Dataset: ");

        fileSelect.addActionListener(new ActionListener() {
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

        panel.add(tip);
        panel.add(fileSelect);
        panel.add(datasetScrollPane);
        panel.add(contentScrollPane);

        return panel;
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
