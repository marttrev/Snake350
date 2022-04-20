package com.company;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A front-end configuration tool for the Snake game, giving GUI
 * controls for difficulty, level select, colors, and a way to
 * view high scores.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public final class MenuPanel extends JPanel implements ActionListener {
    /**
     * A Dimension to be used in conjunction with the
     * Box.createRigidArea method. This is the larger of
     * the two.
     */
    private static final Dimension LARGE_DIMENSION =
            new Dimension(0, 20);
    /**
     * A Dimension to be used in conjunction with the
     * Box.createRigidArea method. This is the smaller of
     * the two.
     */
    private static final Dimension SMALL_DIMENSION =
            new Dimension(0, 10);
    /** An array containing the names of the difficulties. */
    private final String[] difficulty = {"Easy", "Medium", "Hard", "Expert"};
    /** An array containing the names of the levels. */
    private final String[] levels = {"Tiny", "Small", "Medium",
            "Large", "Giant"};
    /** An array containing the names of the colors. */
    private final String[] colors = {"Red", "Orange", "Yellow",
            "Green", "Blue", "Purple", "Black", "White"};
    /** A drop-down containing the difficulties. */
    private final JComboBox<String> diffBox = new JComboBox<String>(difficulty);
    /** A drop-down containing the level names. */
    private final JComboBox<String> levelBox = new JComboBox<String>(levels);
    /** A drop-down containing the colors for the gridlines. */
    private final JComboBox<String> lineColorBox =
            new JComboBox<String>(colors);
    /** A drop-down containing the colors for the snake body. */
    private final JComboBox<String> bodyColorBox =
            new JComboBox<String>(colors);
    /** A drop-down containing the colors for the snake head. */
    private final JComboBox<String> headColorBox =
            new JComboBox<String>(colors);
    /** A drop-down containing the colors for the food. */
    private final JComboBox<String> foodColorBox =
            new JComboBox<String>(colors);
    /** A drop-down containing the colors for the background. */
    private final JComboBox<String> bgColorBox =
            new JComboBox<String>(colors);
    /** A button to start the game. */
    private final JButton start = new JButton("Start");
    /** A button to view the high scores. */
    private final JButton hScores = new JButton("High Scores");

    /**
     * Constructor. Applies a layout to the panel, adds all the elements,
     * spaces the elements out, and prepares the buttons to respond to
     * actions.
     */
    public MenuPanel() {
        // Apply layout
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);

        // Prep buttons
        start.addActionListener(this);
        hScores.addActionListener(this);

        // Add items
        add(new JLabel("Select desired options, then "
                + "click Start to begin the game.            "));
        /* Each of these is for spacing. This one is larger
         * than the rest so as to keep the title more separate
         * from the other options.
         */
        add(Box.createRigidArea(LARGE_DIMENSION));
        add(new JLabel("Select your difficulty:"));
        add(diffBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your level:"));
        add(levelBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your background color:"));
        bgColorBox.setSelectedItem("Black");
        add(bgColorBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your line color:"));
        lineColorBox.setSelectedItem("White");
        add(lineColorBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your snake color:"));
        bodyColorBox.setSelectedItem("White");
        add(bodyColorBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your snake head color:"));
        headColorBox.setSelectedItem("Green");
        add(headColorBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(new JLabel("Select your food color:"));
        foodColorBox.setSelectedItem("Red");
        add(foodColorBox);
        add(Box.createRigidArea(SMALL_DIMENSION));
        add(start);
        add(hScores);
    }

    /**
     * Responds to presses of the two buttons in the panel.
     * @param event The ActionEvent sent by the button that has
     *              been clicked.
     */
    @Override
    public void actionPerformed(final ActionEvent event) {
        // When the start button is clicked.
        if (event.getSource().equals(start)) {
            int diff;
            int level;
            Color lColor;
            Color sColor;
            Color sHeadColor;
            Color fColor;
            Color bgColor;

            // Read in selected values from the drop-downs
            diff = diffBox.getSelectedIndex();
            level = levelBox.getSelectedIndex();
            lColor = colorDeterminer(
                    (String) lineColorBox.getSelectedItem());
            sColor = colorDeterminer(
                    (String) bodyColorBox.getSelectedItem());
            sHeadColor = colorDeterminer(
                    (String) headColorBox.getSelectedItem());
            fColor = colorDeterminer(
                    (String) foodColorBox.getSelectedItem());
            bgColor = colorDeterminer(
                    (String) bgColorBox.getSelectedItem());

            // Feed those values into the SnakeFrame constructor.
            new SnakeFrame(diff, level, lColor, sColor,
                    sHeadColor, fColor, bgColor);
        }
        // When the high scores button is clicked.
        if (event.getSource().equals(hScores)) {
            // Load scores
            java.util.List<String> scoresList = new ArrayList<String>(
                    List.of(SaveHandler.loadHighScores()));

            // Format scores
            scoresList = SaveHandler.formatScores(scoresList);
            String hsOutput = "";
            for (String s : scoresList) {
                hsOutput += s;
            }
            // Output scores
            JOptionPane.showMessageDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(
                            this), hsOutput);
        }
    }

    /**
     * A helper method for the actionPerformed method, allowing for
     * easy translation from strings in the drop-downs to actual
     * Color values.
     * @param color The string indicating the desired color.
     * @return The actual Color value corresponding to the input.
     */
    private Color colorDeterminer(final String color) {
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
