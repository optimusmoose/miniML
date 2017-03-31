package frontend.context;

import utils.Logging.MiniMLLogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractContextActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        try {

        } catch (Exception exception) {
            MiniMLLogger.INSTANCE.error(exception);
        }
    }
}
