package utils.Logging;

import javafx.scene.control.TextArea;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender extends WriterAppender {

    private TextArea textArea;

    public TextAreaAppender(TextArea textArea) {
        super();
        this.textArea = textArea;
        this.setThreshold(Level.INFO);
        this.setLayout(new PatternLayout("%d{ISO8601} - %m%n"));
        MiniMLLogger.INSTANCE.getLogger().addAppender(this);
    }

    public void append(LoggingEvent loggingEvent) {
        final String logMessage = this.layout.format(loggingEvent);
        this.textArea.appendText(logMessage);
        this.textArea.positionCaret(this.textArea.getLength());
    }

}
