package pathtracker.demos;

import pathtracker.gui.wimp.FontStrategy;
import pathtracker.gui.wimp.ScalingFontStrategy;

import javax.swing.*;
import java.awt.*;

public class FontScalingStrategyDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Font scaling");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        FontStrategy fontStrategy = new ScalingFontStrategy();
        Font defaultFont = fontStrategy.getDefaultFont();
        Font smallFont = fontStrategy.getSmallFont();
        Font bigFont = fontStrategy.getBigFont();
        Font titleFont = fontStrategy.getTitleFont();

        JLabel defaultLabel = new JLabel("Default");
        defaultLabel.setFont(defaultFont);
        contentPane.add(defaultLabel);

        JLabel smallLabel = new JLabel("Small");
        smallLabel.setFont(smallFont);
        contentPane.add(smallLabel);

        JLabel bigLabel = new JLabel("Big");
        bigLabel.setFont(bigFont);
        contentPane.add(bigLabel);

        JLabel titleLabel = new JLabel("Title");
        titleLabel.setFont(titleFont);
        contentPane.add(titleLabel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
