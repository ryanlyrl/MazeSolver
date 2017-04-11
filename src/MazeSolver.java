import java.util.Scanner;

/**
 * Created by RobotStudent on 4/7/2017.
 */
public class MazeSolver {

    int X_SIZE = 0;

    int Y_SIZE = 0;

    Maze maze;

    Gui gui;

    Robot robot;

    char[] directions = new char[] {'R', 'D', 'L', 'U'};

    public MazeSolver(Gui gui, int xsize, int ysize) {
        this.X_SIZE = xsize;
        this.Y_SIZE = ysize;
        maze = new Maze(X_SIZE, Y_SIZE);

//        maze.getTile(1, 0).type = 'B';
//        maze.getTile(X_SIZE - 1, Y_SIZE - 1).type = 'E';
//        maze.getTile(1, Y_SIZE - 1).type = 'B';
//        maze.getTile(1, Y_SIZE - 3).type = 'B';
//        maze.getTile(2, Y_SIZE - 3).type = 'B';
//        maze.getTile(3, Y_SIZE - 3).type = 'B';
//        maze.getTile(3, Y_SIZE - 2).type = 'B';
//        maze.getTile(3, Y_SIZE - 1).type = 'B';
//        maze.getTile(2, Y_SIZE - 1).type = 'B';
//        maze.getTile(3, Y_SIZE - 1).type = 'B';
        //gui = new Gui();
        this.gui = gui;
        //gui.start();
        robot = new Robot();
        maze.getTile(robot.x, robot.y).amountOfTimesMovedHere ++;
        turn();
    }

    public MazeSolver(Gui gui, int xSize, int ySize, Maze maze) {
        this.X_SIZE = xSize;
        this.Y_SIZE = ySize;

        this.maze = maze;

        //gui = new Gui();
        this.gui = gui;
        //gui.start();
        robot = new Robot();
        maze.getTile(robot.x, robot.y).amountOfTimesMovedHere ++;
        turn();

    }

    public static void main(String[] args) {
        new MazeSolver(new Gui(), 5, 5);
    }

    public void turn() {
        boolean isRunning = true;
        System.out.println("Starting the maze solving");
        //gui.draw(maze, robot);
        while (isRunning) {
            //Todo: This gets input
            new Scanner(System.in).nextLine();
            //try {Thread.sleep(10);} catch (Exception e) {};
            MazeTile robotTile = maze.getTile(robot.x, robot.y);
            System.out.println("The Robot Direction is: " + robot.direction);
            System.out.println("The Robot's pos is: (" + robot.x + "," + robot.y + ").");
            if (robotTile.wasAlreadyOn) {

                System.out.println("Was already on this tile, check the intersection");

                boolean foundAPath = false;

                int amountOpenNotBeenOn = 0;
                /*

                0: None
                1: Found, but not open
                2: Found and open
                 */
                int foundRight = 0;
                int foundLeft = 0;
                int foundUp = 0;
                int foundDown = 0;

                for (char d: directions) {
                    System.out.println("Checking the direction: " + d);
                    //Todo: THIS LINE RIGHT HERE!!!!!
                    //Todo: Check all, including in same direction. Completly discard redundant tiles (i.e all blocks NSEW are already flagged), and if no other possible directions are found THEN move in same direction as came in
                    //if (d != robot.direction) {
                        MazeTile mazeTile = maze.scan(d, robot.x, robot.y);
                        if (mazeTile == null) {
                            System.out.println("Cannot move to the tile (Index)");
                        }
                        else {
                            if (!mazeTile.isFlagged) {
                                //Todo: Robot needs to actually scan the block
                                mazeTile.isFlagged = true;
                            }
                            if (checkTile(mazeTile, d)) {
                                System.out.println("Cannot move to the tile, dead branch");
                                //robot.direction = oppositeDirection(robot.direction);
                                //robotTile.wasAlreadyOn = true;
                            }
                            else if (mazeTile.type == 'B') {
                                System.out.println("Cannot move to the tile (Barrier)");
                                //robot.direction = oppositeDirection(robot.direction);
                                //robotTile.wasAlreadyOn = true;
                            }
                            else {
                                //Open
                                //maze.getTile(robot.x, robot.y).wasAlreadyOn = true;
                                robot.direction = d;
                                foundAPath = true;
                                boolean isOpen = false;
                                System.out.println("Checking if the mazeTile 'isOpen'");
                                //Scan Up, check If Was Already On the top block
                                boolean wasAlreadyOnCheck = false;
                                MazeTile mazeTileTwo = maze.scan(d, mazeTile.x, mazeTile.y);
                                if (mazeTileTwo != null) {
                                    wasAlreadyOnCheck = mazeTileTwo.wasAlreadyOn;
                                }



                                if ((!mazeTile.wasAlreadyOn && notRedundant(mazeTile) && !wasAlreadyOnCheck) || mazeTile.type == 'E') {
                                    System.out.println("Was open");
                                    isOpen = true;
                                    amountOpenNotBeenOn ++;
                                }
                                else {
                                    System.out.println("Was not open");
                                }
                                if (d == 'U') {
                                    if (isOpen) foundUp = 2;
                                    else foundUp = 1;
                                }
                                else if (d == 'D') {
                                    if (isOpen) foundDown = 2;
                                    else foundDown = 1;
                                }
                                else if (d == 'R') {
                                    if (isOpen) foundRight = 2;
                                    else foundRight = 1;
                                }
                                else if (d == 'L') {
                                    if (isOpen) foundLeft = 2;
                                    else foundLeft = 1;
                                }
                                //break;
                                //move();
                                //mazeTile.wasAlreadyOn = true;
                            }
                        }
                    //}
                }

                if (amountOpenNotBeenOn > 0) {
                    if (foundRight == 2) robot.direction = 'R';
                    else if (foundLeft == 2) robot.direction = 'L';
                    else if (foundUp == 2) robot.direction = 'U';
                    else if (foundDown == 2) robot.direction = 'D';
                    else {
                        System.err.println("Amount not open > 0, but none are open.");
                        System.exit(-1);
                    }
                }
                else {
                    if (foundRight == 1) robot.direction = 'R';
                    else if (foundLeft == 1) robot.direction = 'L';
                    else if (foundUp == 1) robot.direction = 'U';
                    else if (foundDown == 1) robot.direction = 'D';
                    else {
                        System.out.println("Amount not open == 0, none are open (Stuck).");

                        char lowestChar = oppositeDirection(robot.direction);
                        int lowestVal = -1;

                        //Todo: Layer 2 check: Move to the tile that the robot has been on the LEAST.
                        //Todo: Directions should be reversed
                        for (char d: directions) {
                            MazeTile mazeTile = maze.scan(d, robot.x, robot.y);
                            if (mazeTile != null) {
                                //Todo: Should not need to ever HW scan here, but idk
                                if (mazeTile.type != 'B') {
                                    //robot.direction = d;
                                    System.out.println("Direction: " + d + " is OPEN, amount of times here = " + mazeTile.amountOfTimesMovedHere);
                                    if (lowestVal == -1) {
                                        lowestVal = mazeTile.amountOfTimesMovedHere;
                                        lowestChar = d;
                                    }
                                    else if (mazeTile.amountOfTimesMovedHere <= lowestVal) {
                                        lowestVal = mazeTile.amountOfTimesMovedHere;
                                        lowestChar = d;
                                    }
                                }
                            }
                        }
                        System.out.println("The Lowest Char was: " + lowestChar + " The lowest val: " + lowestVal);
                        robot.direction = lowestChar;

                        //System.exit(-1);
                    }
                }

                MazeTile mazeTile = maze.scan(robot.direction, robot.x, robot.y);
                if (mazeTile == null) {
                    robot.direction = oppositeDirection(robot.direction);
                }
                move();
            }

            else {
                System.out.println("Check and move straight if clear");
                MazeTile mazeTile = maze.scan(robot.direction, robot.x, robot.y);
                if (mazeTile == null) {
                    //Don't move, flip the direction.
                    System.out.println("Cannot move straight anymore (Index)");
                    robot.direction = oppositeDirection(robot.direction);
                    robotTile.wasAlreadyOn = true;
                }
                else {
                    if (!mazeTile.isFlagged) {
                        //Todo: This is where the robot ACTUALLY scans
                        mazeTile.isFlagged = true;
                    }
//                    if (mazeTile.isKilled) {
//                        System.out.println("Cannot move straight anymore (Killed Branch)");
//                    }
                    if (checkTile(mazeTile, robot.direction)) {
                        System.out.println("Cannot move straight, dead branch");
                        robot.direction = oppositeDirection(robot.direction);
                        robotTile.wasAlreadyOn = true;
                    }
                    else if (mazeTile.type == 'B') {
                        System.out.println("Cannot move straight anymore (Barrier)");
                        robot.direction = oppositeDirection(robot.direction);
                        robotTile.wasAlreadyOn = true;
                    }
                    else {
                        maze.getTile(robot.x, robot.y).wasAlreadyOn = true;
                        move();
                        //mazeTile.wasAlreadyOn = true;
                    }

                }

            }
            gui.draw(maze, robot);
            if (robot.x == X_SIZE - 1 && robot.y == Y_SIZE - 1) {
                System.out.println("Maze solved");
                isRunning = false;
            }
        }
    }

    private boolean notRedundant(MazeTile mazeTile) {
        boolean isNotRedundant = false;
        for (char d: directions) {
            MazeTile next = maze.scan(d, mazeTile.x, mazeTile.y);
            if (next != null) {
                if (!next.isFlagged) {
                    isNotRedundant = true;
                }
            }
        }
        return  isNotRedundant;
    }

    public boolean checkTile (MazeTile mazeTile, char direction) {
        if (direction == 'U')  return mazeTile.went[0];
        if (direction == 'D') return mazeTile.went[1];
        if (direction == 'L') return mazeTile.went[2];
        if (direction == 'R') return mazeTile.went[3];
        return false;
    }

    public void move () {
        if (robot.direction == 'U') {
            maze.getTile(robot.x - 1, robot.y).went[0] = true;
            maze.getTile(robot.x - 1, robot.y).amountOfTimesMovedHere ++;
        }
        else if (robot.direction == 'D') {
            maze.getTile(robot.x + 1, robot.y).went[1] = true;
            maze.getTile(robot.x + 1, robot.y).amountOfTimesMovedHere ++;
        }
        else if (robot.direction == 'L') {
            maze.getTile(robot.x, robot.y - 1).went[2] = true;
            maze.getTile(robot.x, robot.y - 1).amountOfTimesMovedHere ++;
        }
        else if (robot.direction == 'R') {
            maze.getTile(robot.x, robot.y + 1).went[3] = true;
            maze.getTile(robot.x, robot.y + 1).amountOfTimesMovedHere ++;
        }
        robot.move(robot.direction);
        //Todo
        maze.getTile(robot.x, robot.y).wasAlreadyOn = true;
        robot.move(robot.direction);
    }

    private char oppositeDirection(char direction) {
        if (direction == 'L') return 'R';
        if (direction == 'R') return 'L';
        if (direction == 'U') return 'D';
        if (direction == 'D') return 'U';
        return direction;
    }

}