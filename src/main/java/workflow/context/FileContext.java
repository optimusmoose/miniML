package workflow.context;

import java.io.File;

public class FileContext extends AbstractParameterContext {

    public final static String INPUT_FILE_KEY = "INPUT_FILE_0";

    /**
     * Instantiate context with a null state
     *
     * @param parentContext ContextInterface
     */
    public FileContext(ContextInterface parentContext) {
        super(parentContext);
    }

    @Override
    boolean isValid() {
        return this.fileExists() && this.fileIsValid();
    }

    private boolean fileExists() {
        //TODO: this badly needs a test, possibly a try/catch
        return new File((String) this.value).isFile();
    }

    private boolean fileIsValid()
    {
        //TODO: write/call lint utility for file
        return true;
    }
}
