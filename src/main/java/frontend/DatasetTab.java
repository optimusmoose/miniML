package frontend;

import utils.Logging.MiniMLLogger;
import weka.core.Instances;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    InstanceContext wekaInstance;

    private GridBagConstraints constraints;

    private String dataset;
    private String content;

    private JTextPane previewDataset = new JTextPane();
    private JTextPane previewContent = new JTextPane();

    private DefaultListModel<String> attributes = new DefaultListModel<String>();
    JList<String> attributesList;
    JComboBox<String> classifierSelect;

    public DatasetTab() {
        super();

        this.parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        this.context = new DatasetContext(parentContext, Keys.DatasetConfig);

        this.wekaInstance = new InstanceContext(parentContext, Keys.RootWekaInstnace);  //TODO: dataset should not live in the frontend

        this.constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        this.initFileSelect();
    }

    private void initFileSelect(){
        this.attributesList = new JList<String>(this.attributes);
        this.classifierSelect = new JComboBox<String>();

        FileContext fileSelectContext = new FileContext(this.context, Keys.DatasetFile);

        ParameterContext selectedAttributes = new ParameterContext(this.context, Keys.SelectedAttributes);
        ParameterContext classifier = new ParameterContext(this.context, Keys.SelectedClassifier);

        JScrollPane datasetScrollPane = new JScrollPane(previewDataset);
        JScrollPane contentScrollPane = new JScrollPane(previewContent);
        JScrollPane attributeScrollPane = new JScrollPane(attributesList);

        datasetScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        contentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        contentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        attributeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel browsLabel = new JLabel("Select a Dataset: ");
        JButton browseButton = new JButton("Browse...");

        browseButton.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.DatasetFile);
                    handleContext((ParameterContext) context, dataset);
                } catch (Exception exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        attributesList.addListSelectionListener(new ListSelectionListener() {
            private AbstractCompositeContext context;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                context = WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedAttributes);
                JList<String> source = (JList<String>) e.getSource();
                int[] selected = source.getSelectedIndices();
                handleContext((ParameterContext) context, selected);
            }
        });

        classifierSelect.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                context = WorkflowManager.INSTANCE.getContextByKey(Keys.SelectedClassifier);
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                int selected = source.getSelectedIndex();
                handleContext((ParameterContext) context, selected);
            }
        });

        //TODO: all GBC's need to be made unique to prevent recycling
        this.constraints.anchor = GridBagConstraints.WEST;
        this.constraints.gridy = 0;
        this.constraints.weightx = 1.0;
        this.add(browsLabel, this.constraints);
        this.add(browseButton, this.constraints);

        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.anchor = GridBagConstraints.EAST;
        this.add(datasetScrollPane, this.constraints);

        this.constraints.gridy++;
        this.constraints.gridwidth = GridBagConstraints.REMAINDER-1;
        this.constraints.anchor = GridBagConstraints.WEST;
        this.constraints.weighty = 0.85;
        this.add(contentScrollPane, this.constraints);

        this.constraints.gridwidth = 0;
        this.constraints.anchor = GridBagConstraints.EAST;
        this.add(attributeScrollPane, this.constraints);

        this.constraints.gridy++;
        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.weighty = 0.05;
        this.constraints.anchor = GridBagConstraints.SOUTHEAST;
        this.add(classifierSelect, this.constraints);
    }

    private void handleContext(ParameterContext context, Object value) {
        context.setValue(value);
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

        Instances data = (Instances) this.wekaInstance.getValue();
        for (int i = 0; i < data.numAttributes(); i++)
        {
            String name = data.attribute(i).name();
            this.attributes.add(i, name);
            this.classifierSelect.addItem(name);
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
