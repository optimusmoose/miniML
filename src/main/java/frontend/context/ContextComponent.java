package frontend.context;

import workflow.context.AbstractCompositeContext;

import javax.swing.*;

public class ContextComponent extends JComponent {

    private AbstractCompositeContext context;
    private JComponent component;

    public ContextComponent() {
        super();
    }

    public ContextComponent(AbstractCompositeContext context, JComponent component) {
        super();

        this.context = context;
        this.component = component;
    }

    public AbstractCompositeContext getContext() {
        return context;
    }

    public JComponent getComponent() {
        return component;
    }
}
