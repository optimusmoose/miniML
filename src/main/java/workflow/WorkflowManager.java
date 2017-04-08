package workflow;

import workflow.context.AbstractCompositeContext;

import java.util.HashMap;

public enum WorkflowManager {
    INSTANCE;

    HashMap<String, AbstractCompositeContext> contextMap = new HashMap<String, AbstractCompositeContext>();

    public AbstractCompositeContext getContextByKey(String key) {
        return this.contextMap.get(key);
    }

    public void registerContext(AbstractCompositeContext context) {
        this.contextMap.put(context.getKey(), context);
    }

}
