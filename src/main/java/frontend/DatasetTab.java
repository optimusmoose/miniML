package frontend;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import utils.Logging.MiniMLLogger;

import static org.apache.commons.io.FileUtils.readFileToString;

import workflow.context.*;

public class DatasetTab extends JComponent {

    private DatasetContext context;
    private FileContext fileSelectContext;
    private String dataset;
    private JTextPane txt = new JTextPane();

    public DatasetTab() {
        super();

        context = new DatasetContext(new MiniMLContext()); //TODO: MiniML Context needs to be pulled out to 'primary' panel

        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fileSelectPanel());
        this.add(panel);

    }

    private JPanel fileSelectPanel(){
        //context for the file selector
        fileSelectContext = new FileContext(this.context);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(txt);
        JButton fileSelect = new JButton("Browse...");
        JLabel tip = new JLabel("Select a Dataset: ");

        /*
         * listeners should be anonymous inner classes, this prevents nasty hacks
         */
        fileSelect.addActionListener(new ActionListener() {
            private JButton source;
            private DatasetTab parent;
            private AbstractParameterContext context;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    source = (JButton) e.getSource();
                    parent = (DatasetTab) source.getParent().getParent().getParent();//TODO: TO MANY NESTED JCOMPS
                    context = parent.getFileSelectContext();

                    selectFile();
                    handleFileSelectContext(context);
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

    public void handleFileSelectContext(AbstractParameterContext context) {
        context.setValue(this.dataset, "str");
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
            this.dataset = contents;
            txt.setText(contents);
        }
    }

    public void previewData() {

    }

}
