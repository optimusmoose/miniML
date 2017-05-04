package utils.Logging;

import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;

public class JTextAreaAppender extends WriterAppender {

    private JTextArea jTextArea;

    public JTextAreaAppender(JTextArea jTextArea) {
        super();
        this.jTextArea = jTextArea;
    }

    public void append(LoggingEvent loggingEvent) {
        final String logMessage = this.layout.format(loggingEvent);
        System.out.println("test append!");
        this.jTextArea.append(logMessage);
    }
}
