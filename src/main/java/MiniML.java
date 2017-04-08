import frontend.MiniMLFrame;
import utils.Logging.MiniMLLogger;
import workflow.context.MiniMLContext;

public class MiniML {

    public static final String APPNAME = "MiniML";

    public static void main(String[] args) {
        MiniMLLogger.INSTANCE.info("Starting " + APPNAME);

        try {
            MiniMLContext context = new MiniMLContext();
            MiniMLFrame mainFrame = new MiniMLFrame(APPNAME);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.exception(e);
        }

        MiniMLLogger.INSTANCE.info(APPNAME + " is Ready");
    }

}
