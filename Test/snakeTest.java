import com.company.GameBoard;
import com.company.SnakeFrame;
import com.company.SnakePanel;
import org.junit.Assert;
import org.junit.Test;

public class snakeTest {
    @Test
    public void testgetFoodXCoord(){
        SnakePanel foodX = new SnakePanel();
    }

    @Test
    public void testmove(){
        GameBoard moveSnake = new GameBoard(23, 23);
    }

    @Test
    public void testgenerateFood() {
    GameBoard food = new GameBoard(23, 23);

    }
    @Test
    public void TestColission(){

    }
    @Test
    public void TestisDead(){
        GameBoard dead = new GameBoard(23, 23);
        //assertBoolean(dead.isDead(),true);

    }

    @Test
    public void testHasWon() {
        GameBoard board = new GameBoard(3, 0);
        board.setFoodXCoord(2);
        board.setFoodYCoord(0);
        board.checkEaten(board.getTail().getXCoord(), board.getTail().getYCoord());
        Assert.assertTrue(board.hasWon());
    }

    @Test
    public void testIsDead() {
        GameBoard board = new GameBoard(2, 0);
        board.setFoodXCoord(0);
        board.setFoodYCoord(0);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }
}



