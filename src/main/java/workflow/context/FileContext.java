package workflow.context;

import utils.Logging.MiniMLLogger;

import java.io.File;

public class FileContext extends ParameterContext {

    public final static String INPUT_FILE_KEY = "INPUT_FILE_0";

    /**
     * Instantiate context with a null state
     *
     * @param parentContext ContextInterface
     */
    public FileContext(ContextInterface parentContext, String key) {
        super(parentContext, key);
    }

    @Override
    protected boolean isValid() {
        if(super.isValid()) {
            return this.fileExists() && this.fileIsValid();
        }
        return false;
    }

    private boolean fileExists() {
        try {
            return new File((String) this.value).isFile();
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }
        return false;
    }

    private boolean fileIsValid()
    {
        //TODO: write/call lint utility for file
        return true;
    }
}
