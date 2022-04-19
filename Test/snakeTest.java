import com.company.GameBoard;
import com.company.SnakeFrame;
import com.company.SnakePanel;
import org.junit.Assert;
import org.junit.Test;

public class snakeTest {
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
        board.checkEaten(board.getTail().getXCoord(), board.getTail().getYCoord());
        Assert.assertTrue(board.hasWon());
    }

    @Test
    public void testCheckEaten() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(2);
        board.setFoodYCoord(0);
        Assert.assertTrue(board.checkEaten(board.getTail().getXCoord(), board.getTail().getYCoord()));
        Assert.assertFalse(board.checkEaten(board.getTail().getXCoord(), board.getTail().getYCoord()));
    }

    @Test
    public void testWin() {
        SnakePanel panel = new SnakePanel(1, 1, null, null, null, null, null);
        Assert.assertTrue(panel.win());
    }

    @Test
    public void testLose() {
        SnakePanel panel = new SnakePanel(1, 1, null, null, null, null, null);
        Assert.assertTrue(panel.lose());
    }

}



