package pathtracker.gui.wimp;

import pathtracker.framework.Charact;
import pathtracker.framework.Tracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CharacterPanel extends JPanel {
    private final SizeStrategy sizeStrategy;
    private FontStrategy fontStrategy;
    public CharacterPanel(Charact character, Tracker tracker, SizeStrategy sizeStrategy, FontStrategy fontStrategy) {
        super();
        this.fontStrategy = fontStrategy;
        this.sizeStrategy = sizeStrategy;
        setLayout(new BorderLayout(6,6));

        JPanel acPanel = new JPanel();
        acPanel.setLayout(new BoxLayout(acPanel, BoxLayout.X_AXIS));
        acPanel.add(Box.createHorizontalStrut(6));
        acPanel.setOpaque(false);

        JLabel initiative = new JLabel(character.getInitiative() + "");
        initiative.setFont(fontStrategy.getBigFont());
        acPanel.add(initiative);

        add(acPanel, BorderLayout.WEST);

        JLabel name = new JLabel(character.getName());
        name.setFont(fontStrategy.getDefaultFont());
        add(name, BorderLayout.CENTER);

        setInTurn(character.equals(tracker.getCharacterInTurn()));

        setBackground(Color.LIGHT_GRAY);

        switch(character.getType()) {
            case ALLY:
                setBackground(new Color(199, 199, 199));
                break;
            case ENEMY:
                setBackground(new Color(232, 126, 126));
                break;
        }
        character.getType();
        setBorder(new LineBorder(Color.BLACK));
    }

    public void setInTurn(boolean inTurn) {
        int width = inTurn ? 200 : 160;
        setAbsoluteSize(sizeStrategy.createDimension(width,50));
    }

    private void setAbsoluteSize(Dimension size) {
        setMinimumSize(size);
        setMaximumSize(size);
    }
}
