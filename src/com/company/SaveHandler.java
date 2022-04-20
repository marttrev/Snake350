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

public final class SaveHandler {
    public static final int LIST_SIZE = 10;
    private SaveHandler() {
    }

    public static String[] loadHighScores() {
        try {
            File f = new File("highscores");
            String[] scores = new String[LIST_SIZE];
            if (!f.exists()) {
                writeNewHighScores();
            }
            Scanner fileReader = new Scanner(new File("highscores"), "UTF-8");
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

    public static boolean writeNewHighScores() {
        Writer fstream = null;
        BufferedWriter out = null;
        try {
            fstream = new OutputStreamWriter(
                    new FileOutputStream(
                            "highscores", false),
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

    public static boolean writeHighScore(final String newScore) {
        Writer fstream = null;
        BufferedWriter out = null;
        File f = new File("highscores");
        if (!f.exists()) {
            writeNewHighScores();
        }
        List<String> scores = new ArrayList<String>(List.of(loadHighScores()));
        try {
            fstream = new OutputStreamWriter(
                    new FileOutputStream(
                            "highscores", false),
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
