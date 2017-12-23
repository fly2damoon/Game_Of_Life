/***
 * Game of Life create a two-deimensional cell board, and calculates the
 * next state based on the current cell board. Then prints out the cell board.
 *
 * The life cycle is predetermined by the user with variable LIFE.
 * The initial cell board is set to 8 x 6 (width = 8, height = 6)
 *
 * The board is padded around the edge, for efficient neighbor cell calculation
 *
 * The board size can be changed by updated the BOARD_WIDTH and BOARD_HEIGHT.
 * Also updated initializeBoard() method to corresponding width and height.
 *
 */
public class GameOfLife {
    static final int BOARD_WIDTH = 8;
    static final int BOARD_HEIGHT = 6;
    static final int PADDED_WIDTH = BOARD_WIDTH + 2;
    static final int PADDED_HEIGHT = BOARD_HEIGHT + 2;

    static final int LIFE = 5;
    public static void main (String[] args)
    {
        int[][] initialBoard;
        int[][] nextBoard = new int[PADDED_HEIGHT][PADDED_WIDTH];

        initialBoard = initializeBoard();
        printBoard(initialBoard);

        for(int i = 0; i < LIFE; i++) {
            getNextLife(initialBoard, nextBoard);
            printBoard(nextBoard);
            int [][] temp = initialBoard;
            initialBoard = nextBoard;
            nextBoard = temp;
        }
    }

    /***
     * Initialize the Board. *Board is padded around the edge, to improve neighbor calculation.
     * @return A  initial Board with life at 10 different spots.
     */
    public static int[][] initializeBoard() {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return board;
    }

    /***
     * Calculate the next state based on thisBoard. Store next state into nextBoard.
     * Calculation Rules
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-
     *    population.
     * 2. Any live cell with more than three live neighbors dies, as if by overcrowding.
     * 3. Any live cell with two or three live neighbors lives on to the next generation.
     * 4. Any dead cell with exactly three live neighbors becomes a live cell.
     *
     * @param thisBoard: Board for current life cycle
     * @param nextBoard: Board used for next life cycle
     */
    public static void getNextLife(int[][] thisBoard, int[][]nextBoard) {
        for(int y = 1; y < thisBoard.length-1; y++) {
            for(int x = 1; x < thisBoard[0].length-1; x++) {
                nextBoard[y][x] = 0;
                int neighbors = getNeighbors(thisBoard, x, y);
                if(thisBoard[y][x] == 1 && neighbors < 2)
                    nextBoard[y][x] = 0;
                else if(thisBoard[y][x] == 1 && neighbors > 3)
                    nextBoard[y][x] = 0;
                else if(thisBoard[y][x] == 1 && 2 <= neighbors && neighbors <= 3)
                    nextBoard[y][x] = 1;
                else if(thisBoard[y][x] == 0 && neighbors == 3)
                    nextBoard[y][x] = 1;
            }
        }
    }

    /***
     * Calculate all the alive cells from the neighboring cells
     * Definition of neighboring cell:
     *    A cell’s neighbors are those cells which are horizontally, vertically or
     *    diagonally adjacent. Most cells will have eight neighbors. Cells placed on the
     *    edge of the grid will have fewer.
     *
     * @param board: The board to calculate neighbors
     * @param x: X coordinate of current cell on the board
     * @param y: Y coordinate of current cell on the board
     * @return Total number of life in neighboring cells
     */
    public static int getNeighbors(int[][] board, int x, int y) {
        int sum = 0;
        for(int yNeighbor = y-1; yNeighbor <= y+1; yNeighbor++) {
            for(int xNeighbor = x-1; xNeighbor <= x+1; xNeighbor++) {
                if(xNeighbor == x && yNeighbor == y)
                    continue;
                sum += board[yNeighbor][xNeighbor];
            }
        }
        return sum;
    }

    /***
     * Print the cell board.
     * @param board: The board to print
     */
    public static void printBoard(int[][] board) {
        System.out.println("Printing the Board: ");
        for(int y = 1; y < board.length-1; y++) {
            StringBuilder sb = new StringBuilder();
            for(int x = 1; x < board[0].length-1; x++) {
                char c = board[y][x] == 1 ? 'O' : '.';
                sb.append(c).append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
