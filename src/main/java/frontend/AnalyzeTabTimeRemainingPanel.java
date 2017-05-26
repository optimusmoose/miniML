package frontend;

import workflow.Keys;
import workflow.WorkflowManager;
import workflow.context.ParameterContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class AnalyzeTabTimeRemainingPanel extends JPanel{
    private Timer timer;
    private long startTime = -1;
    private long duration = 5000;

    private JLabel label;

    public void startTimer(){
        this.setDuration();
        this.timer.start();
    }

    public void setDuration() {
        ParameterContext et = (ParameterContext) WorkflowManager.INSTANCE.getContextByKey(Keys.EstimatedTimeConfig);
        this.duration = Integer.parseInt(et.getValue().toString()) * 60 * 1000; //milliseconds of course
    }

    public AnalyzeTabTimeRemainingPanel() {
        setLayout(new GridBagLayout());
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                label.setText(df.format(duration - clockTime));
            }
        });
        timer.setInitialDelay(0);
        label = new JLabel("...");
        add(label);
    }
}

