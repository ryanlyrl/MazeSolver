import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by RobotStudent on 4/7/2017.
 */
public class Gui implements Runnable {
    JFrame frame;
    Canvas canvas;

    Thread t;

    boolean isRunning = false;

    final int WIDTH = 1000;
    final int HEIGHT = 1000;

    final int TILE_SIZE = 50;

    final Color COLOR_OPEN = Color.white;
    final Color COLOR_CLOSED = Color.black;
    final Color COLOR_ROBOT = Color.red;
    final Color COLOR_END = Color.green;

    final Color COLOR_INTERSECTION = Color.gray;

    final Color COLOR_BORDER = Color.blue;

    Maze maze;

    Robot robot;

    ArrayList<Robot> robots;
    ArrayList<Maze> mazes;

    int currentI;



    public Gui() {
        frame = new JFrame();
        canvas = new Canvas();
        canvas.setSize(WIDTH, HEIGHT);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(canvas);
        frame.setVisible(true);
        robots = new ArrayList<>();
        mazes = new ArrayList<>();
        currentI = 0;
        canvas.createBufferStrategy(2);
        start();
    }

    public void draw(Maze maze, Robot robot) {
        this.maze = maze;
        this.robot = robot;
    }

    private void start() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        isRunning = true;
        while (isRunning) {

            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            draw();
        }
    }

    public void draw() {
        if (maze == null && robot == null) {
            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            Graphics g = bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.drawString("Waiting...", 20, 20);
            bufferStrategy.show();
            return;
        }
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.white);
        //Border Top
        //g.fillRect(0, 0, WIDTH, HEIGHT);
        g.clearRect(0, 0, WIDTH, HEIGHT);
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
                else if (mazeTile.type == 'I') {
                    g.setColor(COLOR_INTERSECTION);
                }

                else if (mazeTile.type == 'X') {
                    g.setColor(COLOR_BORDER);
                }

                if (mazeTile.isFlagged && mazeTile.type == 'B') {
                    g.setColor(Color.yellow);
                }
                else if (mazeTile.isFlagged && mazeTile.type == 'O' && !isRobot ) {
                    g.setColor(Color.pink);
                }
                else if (mazeTile.wasAlreadyOn && mazeTile.type == 'I' && !isRobot) {
                    g.setColor(Color.magenta);
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
        bufferStrategy.show();
    }

}