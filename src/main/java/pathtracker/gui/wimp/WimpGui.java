package pathtracker.gui.wimp;

import pathtracker.framework.Charact;
import pathtracker.framework.Gui;
import pathtracker.framework.Tracker;
import pathtracker.framework.TrackerObserver;
import pathtracker.gui.commandGui.CommandLineInputHandler;

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
        Container contentPane = createMainFrame();
        defaultFont = new Font("Arial", Font.PLAIN, 20);

        createMenuBar();
        createRoundCount();
        createCharacterContainer();
        updateCharacterList();
        createCommandLine(contentPane);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createCharacterContainer() {
        Container contentPane = mainFrame.getContentPane();
        characterContainer = new JPanel();
        characterContainer.setLayout(new BoxLayout(characterContainer, BoxLayout.Y_AXIS));
        contentPane.add(characterContainer, BorderLayout.CENTER);
    }

    private Container createMainFrame() {
        mainFrame = new JFrame("Tracker");
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        mainFrame.setMinimumSize(new Dimension(400,800));
        return contentPane;
    }

    private void createCommandLine(Container contentPane) {
        JTextField commandLine = new JTextField();
        commandLine.setFont(defaultFont);
        commandLine.addActionListener(e -> {
            commandLineInputHandler.execute(tracker, commandLine.getText());
            commandLine.setText("");
        });
        contentPane.add(commandLine, BorderLayout.SOUTH);
    }

    private void createRoundCount() {
        Container contentPane = mainFrame.getContentPane();
        roundCountLabel = new CustomLabel("0");
        roundCountLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        roundCountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        roundCountLabel.setHorizontalAlignment(JLabel.CENTER);

        contentPane.add(roundCountLabel, BorderLayout.NORTH);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenuItem helpMenu = new JMenuItem("Help");
        helpMenu.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame,
                "p [name] [initiative] to create a player character\n" +
                        "b [name] [initiative] to create an enemy character\n" +
                        "d [name] to delete a character\n" +
                        "r to advance the turn\n" +
                        "clear to clear the tracker"));
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    @Override
    public void endOfTurn(Charact nextCharacter, int round) {
        roundCountLabel.setText(round + "");
        updateCharacterList();
    }

    @Override
    public void characterListChanged() {
        updateCharacterList();
    }

    @Override
    public void characterChanged(Charact character) {

    }

    @Override
    public void clear() {
        roundCountLabel.setText("0");
        updateCharacterList();
    }

    private void updateCharacterList() {
        characterContainer.removeAll();
        tracker.getCharacters().forEach(c -> {
            characterContainer.add(new CharacterPanel(c,tracker));
            characterContainer.add(Box.createRigidArea(new Dimension(0,10)));
        });
        characterContainer.revalidate();
        characterContainer.repaint();
    }
}
