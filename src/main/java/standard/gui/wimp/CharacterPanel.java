package standard.gui.wimp;

import framework.Charact;
import framework.Tracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CharacterPanel extends JPanel {
    public CharacterPanel(Charact character, Tracker tracker) {
        super();
        setLayout(new BorderLayout(6,6));

        JPanel acPanel = new JPanel();
        acPanel.setLayout(new BoxLayout(acPanel, BoxLayout.X_AXIS));
        acPanel.add(Box.createHorizontalStrut(6));
        acPanel.setOpaque(false);

        JLabel initiative = new CustomLabel(character.getInitiative() + "");
        initiative.setFont(new Font("Arial", Font.PLAIN, 30));
        acPanel.add(initiative);

        add(acPanel, BorderLayout.WEST);

        JLabel name = new CustomLabel(character.getName());
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
        int width = inTurn ? 180 : 150;
        setAbsoluteSize(new Dimension(width,60));
    }

    private void setAbsoluteSize(Dimension size) {
        setMinimumSize(size);
        setMaximumSize(size);
    }
}
