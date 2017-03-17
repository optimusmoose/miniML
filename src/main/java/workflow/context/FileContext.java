package workflow.context;

import java.io.File;

public class FileContext extends AbstractParameterContext{

    /**
     * Instantiate context with a null state
     *
     * @param parentContext ContextInterface
     */
    FileContext(ContextInterface parentContext) {
        super(parentContext);
    }

    @Override
    boolean isValid() {
        return new File((String) fileName.getValue()).isFile();
    }
}
