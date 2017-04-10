package workflow;

import workflow.context.AbstractCompositeContext;

import java.util.HashMap;

/*
 * Workflow manager is a HashMap singleton for tracking contexts throughout the application by keys
 * It allows access to contexts based on key without having to be concerned about where in the composite
 * heirarchy the context in quetions lives.
 */
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
