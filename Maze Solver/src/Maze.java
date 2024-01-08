import java.awt.*;

public class Maze
{
    private Cell[][] board;
    private final int DELAY = 200;
   // private Stack<Cell> visitedStack;
   // private Queue<Cell> visitedQueue;
    public Maze(int rows, int cols, int[][] map){
        StdDraw.setXscale(0, cols);
        StdDraw.setYscale(0, rows);
        board = new Cell[rows][cols];
        //grab number of rows to invert grid system with StdDraw (lower-left, instead of top-left)
        int height = board.length - 1;
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                board[r][c] = map[r][c] == 1 ? new Cell(c , height - r, r, c, 0.5, false) : new Cell(c, height - r, r, c, 0.5, true);
            }
    }

    public void draw()
    {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++){
                Cell cell = board[r][c];
                StdDraw.setPenColor(cell.getColor());
                StdDraw.filledSquare(cell.getX(), cell.getY(), cell.getRadius());
            }
        StdDraw.show();
    }

    /*
     * This method uses a stack to manage the order of the cells that are visited.
     * Returns a boolean indicating whether a path to the exit has been found beginning
     * at the location (row, col) of board.
     */
    public boolean findPathWithStack(int row, int col)
    {
        int r = row;
        int c = col;
        Stack<Cell> cellsToVisit = new Stack<Cell>();
        if(isValid(r, c))
        {
            if(isExit(r, c))
            {
                return true;
            }
            cellsToVisit.push(board[r][c]);
            board[r][c].isVisited();
            board[r][c].setColor(Color.blue);
            draw();
            StdDraw.pause(DELAY);

            while(!cellsToVisit.isEmpty())
            {
                Cell cell = cellsToVisit.pop();
                cell.setColor(Color.blue);
                draw();
                StdDraw.pause(DELAY);
                r=cell.getRow();
                c= cell.getCol();

                if(isExit(r, c)){
                    board[r][c].becomePath();
                    draw();
                    StdDraw.pause(DELAY);
                    turnRed();
                    return true;
                }//else

                if(isValid(r, c + 1) && board[r][c + 1].getColor() == Color.white)
                {

                    cellsToVisit.push(board[r][c+1]);
                    board[r][c].becomePath();
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].isVisited();
                }//else
                if(isValid(r + 1, c) && board[r + 1][c].getColor() == Color.white)
                {

                    cellsToVisit.push(board[r+1][c]);
                    board[r][c].becomePath();
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].isVisited();
                }//else
                if(isValid(r, c - 1) && board[r][c - 1].getColor() == Color.white)
                {

                    cellsToVisit.push(board[r][c-1]);
                    board[r][c].becomePath();
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].isVisited();
                }//else
                if(isValid(r - 1, c) && board[r - 1][c].getColor() == Color.white)
                {

                    cellsToVisit.push(board[r-1][c]);
                    board[r][c].becomePath();
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].isVisited();
                }
                if(!isValid(r, c+1) && !isValid(r+1, c) && !isValid(r, c-1) && isValid(r+1, c))
                {
                    board[r][c].setColor(Color.red);
                    draw();
                    StdDraw.pause(DELAY);
                }
            }
        }

        turnRed();

        return false;
    }


    /*
     * This method uses a queue to manage the order of the cells that are visited.
     * Returns a boolean indicating whether a path to the exit has been found beginning
     * at the location (row, col) of board.
     */
    public boolean findPathWithQueue(int row, int col)
    {
        int r = row;
        int c = col;
        Queue<Cell> cellsToVisit = new Queue<Cell>();
        if(isValid(r, c))
        {
            if(isExit(r, c))
            {
                return true;
            }
            cellsToVisit.enqueue(board[r][c]);
            board[r][c].setColor(Color.blue);
            draw();
            StdDraw.pause(DELAY);
            boolean isPushed = true;
            board[r][c].visitCell();
            draw();
            StdDraw.pause(DELAY);
            while(isPushed)
            {
                if(isValid(r, c + 1) && board[r][c + 1].getColor() == Color.white)
                {
                    c++;
                    cellsToVisit.enqueue(board[r][c]);
                    board[r][c].setColor(Color.blue);
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].isVisited();
                }else
                if(isValid(r + 1, c) && board[r + 1][c].getColor() == Color.white)
                {
                    r++;
                    cellsToVisit.enqueue(board[r][c]);
                    board[r][c].setColor(Color.blue);
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].visitCell();
                }else
                if(isValid(r, c - 1) && board[r][c - 1].getColor() == Color.white)
                {
                    c--;
                    cellsToVisit.enqueue(board[r][c]);
                    board[r][c].setColor(Color.blue);
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].visitCell();
                }else
                if(isValid(r - 1, c) && board[r - 1][c].getColor() == Color.white)
                {
                    r--;
                    cellsToVisit.enqueue(board[r][c]);
                    board[r][c].setColor(Color.blue);
                    draw();
                    StdDraw.pause(DELAY);
                    board[r][c].visitCell();
                }else{
                    isPushed = false;
                }
                draw();
                StdDraw.pause(DELAY);
                board[r][c].visitCell();
                draw();
                StdDraw.pause(DELAY);
                if(isExit(r, c))
                {
                    isPushed = false;
                }

            }
            if(isExit(r, c))
            {
                while(!cellsToVisit.isEmpty())
                {
                    cellsToVisit.dequeue().setColor(Color.green);
                    draw();
                    StdDraw.pause(DELAY);
                }
                return true;
            }else{
                while(!cellsToVisit.isEmpty())
                {
                    cellsToVisit.dequeue().setColor(Color.red);
                    draw();
                    StdDraw.pause(DELAY);
                }
            }
        }
        turnRed();
        return false;
    }

    private void turnRed()
    {
        for(int i = 0; i < board.length; i++)
        {
         for(int j = 0; j < board[0].length; j++)
         {
             if(!board[i][j].isWall() && board[i][j].getColor() != Color.green)
             {
                 board[i][j].setColor(Color.red);
                 draw();
                 StdDraw.pause(DELAY);
             }
         }
        }
    }

    /*
     * Returns a boolean whether or not position (row, col) is an open cell in the
     * board.
     */
    private boolean isValid(int row, int col)
    {
        if(row >= 0 && row <= (board.length - 1) && col >= 0 && col <= (board[0].length - 1))
        {
            if(!board[row][col].isVisited() && !board[row][col].isWall())
            {
                return true;
            }
        }

        return false;
    }

    /*
     * Returns a boolean whether position (row, col) is the exit in the board.
     */
    private boolean isExit(int row, int col)
    {
        if(row == (board.length - 1) && col == (board[0].length - 1))
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        int[][] maze = {{1,1,0,0,0,0,0,0,0,0},
                        {0,1,1,1,0,0,1,1,1,0},
                        {0,1,1,1,1,0,1,1,0,0},
                        {0,1,0,0,0,1,1,1,1,0},
                        {0,1,0,0,0,0,0,1,1,0},
                        {0,1,1,0,1,1,1,1,1,0},
                        {0,0,1,0,0,1,0,1,0,0},
                        {0,1,1,0,1,1,0,1,1,0},
                        {0,1,1,1,1,1,0,1,1,0},
                        {0,0,0,0,0,0,0,0,1,1}};
        Maze geerid = new Maze(maze.length, maze[0].length, maze);
        geerid.draw();
        // Change the commenting to test your queue method instead of the stack method
        //geerid.findPathWithStack(0, 0);
        geerid.findPathWithQueue(0, 0);
        geerid.draw();
    }
}
