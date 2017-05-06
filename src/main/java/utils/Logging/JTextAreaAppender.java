package utils.Logging;

import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;

public class JTextAreaAppender extends WriterAppender {

    private JTextArea jTextArea;

    public JTextAreaAppender(JTextArea jTextArea) {
        super();
        this.jTextArea = jTextArea;
        this.setThreshold(Level.INFO);
        this.setLayout(new PatternLayout("%d{ISO8601} - %m%n"));
        MiniMLLogger.INSTANCE.getLogger().addAppender(this);
    }

    public void append(LoggingEvent loggingEvent) {
        final String logMessage = this.layout.format(loggingEvent);
        this.jTextArea.append(logMessage);
        this.jTextArea.setCaretPosition(this.jTextArea.getDocument().getLength());
    }
}
