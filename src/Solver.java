import java.util.Scanner;

/**
 * Created by S199756066 on 3/22/2017.
 */
public class Solver extends Maze {

    int x = 0;
    int y = 0;
    boolean solved = false;
    char heading = 'E';

    public Solver(Maze maze){
        super(maze.xSize, maze.ySize, maze.blockedX, maze.blockedY);
    }

    public void run(){
        loop();
    }

    public void loop(){
        while(true) {
            Scanner in = new Scanner(System.in);
           if (in.nextLine().trim().equals("")) {
                move();
                atIntersection();
                System.out.println("(" + x + " ," + y + ")");
                for (int i = 0; i < 5; i++) {
                    System.out.println("");
                }
                System.out.println(this);
           } else {
               System.out.println("no");
           }
        }
    }

    private void move(){
        if(heading == 'E'){
            x++;
        } else if(heading == 'S'){
            y++;
        } else if(heading == 'W'){
            x--;
        } else if(heading == 'N'){
            y--;
        }
    }

    public void atIntersection(){
        if(x == 6 && y == 3){
            for(int i = 0;i < 5;i++){
                System.out.println("");
            }
            System.out.println(this);
            System.out.println("Solved!");
            System.exit(0);
        }

        boolean canMoveStraight = true;
        switch (heading){
            case 'E':
                if(!points[x][y].canMoveE){
                    canMoveStraight = false;
                }
                break;
            case 'S':
                if(!points[x][y].canMoveS){
                    canMoveStraight = false;
                }
                break;
            case 'W':
                if(!points[x][y].canMoveW){
                    canMoveStraight = false;
                }
                break;
            case 'N':
                if(!points[x][y].canMoveN){
                    canMoveStraight = false;
                }
                break;
            default:
                break;
        }

        if(!canMoveStraight){
            char turnDir = turnDirection();
            doTurn(heading, turnDir);
            heading = turnDir;
        } else {
            return;
        }

    }

    public char turnDirection(){
        if(points[x][y].canMoveS){
            if(points[x][y].canMoveE) {
                if (6 - x >= 3 - y) {
                    return 'E';
                } else {
                    return 'S';
                }
            } else {
                return 'S';
            }
        }  else if(points[x][y].canMoveE){
            return 'E';
        } else if(points[x][y].canMoveW){
            return 'W';
        } else {
            return 'N';
        }
    }

    void doTurn(char origin, char destination){
        int origInt = headingToInt(origin);
        int destInt = headingToInt(destination);

        if(Math.abs(origInt - destInt) == 3){
            if(origInt == 1){
                origInt = 5;
            } else {
                destInt = 5;
            }
        }

        int turn = origInt - destInt;
        if(turn < 0){
            for(int i = turn;i < 0;i++){
                turnLeft();
            }
        } else {
            for(int i = 0;i < turn;i++){
                turnRight();
            }
        }
    }

    public void turnRight(){
        if(heading == 'S'){
            heading = 'W';
        } else if(heading == 'W'){
            heading = 'N';
        } else if(heading == 'N'){
            heading = 'E';
        } else if(heading == 'E'){
            heading = 'S';
        }
    }

    public void turnLeft(){
        if(heading == 'S'){
            heading = 'E';
        } else if(heading == 'W'){
            heading = 'S';
        } else if(heading == 'N'){
            heading = 'W';
        } else if(heading == 'E'){
            heading = 'N';
        }
    }

    public int headingToInt(char heading){
        if(heading == 'E'){
            return 1;
        } else if(heading == 'N'){
            return 2;
        } else if(heading == 'W'){
            return 3;
        } else if(heading == 'S'){
            return 4;
        } else {
            return -1;
        }
    }

    @Override
    public String toString(){
        int currentX = 0;
        int currentY = 0;
        for(int i = 0;i < ySize * 2 - 1;i++){
            if(i % 2 == 0){
                currentX = 0;
                for(int j = 0;j < xSize - 1;j++){
                    if(currentX == x && currentY == y) {
                        System.out.print("B");
                        currentX++;
                    } else {
                        System.out.print("O");
                        currentX++;
                    }

                    if(blockedX[i/2][j]){
                        System.out.print("--X--");
                    } else {
                        System.out.print("-----");
                    }
                }
                if(currentX == x && currentY == y) {
                    System.out.println("B");
                } else {
                    System.out.println("O");
                }
                currentY++;
            } else {
                for(int k = 0;k < 3;k++) {
                    for (int j = 0; j < xSize; j++) {
                        if(k == 1) {
                            if(blockedY[i/2][j]) {
                                System.out.print("X     ");
                            } else {
                                System.out.print("|     ");
                            }
                        } else {
                            System.out.print("|     ");
                        }
                    }
                    System.out.println("");
                }
            }
        }

        return "";
    }
}
