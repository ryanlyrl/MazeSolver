import javax.swing.*;
import java.awt.*;

/**
 * Created by RobotStudent on 4/7/2017.
 */
public class Gui {
    JFrame frame;
    Canvas canvas;

    final int WIDTH = 500;
    final int HEIGHT = 500;

    final int TILE_SIZE = 30;

    final Color COLOR_OPEN = Color.white;
    final Color COLOR_CLOSED = Color.black;
    final Color COLOR_ROBOT = Color.red;
    final Color COLOR_END = Color.green;

    final Color COLOR_BORDER = Color.blue;

    Maze maze;

    Robot robot;

    public Gui() {
        frame = new JFrame();
        canvas = new Canvas();
        canvas.setSize(WIDTH, HEIGHT);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(canvas);
        frame.setVisible(true);
    }

    public void draw(Maze maze, Robot robot) {
        this.maze = maze;
        this.robot = robot;
        Graphics g = canvas.getGraphics();
        g.setColor(Color.white);
        //Border Top
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(COLOR_BORDER);
        for (int j = 0; j < maze.tiles[0].length + 2; j ++) {
            g.fillRect(j * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE);
        }
        for (int x = 0; x < maze.tiles.length; x ++) {
            g.setColor(COLOR_BORDER);
            g.fillRect(0, (x * TILE_SIZE) + TILE_SIZE, TILE_SIZE, TILE_SIZE);
            for (int y = 0; y < maze.tiles[x].length; y ++) {
                MazeTile mazeTile = maze.tiles[x][y];
                boolean isRobot = false;
                if (robot.x == mazeTile.x && robot.y == mazeTile.y) {
                    g.setColor(COLOR_ROBOT);
                    isRobot = true;
                }
                else if (mazeTile.type == 'O') {
                    g.setColor(COLOR_OPEN);
                }
                else if (mazeTile.type == 'B') {
                    g.setColor(COLOR_CLOSED);
                }
                else if (mazeTile.type == 'E') {
                    g.setColor(COLOR_END);
                }

                if (mazeTile.isFlagged && mazeTile.type == 'B') {
                    g.setColor(Color.yellow);
                }
                else if (mazeTile.isFlagged && mazeTile.type == 'O' && !isRobot ) {
                    g.setColor(Color.pink);
                }

                g.fillRect((y * TILE_SIZE) + TILE_SIZE, (x * TILE_SIZE) + TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
            g.setColor(COLOR_BORDER);
            g.fillRect((TILE_SIZE * maze.tiles[0].length) + TILE_SIZE, (x * TILE_SIZE) + TILE_SIZE, TILE_SIZE, TILE_SIZE);

        }
        //Bottom
        g.setColor(COLOR_BORDER);
        for (int j = 0; j < maze.tiles[0].length + 2; j ++) {
            g.fillRect(j * TILE_SIZE, (maze.tiles.length * TILE_SIZE) + TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

}