package frontend;

import javax.swing.*;
import java.awt.*;

public class MiniMLFrame extends JFrame {

    public MiniMLFrame(String key) {
        super(key);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.addMainPanelToFrame();

        this.pack();
        this.setVisible(true);
    }

    private void addMainPanelToFrame()
    {
        MiniMLContainerPanel mainPanel = new MiniMLContainerPanel();
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
}
