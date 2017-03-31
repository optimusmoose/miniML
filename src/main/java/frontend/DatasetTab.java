package frontend;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;

import utils.Logging.MiniMLLogger;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * Created by dave on 3/3/17.
 */
public class DatasetTab  extends JComponent {
    private String dataset = "";
    private JTextPane txt = new JTextPane();

    public DatasetTab() {
        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fs_panel());
        this.add(panel);
    }

    public JPanel fs_panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        //JTextPane txt = new JTextPane();
        JScrollPane jsp = new JScrollPane(txt);
        JButton fs = new JButton("Browse...");
        JLabel tip = new JLabel("Select a Dataset: ");
        fs.addActionListener(new fslistener());
        panel.add(tip);
        panel.add(fs);
        panel.add(jsp);
        MiniMLLogger.INSTANCE.info((dataset));
        return panel;
    }

    public String fs_select() throws IOException{
        String contents = null;
        JFileChooser fileChooser = new JFileChooser();
        File selectedFile = null;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            contents = readFileToString(selectedFile);
        }
        dataset = contents;
        return(contents);
    }

    private class fslistener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                dataset = fs_select();
                txt.setText(dataset);
                //System.out.println(dataset);
            } catch (IOException e) {
                MiniMLLogger.INSTANCE.error(("Hit an error opening file."));
            }
        }
    }


}
