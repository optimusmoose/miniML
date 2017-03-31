package frontend.context;

import frontend.ContainerPanel;
import junit.framework.TestCase;
import workflow.context.MiniMLContext;

import javax.swing.*;

public class ComponentProxyText extends TestCase {

    public void testProxy(){
        JFrame frame;
        frame = (JFrame) ComponentProxy.newInstance(new ContextComponent(
            new MiniMLContext(),
            new ContainerPanel()
        ));

        assertTrue(frame.getClass() == JFrame.class);
    }
}
