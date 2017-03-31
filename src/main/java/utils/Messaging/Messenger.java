package utils.Messaging;

import org.apache.log4j.Logger;

public enum Messenger {
    INSTANCE;
    public static final String APPNAME = "miniML";

    private final Logger logger = Logger.getLogger(APPNAME);

    public Logger getLogger()
    {
        return this.logger;
    }

}
