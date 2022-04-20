package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages all interactions with save data for the Snake game.
 *
 * @author Lucas Champoux, Trevor Martin, Raunak Shahi
 * @version 1.0
 */
public final class SaveHandler {
    /** The number of high scores to be saved. */
    public static final int LIST_SIZE = 10;
    /** The file to read and write high scores from. */
    public static final File FILE = new File("highscores");
    /** Default constructor. Do not instantiate this class. */
    private SaveHandler() {
    }

    /**
     * Reads the high scores from FILE, then builds a String[]
     * representation of them.
     * @return an array containing the contents of FILE, each
     *         element in the format "SCORE,NAME". If FILE
     *         cannot be read, returns an array of all 0
     *         scores.
     */
    public static String[] loadHighScores() {
        try {
            String[] scores = new String[LIST_SIZE];
            if (!FILE.exists()) {
                writeNewHighScores();
            }
            Scanner fileReader = new Scanner(FILE, "UTF-8");
            fileReader.nextLine();
            for (int i = 0; i < LIST_SIZE; i++) {
                if (fileReader.hasNextLine()) {
                    scores[i] = fileReader.nextLine();
                } else {
                    scores[i] = "0,---";
                }
            }
            return scores;
        } catch (FileNotFoundException e) {
            return new String[]{"0,---", "0,---", "0,---",
                    "0,---", "0,---", "0,---", "0,---",
                    "0,---", "0,---", "0,---"};
        }
    }

    /**
     * Writes a default set of high scores to initialize the
     * file FILE.
     * @return true if successful, false otherwise.
     */
    public static boolean writeNewHighScores() {
        Writer fstream = null;
        BufferedWriter out = null;
        try {
            fstream = new OutputStreamWriter(
                    new FileOutputStream(
                            FILE, false),
                    StandardCharsets.UTF_8);
            out = new BufferedWriter(fstream);

            out.write("You shouldn't be in here! "
                    + "Eh, whatever, you're only cheating yourself.\n");
            for (int i = 0; i < LIST_SIZE; i++) {
                out.write("0,---\n");
            }
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Adds the score passed in to FILE, in the correct position on the
     * leaderboard, assuming it is within the top LIST_SIZE scores.
     * @param newScore The score to be added, formatted as "SCORE,NAME"
     * @return true if successful, false otherwise.
     */
    public static boolean writeHighScore(final String newScore) {
        Writer fstream = null;
        BufferedWriter out = null;
        if (!FILE.exists()) {
            writeNewHighScores();
        }
        List<String> scores = new ArrayList<String>(List.of(loadHighScores()));
        try {
            fstream = new OutputStreamWriter(
                    new FileOutputStream(
                            FILE, false),
                    StandardCharsets.UTF_8);
            out = new BufferedWriter(fstream);

            int index = 0;
            for (String s : scores) {
                if (Integer.parseInt(
                        s.substring(0, s.indexOf(",")))
                        <= Integer.parseInt(
                                newScore.substring(0, newScore.indexOf(",")))) {
                    scores.add(index, newScore);
                    break;
                }
                index++;
            }

            out.write("You shouldn't be in here! "
                    + "Eh, whatever, you're only cheating yourself.\n");
            for (int i = 0; i < LIST_SIZE; i++) {
                out.write(scores.get(i) + "\n");
            }
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Takes a list of scores formatted in storage format and
     * returns a list of the same scores formatted for output
     * and view by the user.
     * @param list the list of scores to be formatted.
     * @return a list of the same scores formatted for output.
     */
    public static List<String> formatScores(final List<String> list) {
        List<String> formattedList = new ArrayList<String>();
        formattedList.add("High Scores:\n");
        for (int i = 0; i < LIST_SIZE; i++) {
            String temp = list.get(i);
            String output = "";
            output += NumberFormat.getInstance().format(
                    Integer.parseInt(temp.substring(0, temp.indexOf(","))));
            output += "  -  ";
            output += temp.substring(temp.indexOf(",") + 1);
            output += "\n";
            formattedList.add(output);
        }
        return formattedList;
    }
}
