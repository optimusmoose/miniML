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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.io.FileUtils;

import utils.Logging.MiniMLLogger;

import static org.apache.commons.io.FileUtils.readFileToString;

import workflow.context.*;


/**
 * Created by dave on 3/3/17.
 */
public class DatasetTab  extends JComponent {

    private DatasetContext context;
    private FileContext fsContext;
    private String dataset = "";
    private JTextPane txt = new JTextPane();

    public DatasetTab() {

        context = new DatasetContext(new MiniMLContext()); //TODO: MiniML Context needs to be pulled out to 'primary' panel


        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6, 0));
        panel.add(fs_panel());
        this.add(panel);
    }

    public JPanel fs_panel(){
        //context for the file selector
        fsContext = new FileContext(this.context);

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
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            contents = readFileToString(selectedFile);
        }
        dataset = contents;
        return(contents);
    }

    private class fslistener implements ActionListener {
        private JButton source;
        private DatasetTab parent;
        private AbstractParameterContext context;
        public void actionPerformed(ActionEvent ev) {
            try {
                source = (JButton) ev.getSource();
                parent = (DatasetTab) source.getParent().getParent().getParent();//TODO: TO MANY NESTED JCOMPS
                dataset = fs_select();
                txt.setText(dataset);
                context = parent.getFsContext();
                context.setValue(dataset, "str");
                context.updateState();
            } catch (IOException e) {
                MiniMLLogger.INSTANCE.error(("Hit an error opening file."));
            }
        }
    }


}
