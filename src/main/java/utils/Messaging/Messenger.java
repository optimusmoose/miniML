package utils.Messaging;

import config.MessageingConfig;
import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public enum Messenger {
    INSTANCE;

    private Queue<String> messages = new LinkedBlockingQueue<String>();
    private final Logger logger = Logger.getLogger(MessageingConfig.APPNAME);

    public Logger getLogger()
    {
        return this.logger;
    }

}
