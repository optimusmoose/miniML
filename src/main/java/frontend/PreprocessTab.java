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

        this.add(statusPanel(), BorderLayout.PAGE_END);
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

        JLabel label = new JLabel("Filter");
        JButton chooseButton = new JButton("Choose");
        JTextArea text = new JTextArea();
        JButton applyButton = new JButton("Apply");

        buttonPanel.add(openFileButton);
        buttonPanel.add(openURLButton);
        buttonPanel.add(openDBButton);
        buttonPanel.add(generateButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);

        filePanel.add(label, BorderLayout.PAGE_START);
        filePanel.add(chooseButton, BorderLayout.LINE_START);
        filePanel.add(text, BorderLayout.CENTER);
        filePanel.add(applyButton, BorderLayout.LINE_END);

        panel.add(buttonPanel);
        panel.add(filePanel);

        return panel;
    }

    private JPanel statusPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel statusLabel = new JLabel("Status");
        JTextArea text = new JTextArea();

        panel.add(statusLabel, BorderLayout.PAGE_START);
        panel.add(text, BorderLayout.CENTER);

        return panel;
    }
}
