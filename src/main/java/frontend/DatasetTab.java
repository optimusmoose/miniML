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
    private FileContext fsContext;
    private String dataset = "";
    private JTextPane txt = new JTextPane();

    public DatasetTab() {

        context = new DatasetContext(new MiniMLContext()); //TODO: MiniML Context needs to be pulled out to 'primary' panel

        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fileSelectPanel());
        this.add(panel);

    }

    public JPanel fileSelectPanel(){
        //context for the file selector
        fsContext = new FileContext(this.context);

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
                    dataset = fs_select();

                    txt.setText(dataset);

                    context = parent.getFsContext();
                    context.setValue(dataset, "str");
                    context.updateState();
                } catch (IOException exception) {
                    MiniMLLogger.INSTANCE.exception(exception);
                }
            }
        });

        panel.add(tip);
        panel.add(fileSelect);
        panel.add(scrollPane);
        MiniMLLogger.INSTANCE.info((dataset));
        return panel;
    }

    //TODO: move this to wrapper magic class that does not exist yet
    public FileContext getFsContext(){
        return this.fsContext;
    }

    public String fs_select() throws IOException{
        String contents = null;
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile = null;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            MiniMLLogger.INSTANCE.info("Selected file: " + selectedFile.getAbsolutePath());
            contents = readFileToString(selectedFile);//TODO: deprecated fileread, why!
        }
        dataset = contents;
        return(contents);
    }

}
