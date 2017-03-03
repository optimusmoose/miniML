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

public class PreprocessTab extends JComponent{

    public PreprocessTab(){
        this.setLayout(new GridLayout());
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(6,0));
        JPanel bp = base_panel();
        panel.add(bp);
        this.add(panel);
    }


    public JPanel base_panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        String val = new String();
        JLabel tip = new JLabel("Preprocessing Stuff");
        panel.add(tip);
        return panel;
    }

}
