package frontend;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MiniMLFrame extends JFrame {

    public MiniMLFrame() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //this.setLayout(new GridBagLayout());
        //TODO: gridbag constraints
        this.setSize(new Dimension(800, 600));
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
