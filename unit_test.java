import org.junit.Assert;
import org.junit.Test;

public class unit_test {

    /***
     * Test getNextLife method.
     */
    @Test
    public void testGetNextLife() {
        int[][] initialBoard =
                {{0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0}};
        int[][] actualResult =
                {{0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};
        int[][] expectedResult =
                {{0,0,0,0,0},
                {0,0,0,0,0},
                {0,1,1,1,0},
                {0,0,0,0,0},
                {0,0,0,0,0}};

        GameOfLife.getNextLife(initialBoard, actualResult);

        Assert.assertArrayEquals(expectedResult, actualResult);
    }

    /***
     * Test getNeighbors method. 
     */
    @Test
    public void testGetNeighbors() {
        int[][] initialBoard =
                {{0,0,0,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,0,0,0}};

        int expectedResult = 2;
        int actualResult = GameOfLife.getNeighbors(initialBoard, 3,3);

        Assert.assertEquals(expectedResult, actualResult);
    }
}
