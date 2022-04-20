package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel implements ActionListener {
    private final String[] difficulty = {"Easy", "Medium", "Hard", "Expert"};
    private final String[] levels = {"Tiny", "Small", "Medium", "Large", "Giant"};
    private final String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black", "White"};
    private boolean startGame = false;
    private final JComboBox<String> diffBox = new JComboBox<String>(difficulty);
    private final JComboBox<String> levelBox = new JComboBox<String>(levels);
    private final JComboBox<String> lineColorBox = new JComboBox<String>(colors);
    private final JComboBox<String> bodyColorBox = new JComboBox<String>(colors);
    private final JComboBox<String> headColorBox = new JComboBox<String>(colors);
    private final JComboBox<String> foodColorBox = new JComboBox<String>(colors);
    private final JComboBox<String> bgColorBox = new JComboBox<String>(colors);
    private final JButton start = new JButton("Start");
    private final JButton hScores = new JButton("High Scores");

    public MenuPanel() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);

        start.addActionListener(this);
        hScores.addActionListener(this);

        add(new JLabel("Select desired options, then click Start to begin the game.            "));
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(new JLabel("Select your difficulty:"));
        add(diffBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your level:"));
        add(levelBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your background color:"));
        bgColorBox.setSelectedItem("Black");
        add(bgColorBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your line color:"));
        lineColorBox.setSelectedItem("White");
        add(lineColorBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your snake color:"));
        bodyColorBox.setSelectedItem("White");
        add(bodyColorBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your snake head color:"));
        headColorBox.setSelectedItem("Green");
        add(headColorBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel("Select your food color:"));
        foodColorBox.setSelectedItem("Red");
        add(foodColorBox);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(start);
        add(hScores);
    }

    @Override
    /* Can't get this working, using keystroke workaround for now. */
    public void actionPerformed(final ActionEvent event) {
        if (event.getSource().equals(start)) {
            int diff;
            int level;
            Color lColor;
            Color sColor;
            Color sHeadColor;
            Color fColor;
            Color bgColor;

            diff = diffBox.getSelectedIndex();
            level = levelBox.getSelectedIndex();
            lColor = colorDeterminer((String) lineColorBox.getSelectedItem());
            sColor = colorDeterminer((String) bodyColorBox.getSelectedItem());
            sHeadColor = colorDeterminer((String) headColorBox.getSelectedItem());
            fColor = colorDeterminer((String) foodColorBox.getSelectedItem());
            bgColor = colorDeterminer((String) bgColorBox.getSelectedItem());

            new SnakeFrame(diff, level, lColor, sColor, sHeadColor, fColor, bgColor);
        }
        if (event.getSource().equals(hScores)) {
            // Load scores
            java.util.List<String> scoresList = new ArrayList<String>(List.of(SaveHandler.loadHighScores()));

            // Format scores
            scoresList = SaveHandler.formatScores(scoresList);
            String hsOutput = "";
            for (String s : scoresList) {
                hsOutput += s;
            }
            JOptionPane.showMessageDialog((JFrame)SwingUtilities.getWindowAncestor(this), hsOutput);
        }
        }

    private Color colorDeterminer(String color) {
        if (color == null) {
            return Color.BLACK;
        } else if (color.equals("Red")) {
            return Color.RED;
        } else if (color.equals("Yellow")) {
            return Color.YELLOW;
        } else if (color.equals("Orange")) {
            return Color.ORANGE;
        } else if (color.equals("Green")) {
            return Color.GREEN;
        } else if (color.equals("Blue")) {
            return Color.BLUE;
        } else if (color.equals("Purple")) {
            return Color.MAGENTA;
        } else if (color.equals("White")) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
