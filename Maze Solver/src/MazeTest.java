import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class MazeTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        StdDraw.enableDoubleBuffering();
    }

    @After
    public void tearDown() throws Exception {
        StdDraw.clear();
    }

    @Test
    public void test3x3_stack() {
        int[][] data = {{1,1,0},
                {0,1,1},
                {0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(true, maze.findPathWithStack(0, 0));
    }

    @Test
    public void test3x3_queue() {
        int[][] data = {{1,1,0},
                {0,1,1},
                {0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(true, maze.findPathWithQueue(0, 0));
    }

    @Test
    public void test10x10_hasPath_stack() {
        int[][] data = {{1,1,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,0,1,1,1,0},
                {0,1,1,1,1,0,1,1,0,0},
                {0,1,0,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,1,1,0},
                {0,1,1,0,1,1,1,1,1,0},
                {0,0,1,0,0,1,0,1,0,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,0,0,0,0,0,0,0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(true, maze.findPathWithStack(0, 0));
    }

    @Test
    public void test10x10_hasPath_queue() {
        int[][] data = {{1,1,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,0,1,1,1,0},
                {0,1,1,1,1,0,1,1,0,0},
                {0,1,0,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,1,1,0},
                {0,1,1,0,1,1,1,1,1,0},
                {0,0,1,0,0,1,0,1,0,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,0,0,0,0,0,0,0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(true, maze.findPathWithQueue(0, 0));
    }

    @Test
    public void test10x10_noPath_stack() {
        int[][] data = {{1,1,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,0,1,1,1,0},
                {0,1,1,1,1,0,1,1,0,0},
                {0,1,0,1,1,0,1,1,1,0},
                {0,1,0,0,0,0,0,1,1,0},
                {0,1,1,0,1,1,1,1,1,0},
                {0,0,1,0,0,0,0,1,0,0},
                {0,1,1,1,1,1,0,1,1,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,0,0,0,0,1,0,0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(false, maze.findPathWithStack(0, 0));
    }


    @Test
    public void test10x10_noPath_queue() {
        int[][] data = {{1,1,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,0,1,1,1,0},
                {0,1,1,1,1,0,1,1,0,0},
                {0,1,0,1,1,0,1,1,1,0},
                {0,1,0,0,0,0,0,1,1,0},
                {0,1,1,0,1,1,1,1,1,0},
                {0,0,1,0,0,0,0,1,0,0},
                {0,1,1,1,1,1,0,1,1,0},
                {0,1,1,0,1,1,0,1,1,0},
                {0,0,0,0,0,1,0,0,1,1}};
        Maze maze = new Maze(data.length, data[0].length, data);
        maze.draw();
        assertEquals(false, maze.findPathWithQueue(0, 0));
    }
}
