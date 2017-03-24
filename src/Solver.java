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

    }

    public void loop(){
        while(!solved){

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
        
    }

    @Override
    public String toString(){
        int currentX = 0;
        int currentY = 0;
        for(int i = 0;i < ySize * 2 - 1;i++){
            if(i % 2 == 0){
                for(int j = 0;j < xSize - 1;j++){
                    if(currentX == x && currentY == y) {
                        System.out.print("B");
                    } else {
                        System.out.print("O");
                    }

                    if(blockedX[i/2][j]){
                        System.out.print("--X--");
                    } else {
                        System.out.print("-----");
                    }
                    currentX++;
                }
                if(currentX == x && currentY == y) {
                    System.out.println("B");
                } else {
                    System.out.println("O");
                }
                currentY++;
            } else {
                for(int k = 0;k < 3;k++) {
                    for (int j = 0; j < xSize - 1; j++) {
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
                    System.out.println("|");
                }
            }
        }

        return "";
    }
}
