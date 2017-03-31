import frontend.MiniMLFrame;
import utils.Messaging.Messenger;
import workflow.context.MiniMLContext;

public class MiniML {

    public static void main(String[] args) {
        Messenger.INSTANCE.getLogger().info("Hello World");

        try {
            MiniMLContext context = new MiniMLContext();
            MiniMLFrame mainFrame = new MiniMLFrame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
