package standard.gui.wimp;

import framework.Gui;
import framework.Tracker;
import standard.gui.commandGui.CommandLineInputHandler;

import javax.swing.*;
import java.awt.*;
import java.beans.EventHandler;

public class WimpGui implements Gui {
    private Tracker tracker;
    private JFrame mainFrame;
    private Font defaultFont;
    private CommandLineInputHandler commandLineInputHandler;

    public WimpGui(Tracker tracker) {
        this.tracker = tracker;
        this.commandLineInputHandler = new CommandLineInputHandler();
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Tracker");
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        mainFrame.setMinimumSize(new Dimension(400,800));
        defaultFont = new Font("Arial", Font.PLAIN, 20);

        Container characterContainer = new Container();
        characterContainer.setLayout(new BoxLayout(characterContainer, BoxLayout.Y_AXIS));
        contentPane.add(characterContainer, BorderLayout.CENTER);

        tracker.getCharacters().forEach(c -> characterContainer.add(new CustomLabel(c.getInitiative() + " " + c.getName())));

        SwingUtilities.updateComponentTreeUI(mainFrame);

        JTextField commandLine = new JTextField("test");
        contentPane.add(commandLine, BorderLayout.SOUTH);
        commandLine.addActionListener(e -> commandLineInputHandler.execute(tracker, commandLine.getText()));

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}

class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}
