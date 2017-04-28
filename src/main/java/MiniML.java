import frontend.MiniMLFrame;
import utils.Logging.MiniMLLogger;

import javax.swing.*;

public class MiniML {

    public static final String APPNAME = "MiniML";

    public static void main(String[] args) {
        MiniMLLogger.INSTANCE.info("Starting " + APPNAME + ".");

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (Exception e) {
            MiniMLLogger.INSTANCE.debug("Failed to set the Java Look and Feel.");
            MiniMLLogger.INSTANCE.exception(e);
        }

        try {
            MiniMLFrame mainFrame = new MiniMLFrame(APPNAME);
        } catch(Exception e) {
            MiniMLLogger.INSTANCE.debug("Failed to load the MiniML SWING GUI.");
            MiniMLLogger.INSTANCE.exception(e);
            System.exit(1);
        }

        MiniMLLogger.INSTANCE.info(APPNAME + " is Ready.");
    }

}
