package frontend;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {

    public ContainerPanel() {
        super();
        //TODO: configuration import
        setPreferredSize(new Dimension(1200, 600));

        this.addComponents();
    }

    private void addComponents()
    {
        Label myLabel = new Label("Hello World!");
        this.add(myLabel);
    }
}
