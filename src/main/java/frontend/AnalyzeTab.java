package frontend;

import utils.MessageConsole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mattpatera on 3/19/17.
 */
public class AnalyzeTab extends JLabel{

    public AnalyzeTab() {
        this.setLayout(new BorderLayout());
        this.add(consolePanel(), BorderLayout.PAGE_START);
        this.add(modelPanel(), BorderLayout.CENTER);
        this.add(timeRemainingPanel(), BorderLayout.PAGE_END);
    }

    /**
     * Console panel holds a live feed of the commands being used by the WEKA
     * API and what they return.
     * @return consolePanel
     */
    JPanel consolePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Console Output");
        JTextArea consoleText = new JTextArea("\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut faucibus consectetur accumsan. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rutrum metus eget quam ornare, quis laoreet urna bibendum. Aliquam ut enim ut purus convallis blandit. Integer tellus est, consectetur sed leo at, fermentum viverra quam. Pellentesque fringilla neque eu arcu malesuada, at dictum neque sagittis. Praesent et efficitur nulla. Sed faucibus ornare quam, in blandit dolor. Donec quis erat sed neque ullamcorper pellentesque at ut ex. Nulla nulla turpis, iaculis in rutrum non, facilisis at erat. Nullam tempor nunc at vulputate congue. Vivamus vel porta nibh. Donec facilisis posuere turpis a scelerisque. Fusce pellentesque velit in ligula laoreet rhoncus. Aliquam condimentum id metus vitae sollicitudin.\n" +
                "\n" +
                "Morbi mattis lorem odio, volutpat bibendum lorem ornare a. Aenean ullamcorper ligula sit amet porttitor imperdiet. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Interdum et malesuada fames ac ante ipsum primis in faucibus. Donec in neque eros. Vestibulum ac felis velit. Etiam eu risus odio. Nulla ut iaculis metus, at rutrum sem.\n" +
                "\n" +
                "Sed vitae magna est. Sed pulvinar risus sit amet tortor pellentesque tristique. Sed dui enim, accumsan vitae porta eget, consequat vel eros. Cras felis diam, rhoncus ut rutrum sit amet, pellentesque at erat. Ut eu nunc ipsum. Cras efficitur sodales libero eget ultrices. Donec sed nulla in nulla eleifend ultrices.\n" +
                "\n" +
                "Suspendisse consequat imperdiet dolor rhoncus laoreet. Praesent in auctor dui. Integer at pulvinar nisl, eu interdum magna. Maecenas sit amet felis fringilla, finibus ligula at, semper diam. Donec vitae felis eros. Donec vel molestie nulla, convallis dignissim mi. Sed vitae viverra lacus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Phasellus eu pretium lorem. Praesent nec sodales velit. Curabitur elit lorem, hendrerit pulvinar massa quis, sagittis tincidunt turpis. Proin congue ante vitae diam elementum, eu tempor metus laoreet. Etiam id mauris semper, porta odio ac, rhoncus elit. Quisque tempus, ligula nec pellentesque efficitur, massa nisi congue justo, non tempus turpis nunc id felis. Quisque eleifend purus non eros laoreet varius.\n" +
                "\n" +
                "Praesent quis congue dolor. Aliquam consequat porta facilisis. Etiam id lobortis enim, vel vehicula lacus. Fusce hendrerit et dui non efficitur. Pellentesque ultricies risus dui, ac varius sem consectetur ac. Donec et ligula quis tellus laoreet faucibus. Curabitur tincidunt odio nec odio auctor pellentesque. Nullam scelerisque, ligula in facilisis dictum, tellus felis aliquam orci, nec aliquam purus felis quis quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed tincidunt posuere sapien, non lobortis lorem blandit id. Nam risus mauris, euismod non vestibulum a, vehicula volutpat lorem. Nunc tristique magna eget mauris vehicula blandit. Proin eu mauris at eros ullamcorper scelerisque. ");
        JScrollPane consolePane = new JScrollPane(consoleText);
        consolePane.setPreferredSize(new Dimension(panel.getWidth(), 100)); // make this scale

        panel.add(title, BorderLayout.PAGE_START);
        panel.add(new JScrollPane(consoleText));

        MessageConsole console = new MessageConsole(consoleText);
        console.redirectOut();
        console.redirectError(Color.RED, null);

        return panel;
    }

    JPanel modelPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel bestGraph = new JLabel("Some Graph Here", SwingConstants.CENTER);
        JLabel graphInfoAndSave = new JLabel("Important graph info goes here and so does the save button", SwingConstants.CENTER);

        panel.add(bestGraph);
        panel.add(graphInfoAndSave);

        return panel;
    }

    JPanel timeRemainingPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel title = new JLabel("Time Remaining", SwingConstants.CENTER);
        JLabel timeRemaining = new JLabel("Time Goes Here", SwingConstants.CENTER);

        panel.add(title);
        panel.add(timeRemaining);

        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        return panel;
    }
}
