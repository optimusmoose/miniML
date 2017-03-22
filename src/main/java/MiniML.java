import frontend.MiniMLFrame;
import workflow.context.MiniMLContext;

public class MiniML {

    public static void main(String[] args) {
        System.out.println("Hello World!\n");

        try {
            MiniMLContext context = new MiniMLContext();
            MiniMLFrame mainFrame = new MiniMLFrame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
