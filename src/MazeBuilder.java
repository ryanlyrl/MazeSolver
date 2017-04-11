import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Evan on 4/7/2017.
 */
public class MazeBuilder {

    JFrame frame;
    GridLayout gridLayout;

    final int WIDTH = 500;
    final int HEIGHT = 500;

    final int TILE_SIZE = 40;

    final Color COLOR_OPEN = Color.white;
    final Color COLOR_CLOSED = Color.black;
    final Color COLOR_ROBOT = Color.red;
    final Color COLOR_END = Color.green;

    final Color COLOR_INTERSECTION = Color.gray;

    static Gui gui;

    //final int X_SIZE = HEIGHT / TILE_SIZE;
    //final int Y_SIZE = WIDTH / TILE_SIZE;

    int X_SIZE = 10;
    int Y_SIZE = 10;

    ArrayList<JLabel> labels;

    public MazeBuilder (int xSize, int ySize) {
        this.X_SIZE = xSize;
        this.Y_SIZE = ySize;
        frame = new JFrame();
        gridLayout = new GridLayout(X_SIZE, Y_SIZE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(gridLayout);

        labels = new ArrayList<>();

        for (int x = 0; x < X_SIZE ; x ++) {
            for (int y = 0; y < Y_SIZE; y ++) {
                //JLabel label = new JLabel(x + "," + y);
                JLabel label;

                if (x == X_SIZE - 1 && y == Y_SIZE - 1) {
                    label = new JLabel("E");
                    label.setBackground(COLOR_END);
                }
                else if (y % 2 == 0 && x % 2 == 0) {
                    label = new JLabel("I");
                    label.setBackground(COLOR_INTERSECTION);
                }
                else if (y % 2 != 0 && x % 2 != 0) {
                    label = new JLabel("X");
                    label.setBackground(Color.blue);
                }
                else {
                    label = new JLabel("O");
                    label.setBackground(COLOR_OPEN);
                }
                label.setOpaque(true);
                label.setName(x + "," + y);
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (label.getText().equals("X") || label.getText().equals("I")) return;
                        if (label.getText().equals("O")) {
                            label.setText("B");
                            label.setBackground(COLOR_CLOSED);
                        }
                        else {
                            label.setText("O");
                            label.setBackground(COLOR_OPEN);

                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                labels.add(label);
                frame.add(label);

            }
        }
        frame.setVisible(true);

        loop2();
    }

    public MazeBuilder(int xSize, int ySize, ArrayList<Character> chars) {
        this.X_SIZE = xSize;
        this.Y_SIZE = ySize;
        frame = new JFrame();
        gridLayout = new GridLayout(X_SIZE, Y_SIZE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(gridLayout);

        labels = new ArrayList<>();
        int j = 0;
        for (int x = 0; x < X_SIZE ; x ++) {
            for (int y = 0; y < Y_SIZE; y ++) {
                //JLabel label = new JLabel(x + "," + y);
                JLabel label;

//                if (x == X_SIZE - 1 && y == Y_SIZE - 1) {
//                    label = new JLabel("E");
//                    label.setBackground(COLOR_END);
//                }
//                else {
//                    label = new JLabel("O");
//                    label.setBackground(COLOR_OPEN);
//                }
                char c = chars.get(j);
                label = new JLabel(c + "");
                if (c == 'O') label.setBackground(COLOR_OPEN);
                else if (c == 'B') label.setBackground(COLOR_CLOSED);
                else if (c == 'E') label.setBackground(COLOR_END);
                label.setOpaque(true);
                label.setName(x + "," + y);
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (label.getText().equals("X") || label.getText().equals("I")) return;
                        if (label.getText().equals("O")) {
                            label.setText("B");
                            label.setBackground(COLOR_CLOSED);
                        }
                        else {
                            label.setText("O");
                            label.setBackground(COLOR_OPEN);

                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                labels.add(label);
                frame.add(label);
                j++;
            }
        }
        frame.setVisible(true);

        loop2();
    }

    public void loop2() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("OPTIONS: S = Save, R = run, E = exit editor");
            String option = new Scanner(System.in).nextLine();
            if (option.equals("S")) {
                System.out.println("Name of the file:");
                String name = new Scanner(System.in).nextLine();
                Maze maze = new Maze(X_SIZE, Y_SIZE);
                for (JLabel label: labels) {
                    System.out.println(label.getName());
                    String[] split = label.getName().split(",");
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);
                    maze.getTile(x, y).type = label.getText().charAt(0);
                }
                try {
                    PrintWriter printWriter = new PrintWriter(name);
                    printWriter.println(X_SIZE);
                    printWriter.println(Y_SIZE);
                    for (MazeTile[] tiles: maze.tiles) {
                        String s = "";
                        for (MazeTile tile: tiles) {
                            s += tile.type;
                        }
                        printWriter.println(s);
                    }
                    printWriter.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                new MazeSolver(gui, X_SIZE, Y_SIZE, maze);
            }
            else if (option.equals("R")) {
                Maze maze = new Maze(X_SIZE, Y_SIZE);
                for (JLabel label: labels) {
                    System.out.println(label.getName());
                    String[] split = label.getName().split(",");
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);
                    maze.getTile(x, y).type = label.getText().charAt(0);
                }
                new MazeSolver(gui, X_SIZE, Y_SIZE, maze);
            }
            else if (option.equals("E")) {
                frame.setVisible(false);
                frame.dispose();
                isRunning = false;
            }
        }
    }

    public static void main(String[] args) {
        //Maze maze = new Maze(X_SIZE, Y_SIZE);
        gui = new Gui();
        loop();
        //new MazeBuilder();
    }

    public static void loop() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("OPTIONS: EB = open editor (blank), EL = open file to edit, L = load file and run");
            String option1 = new Scanner(System.in).nextLine();
            if (option1.equals("EB")) {
                System.out.println("!!!ODD NUMBERS ONLY!!!");
                System.out.println("X_SIZE:");
                int xSize = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println("Y_SIZE:");
                int ySize = Integer.parseInt(new Scanner(System.in).nextLine());
                new MazeBuilder(xSize, ySize);
            }
            else if (option1.equals("EL")) {
                System.out.println("Name of the file:");
                String name = new Scanner(System.in).nextLine();
                int xsize = 0, ysize = 0;
                ArrayList<Character> chars = new ArrayList<>();
                Maze maze = null;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(name));
                    xsize = Integer.parseInt(reader.readLine());
                    ysize = Integer.parseInt(reader.readLine());
                    maze = new Maze(xsize, ysize);
                    for (MazeTile[] tiles: maze.tiles) {
                        String s = reader.readLine();
                        for (int i = 0; i < tiles.length; i ++) {
                            chars.add(s.charAt(i));
                            tiles[i].type = s.charAt(i);
                        }
                    }
                    reader.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                new MazeBuilder(xsize, ysize, chars);
            }
            else if (option1.equals("L")) {
                System.out.println("Name of the file:");
                String name = new Scanner(System.in).nextLine();
                int xsize = 0, ysize = 0;
                Maze maze = null;
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(name));
                    xsize = Integer.parseInt(reader.readLine());
                    ysize = Integer.parseInt(reader.readLine());
                    maze = new Maze(xsize, ysize);
                    for (MazeTile[] tiles: maze.tiles) {
                        String s = reader.readLine();
                        for (int i = 0; i < tiles.length; i ++) {
                            tiles[i].type = s.charAt(i);
                        }
                    }
                    reader.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                new MazeSolver(gui, xsize, ysize, maze);
            }


        }
    }
}
