package standard.gui.wimp;

import framework.Charact;
import framework.Gui;
import framework.Tracker;
import framework.TrackerObserver;
import standard.gui.commandGui.CommandLineInputHandler;

import javax.swing.*;
import java.awt.*;

public class WimpGui implements Gui, TrackerObserver {
    private Tracker tracker;
    private JFrame mainFrame;
    private Font defaultFont;
    private CommandLineInputHandler commandLineInputHandler;
    private JLabel roundCountLabel;
    private Container characterContainer;

    public WimpGui(Tracker tracker) {
        this.tracker = tracker;
        tracker.addObserver(this);
        this.commandLineInputHandler = new CommandLineInputHandler();
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Tracker");
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        mainFrame.setMinimumSize(new Dimension(400,800));
        defaultFont = new Font("Arial", Font.PLAIN, 20);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem helpMenu = new JMenuItem("Help");
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);

        roundCountLabel = new CustomLabel("0");
        roundCountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        contentPane.add(roundCountLabel, BorderLayout.NORTH);

        characterContainer = new Container();
        characterContainer.setLayout(new BoxLayout(characterContainer, BoxLayout.Y_AXIS));
        contentPane.add(characterContainer, BorderLayout.CENTER);

        tracker.getCharacters().forEach(c -> characterContainer.add(new CustomLabel(c.getInitiative() + " " + c.getName())));

        JTextField commandLine = new JTextField();
        commandLine.setFont(defaultFont);
        contentPane.add(commandLine, BorderLayout.SOUTH);
        commandLine.addActionListener(e -> commandLineInputHandler.execute(tracker, commandLine.getText()));


        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    @Override
    public void endOfTurn(Charact nextCharacter, int round) {
        roundCountLabel.setText(round + "");
    }

    @Override
    public void characterListChanged() {
        updateCharacterList();
    }

    @Override
    public void characterChanged(Charact character) {

    }

    private void updateCharacterList() {
        characterContainer.removeAll();
        tracker.getCharacters().forEach(c -> characterContainer.add(new CustomLabel(c.getInitiative() + " " + c.getName())));
        characterContainer.revalidate();
        characterContainer.repaint();
    }
}

class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}
