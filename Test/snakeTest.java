import com.company.GameBoard;
import com.company.SaveHandler;
import com.company.SnakePanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class snakeTest {

    @Before
    public void resetSaves() {
        SaveHandler.writeNewHighScores();
    }


    @Test
    public void testConstructor() {
        GameBoard board = new GameBoard(2, 2);
        Assert.assertEquals(2, board.getXpixels());
        Assert.assertEquals(2, board.getYpixels());
        Assert.assertEquals(2, board.getHead().getXCoord());
        Assert.assertEquals(1, board.getHead().getDirection());
        Assert.assertTrue(board.getHead().isHead());
        Assert.assertEquals(0, board.getHead().getYCoord());
        Assert.assertEquals(3, board.getSnakeLength());
    }

    @Test
    public void testGetFoodXCoord() {
        GameBoard board = new GameBoard(23, 23);
        int xCoord = (int)Math.random()*23;
        board.setFoodXCoord(xCoord);
        Assert.assertEquals(xCoord, board.getFoodXCoord());
    }

    @Test
    public void testGetFoodYCoord() {
        GameBoard board = new GameBoard(23, 23);
        int yCoord = (int)Math.random()*23;
        board.setFoodYCoord(yCoord);
        Assert.assertEquals(yCoord, board.getFoodYCoord());
    }

    @Test
    public void testMoveEast() {
        GameBoard board = new GameBoard(3, 0);
        board.setFoodXCoord(0);
        board.moveSnake();
        Assert.assertEquals(3, board.getHead().getXCoord());
        Assert.assertEquals(0, board.getHead().getYCoord());
        Assert.assertEquals(2, board.getHead().getNext().getXCoord());
        Assert.assertEquals(0, board.getHead().getNext().getYCoord());
        Assert.assertEquals(1, board.getTail().getXCoord());
        Assert.assertEquals(0, board.getTail().getYCoord());
    }

    @Test
    public void testMoveSouth() {
        GameBoard board = new GameBoard(2, 1);
        board.setFoodXCoord(0);
        board.setHeadDirection(2);
        board.moveSnake();
        Assert.assertEquals(2, board.getHead().getXCoord());
        Assert.assertEquals(1, board.getHead().getYCoord());
        Assert.assertEquals(2, board.getHead().getNext().getXCoord());
        Assert.assertEquals(0, board.getHead().getNext().getYCoord());
        Assert.assertEquals(1, board.getTail().getXCoord());
        Assert.assertEquals(0, board.getTail().getYCoord());
    }

    @Test
    public void testMoveWest() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(0);
        board.setHeadDirection(3);
        board.moveSnake();
        Assert.assertEquals(1, board.getHead().getXCoord());
        Assert.assertEquals(0, board.getHead().getYCoord());
        Assert.assertEquals(2, board.getHead().getNext().getXCoord());
        Assert.assertEquals(0, board.getHead().getNext().getYCoord());
        Assert.assertEquals(1, board.getTail().getXCoord());
        Assert.assertEquals(0, board.getTail().getYCoord());
    }

    @Test
    public void testMoveNorth() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(0);
        board.setHeadDirection(0);
        board.moveSnake();
        Assert.assertEquals(2, board.getHead().getXCoord());
        Assert.assertEquals(-1, board.getHead().getYCoord());
        Assert.assertEquals(2, board.getHead().getNext().getXCoord());
        Assert.assertEquals(0, board.getHead().getNext().getYCoord());
        Assert.assertEquals(1, board.getTail().getXCoord());
        Assert.assertEquals(0, board.getTail().getYCoord());
    }

    @Test
    public void testGenerateFood() {
        GameBoard board = new GameBoard(2, 0);
        Assert.assertEquals(board.generateFood(), board.getFoodXCoord());
    }

    @Test
    public void testFoodCollision() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(2);
        board.setFoodYCoord(0);
        Assert.assertTrue(board.checkFoodCollision());
        Assert.assertEquals(board.getFoodXCoord(), 0);
        Assert.assertFalse(board.checkFoodCollision());
    }

    @Test
    public void testCollisionSelf() {
        GameBoard board = new GameBoard(2, 0);
        board.setHeadDirection(3);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }

    @Test
    public void testCollisionNorth() {
        GameBoard board = new GameBoard(2, 0);
        board.setHeadDirection(0);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }

    @Test
    public void testCollisionEast() {
        GameBoard board = new GameBoard(2, 0);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }

    @Test
    public void testCollisionSouth() {
        GameBoard board = new GameBoard(2, 0);
        board.setHeadDirection(2);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }

    @Test
    public void testCollisionWest() {
        GameBoard board = new GameBoard(2, 1);
        board.setFoodXCoord(0);
        board.setFoodYCoord(0);
        board.setHeadDirection(2);
        board.moveSnake();
        board.setHeadDirection(3);
        board.moveSnake();
        board.moveSnake();
        Assert.assertFalse(board.isDead());
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }

    @Test
    public void testHasWon() {
        GameBoard board = new GameBoard(3, 0);
        board.setFoodXCoord(2);
        board.setFoodYCoord(0);
        Assert.assertFalse(board.hasWon());
        board.checkEaten();
        Assert.assertTrue(board.hasWon());
    }

    @Test
    public void testCheckEaten() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(2);
        board.setFoodYCoord(0);
        Assert.assertTrue(board.checkEaten());
        Assert.assertFalse(board.checkEaten());
    }

    @Test
    public void testWin() {
        SnakePanel panel = new SnakePanel(1, 1, null, null, null, null, null);
        Assert.assertTrue(panel.win());
        File f = new File("highscores");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void testSaveHandler() {
        // Load without file existing (remember to delete highscores)
        SaveHandler.loadHighScores();
        File f = new File("highscores");
        Assert.assertTrue(f.exists());
        // Load
        Assert.assertEquals(new String[]{"0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---"}, SaveHandler.loadHighScores());
        // Save above
        SaveHandler.writeHighScore("420,TEST");
        Assert.assertEquals(new String[]{"420,TEST", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---"}, SaveHandler.loadHighScores());
        //Save below
        SaveHandler.writeHighScore("69,TEST");
        Assert.assertEquals(new String[]{"420,TEST", "69,TEST", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---"}, SaveHandler.loadHighScores());
        //Save at bottom
        SaveHandler.writeHighScore("1,TEST");
        Assert.assertEquals(new String[]{"420,TEST", "69,TEST", "1,TEST", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---", "0,---"}, SaveHandler.loadHighScores());
    }

    @Test
    public void testFormatScores() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("0,---");
        }
        list = SaveHandler.formatScores(list);
        Assert.assertEquals("High Scores:\n", list.get(0));
        for (int i = 1; i < 11; i++) {
            Assert.assertEquals("0  -  ---\n", list.get(i));
        }
    }

    @Test
    public void testLose() {
        SnakePanel panel = new SnakePanel(1, 1, null, null, null, null, null);
        Assert.assertTrue(panel.lose());
        File f = new File("highscores");
        Assert.assertTrue(f.exists());
    }

}



