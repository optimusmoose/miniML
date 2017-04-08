package frontend;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MiniMLFrame extends JFrame {

    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;

    public MiniMLFrame(String text) {
        super(text);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(APP_WIDTH, APP_HEIGHT));//TODO: configs
        this.addMainPanelToFrame();
        this.pack();
        this.setVisible(true);
    }

    private void addMainPanelToFrame()
    {
        ContainerPanel mainPanel = new ContainerPanel();
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
}
