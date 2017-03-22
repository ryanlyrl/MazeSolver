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

}
