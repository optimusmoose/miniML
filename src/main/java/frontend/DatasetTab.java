package frontend;

import utils.Logging.MiniMLLogger;
import utils.TypeFactory;
import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.AbstractCompositeContext;
import workflow.context.ParameterContext;
import workflow.context.DatasetContext;
import workflow.context.FileContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

public class DatasetTab extends JComponent {

    private AbstractCompositeContext parentContext;
    private DatasetContext context;
    private FileContext fileSelectContext;
    private String dataset;
    private JTextPane txt = new JTextPane();

    public DatasetTab() {
        super();

        parentContext = WorkflowManager.INSTANCE.getContextByKey(Keys.App);
        context = new DatasetContext(parentContext, Keys.DatasetConfig);

        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fileSelectPanel());
        this.add(panel);

    }

    private JPanel fileSelectPanel(){
        //context for the file selector
        fileSelectContext = new FileContext(this.context, Keys.DatasetFile);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(txt);
        JButton fileSelect = new JButton("Browse...");
        JLabel tip = new JLabel("Select a Dataset: ");

        /*
         * listeners should be anonymous inner classes, this prevents nasty hacks
         */
        fileSelect.addActionListener(new ActionListener() {
            private AbstractCompositeContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile();
                    context = WorkflowManager.INSTANCE.getContextByKey(Keys.DatasetFile);
                    handleFileSelectContext((ParameterContext) context);
                } catch (IOException exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        panel.add(tip);
        panel.add(fileSelect);
        panel.add(scrollPane);
        return panel;
    }

    //TODO: move this to wrapper magic class that does not exist yet
    public FileContext getFileSelectContext(){
        return this.fileSelectContext;
    }

    public void handleFileSelectContext(ParameterContext context) {
        context.setValue(this.dataset, TypeFactory.STRING);
        context.updateState();
    }

    public void selectFile() throws IOException {
        String contents;
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            MiniMLLogger.INSTANCE.info("Selected file: " + selectedFile.getAbsolutePath());
            contents = readFileToString(selectedFile);//TODO: deprecated fileread, why! this will explode on a huge file!
            this.dataset = selectedFile.getAbsolutePath();
            txt.setText(contents);
        }
    }

    public void previewData() {

    }

}
