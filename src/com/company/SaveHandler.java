package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveHandler {
    private SaveHandler(){}

    public static String[] loadHighScores() {
        try {
            File f = new File("highscores");
            String[] scores = new String[10];
            if (!f.exists()) {
                writeNewHighScores();
            }
            Scanner fileReader = new Scanner(new File("highscores"));
            fileReader.nextLine();
            for (int i = 0; i < 10; i++) {
                if (fileReader.hasNextLine()) {
                    scores[i] = fileReader.nextLine();
                } else {
                    scores[i] = "0,---";
                }
            }
            return scores;
        } catch (FileNotFoundException e) {
            return new String[]{"0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---"};
        }
    }

    public static boolean writeNewHighScores() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("highscores")));
        } catch (IOException e) {
            return false;
        }
        out.println("You shouldn't be in here! Eh, whatever, you're only cheating yourself.");
        for (int i = 0; i < 10; i++) {
            out.println("0,---");
        }
        out.close();
        return true;
    }

    public static boolean writeHighScore(String newScore) {
        PrintWriter out = null;
        File f = new File("highscores");
        if (!f.exists()) {
            writeNewHighScores();
        }
        List<String> scores = new ArrayList<String>(List.of(loadHighScores()));
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("highscores")));
        } catch (IOException e) {
            return false;
        }


        int index = 0;
        for (String s : scores) {
            if (Integer.valueOf(s.substring(0, s.indexOf(","))) <= Integer.valueOf(newScore.substring(0, newScore.indexOf(",")))) {
                scores.add(index, newScore);
                break;
            }
            index++;
        }

        out.println("You shouldn't be in here! Eh, whatever, you're only cheating yourself.");
        for (int i = 0; i < 10; i++) {
            out.println(scores.get(i));
        }
        out.close();
        return true;
    }
}
