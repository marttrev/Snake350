package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MenuPanel() {

        StartAction startAction = new StartAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "startAction");
        this.getActionMap().put("startAction", startAction);

        //start.addActionListener(this);
        add(new JLabel("Select desired options, then press ENTER or RETURN to begin."));
        add(new JSeparator(SwingConstants.VERTICAL));
        add(new JLabel("Select your difficulty:"));
        add(diffBox);
        add(new JLabel("Select your level:"));
        add(levelBox);
        add(new JLabel("Select your background color:"));
        add(bgColorBox);
        add(new JLabel("Select your line color:"));
        add(lineColorBox);
        add(new JLabel("Select your snake color:"));
        add(bodyColorBox);
        add(new JLabel("Select your snake head color:"));
        add(headColorBox);
        add(new JLabel("Select your food color:"));
        add(foodColorBox);
        //add(new JButton("Start"));
    }

    public boolean startGame() {
        boolean x = startGame;
        return x;
    }

    @Override
    /* Can't get this working, using keystroke workaround for now. */
    public void actionPerformed(final ActionEvent event) {
        //new SnakeFrame(1, 1, null, null, null, null);
    }

    public class StartAction extends AbstractAction {
        int diff;
        int level;
        Color lColor;
        Color sColor;
        Color sHeadColor;
        Color fColor;
        Color bgColor;

        public void actionPerformed(final ActionEvent event) {
            diff = diffBox.getSelectedIndex();
            level = levelBox.getSelectedIndex();
            lColor = colorDeterminer((String) lineColorBox.getSelectedItem());
            sColor = colorDeterminer((String)bodyColorBox.getSelectedItem());
            sHeadColor = colorDeterminer((String)headColorBox.getSelectedItem());
            fColor = colorDeterminer((String)foodColorBox.getSelectedItem());
            bgColor = colorDeterminer((String)bgColorBox.getSelectedItem());

            new SnakeFrame(diff, level, lColor, sColor, sHeadColor, fColor, bgColor);
        }

        private Color colorDeterminer(String color) {
            if (color.equals("Red")) {
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
}
