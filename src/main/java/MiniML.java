import frontend.MiniMLFrame;
import utils.Logging.MiniMLLogger;
import workflow.context.MiniMLContext;

public class MiniML {

    public static void main(String[] args) {
        MiniMLLogger.INSTANCE.info("Hello World");

        try {
            MiniMLContext context = new MiniMLContext();
            MiniMLFrame mainFrame = new MiniMLFrame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
