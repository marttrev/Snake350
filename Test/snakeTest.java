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
        GameBoard moveSnake = new GameBoard();
    }

    @Test
    public void testgenerateFood() {
    GameBoard food = new GameBoard();

    }
    @Test
    public void TestColission(){

    }
    @Test
    public void TestisDead(){
        GameBoard dead = new GameBoard();
        //assertBoolean(dead.isDead(),true);

    }

    @Test
    public void testHasWon() {
        GameBoard board = new GameBoard(true);
        board.checkEaten(board.getTail().getXCoord(), board.getTail().getYCoord());
        Assert.assertTrue(board.hasWon());
    }

    @Test
    public void testIsDead() {
        GameBoard board = new GameBoard(true);
        board.moveSnake();
        Assert.assertTrue(board.isDead());
    }
}



