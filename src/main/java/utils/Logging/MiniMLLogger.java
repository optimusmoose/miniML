package utils.Logging;

import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

import java.io.PrintWriter;
import java.io.StringWriter;

public enum MiniMLLogger {
    INSTANCE;
    public static final String APPNAME = "miniML";

    private final Logger logger = Logger.getLogger(APPNAME);

    public Logger getLogger()
    {
        return this.logger;
    }

    public void trace(Object message) {
        this.logger.trace(message);
    }
    public void debug(Object message) {
        this.logger.debug(message);
    }
    public void info(Object message) {
        this.logger.info(message);
    }
    public void warn(Object message) {
        this.logger.warn(message);
    }
    public void error(Object message) {
        this.logger.error(message);
    }
    public void fatal(Object message) {
        this.logger.fatal(message);
    }

    public void exception(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        this.logger.error(sw.toString());
    }

    public void registerAppender(WriterAppender appender) {
        this.logger.addAppender(appender);
    }
}
