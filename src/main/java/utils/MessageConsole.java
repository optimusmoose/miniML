package utils;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.io.PrintStream;
import java.lang.annotation.Documented;

/**
 * Created by mattpatera on 3/20/17.
 */
public class MessageConsole {

    private JTextArea messageBox;
    private Document document;

    public MessageConsole(JTextArea messageBox) {
        this.messageBox = messageBox;
        this.document = messageBox.getDocument();
        messageBox.setEditable(false);
    }

    public void redirectOut() {
        redirectOut(null, null);
    }

    public void redirectOut(Color textColor, PrintStream printStream) {

    }

    public void redirectError() {
        redirectError(null, null);
    }

    public void redirectError(Color textColor, PrintStream printStream) {

    }

}
