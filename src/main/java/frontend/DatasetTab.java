package frontend;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.io.FileUtils;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * Created by dave on 3/3/17.
 */
public class DatasetTab  extends JComponent {

    public DatasetTab() {
        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fs_panel());
        this.add(panel);
    }

    public JPanel fs_panel(){
        String dataset = null;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JTextPane txt = new JTextPane();
        JScrollPane jsp = new JScrollPane(txt);
        JButton fs = new JButton("Browse...");
        JLabel tip = new JLabel("Select a Dataset: ");
        panel.add(tip);
        panel.add(fs);
        panel.add(jsp);
        //if(fs.getModel().isPressed()) {
            try {
                dataset = fs_select();
            } catch (IOException e) {
                System.out.println("Hit an error opening file.");
            }
        //}
        txt.setText(dataset);
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
        return(contents);
    }


}
