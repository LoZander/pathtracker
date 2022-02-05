package pathtracker.gui.wimp;

import pathtracker.framework.Charact;
import pathtracker.framework.Gui;
import pathtracker.framework.Tracker;
import pathtracker.framework.TrackerObserver;
import pathtracker.gui.commandGui.CommandLineInputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class WimpGui implements Gui, TrackerObserver {
    private final SizeStrategy sizeStrategy;
    private Tracker tracker;
    private JFrame mainFrame;
    private FontStrategy fontStrategy;
    private CommandLineInputHandler commandLineInputHandler;
    private JLabel roundCountLabel;
    private Container characterContainer;

    public WimpGui(Tracker tracker) {
        this.tracker = tracker;
        sizeStrategy = new ScalingSizeStrategy();
        tracker.addObserver(this);
        commandLineInputHandler = new CommandLineInputHandler();
        this.fontStrategy = new ScalingFontStrategy();
    }

    @Override
    public void run() {
        Container contentPane = createMainFrame();

        createMenuBar(mainFrame);
        createRoundCount(contentPane);
        createCharacterContainer(contentPane);

        updateCharacterList();

        createControlPanel(contentPane);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createControlPanel(Container parent) {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        createButtons(controlPanel);
        createCommandLine(controlPanel);
        parent.add(controlPanel, BorderLayout.SOUTH);
    }

    private Container createMainFrame() {
        mainFrame = new JFrame("Tracker");
        Container contentPane = mainFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        mainFrame.setMinimumSize(sizeStrategy.createDimension(160,300));
        return contentPane;
    }

    private void createMenuBar(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();

        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setFont(fontStrategy.getDefaultFont());
        JMenuItem nextTurnItem = new JMenuItem("Next turn");
        nextTurnItem.setFont(fontStrategy.getDefaultFont());
        nextTurnItem.addActionListener(e -> tracker.nextTurn());
        nextTurnItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,KeyEvent.CTRL_DOWN_MASK));
        toolsMenu.add(nextTurnItem);
        menuBar.add(toolsMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(fontStrategy.getDefaultFont());
        JMenuItem commandItem = new JMenuItem("Commands");
        commandItem.setFont(fontStrategy.getDefaultFont());
        commandItem.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame,
                "p [name] [initiative] to create a player character\n" +
                        "b [name] [initiative] to create an enemy character\n" +
                        "d [name] to delete a character\n" +
                        "r to advance the turn\n" +
                        "clear to clear the tracker"));
        helpMenu.add(commandItem);

        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
    }

    private void createRoundCount(Container parent) {
        roundCountLabel = new JLabel(tracker.getRound() + "");
        roundCountLabel.setFont(fontStrategy.getBigFont());
        roundCountLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        roundCountLabel.setHorizontalAlignment(JLabel.CENTER);

        parent.add(roundCountLabel, BorderLayout.NORTH);
    }

    private void createCharacterContainer(Container parent) {
        characterContainer = new JPanel();
        characterContainer.setLayout(new BoxLayout(characterContainer, BoxLayout.Y_AXIS));
        parent.add(characterContainer, BorderLayout.CENTER);
    }


    private void createButtons(Container parent) {
        JButton nextTurnButton = new JButton("Next turn");
        nextTurnButton.setFont(fontStrategy.getDefaultFont());
        nextTurnButton.addActionListener(e -> tracker.nextTurn());
        parent.add(nextTurnButton, BorderLayout.SOUTH);
        nextTurnButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    }

    private void createCommandLine(Container parent) {
        JTextField commandLine = new JTextField();
        commandLine.setFont(fontStrategy.getDefaultFont());
        commandLine.addActionListener(e -> {
            commandLineInputHandler.execute(tracker, commandLine.getText());
            commandLine.setText("");
        });
        parent.add(commandLine, BorderLayout.SOUTH);
    }



    @Override
    public void endOfTurn(Charact nextCharacter, int round) {
        roundCountLabel.setText(tracker.getRound() + "");
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
            characterContainer.add(new CharacterPanel(c, tracker, sizeStrategy, fontStrategy));
            characterContainer.add(Box.createRigidArea(sizeStrategy.createDimension(0,5)));
        });
        characterContainer.revalidate();
        characterContainer.repaint();
    }
}
