package frontend.context;

import workflow.context.AbstractCompositeContext;

import javax.swing.*;

public class ContextComponent extends JComponent {

    private AbstractCompositeContext context;
    private JComponent component;
    private Class actionListener;

    public ContextComponent(AbstractCompositeContext context, JComponent component, Class actionListener) {
        super();
        this.context = context;
        this.component = component;
        this.actionListener = this.actionListener;
    }

    public AbstractCompositeContext getContext() {
        return context;
    }

    public JComponent getComponent() {
        return component;
    }

    public Class getActionListener() {
        return actionListener;
    }
}
