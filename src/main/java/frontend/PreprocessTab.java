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

class PreprocessTab extends JPanel{

    PreprocessTab(){
        this.setLayout(new BorderLayout());
        this.add(fileAndFilter(), BorderLayout.PAGE_START);
    }

    private JPanel fileAndFilter() {
        JPanel panel = new JPanel(new GridLayout(2, 0));
        JPanel buttonPanel = new JPanel(new GridLayout(0, 7));
        JPanel filePanel = new JPanel(new BorderLayout());

        JButton openFileButton = new JButton("Open File...");
        JButton openURLButton = new JButton("Open URL...");
        JButton openDBButton = new JButton("Open DP...");
        JButton generateButton = new JButton("Generate...");
        JButton undoButton = new JButton("Undo");
        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Save");

        buttonPanel.add(openFileButton);
        buttonPanel.add(openURLButton);
        buttonPanel.add(openDBButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);

        panel.add(buttonPanel);
        panel.add(filePanel);

        return panel;
    }

}
