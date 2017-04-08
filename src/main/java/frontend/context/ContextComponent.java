package frontend.context;

import workflow.context.AbstractCompositeContext;

import javax.swing.*;

/*
 * This is not currently in use, the intent is to extend this for sections instead of JComp.
 */
public class ContextComponent extends JComponent {

    private AbstractCompositeContext context;

    public ContextComponent() {
        super();
    }

    public ContextComponent(AbstractCompositeContext context) {
        super();

        this.context = context;
    }

    public AbstractCompositeContext getContext() {
        return context;
    }
}
