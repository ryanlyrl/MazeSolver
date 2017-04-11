/**
 * Created by RobotStudent on 4/7/2017.
 */
public class MazeTile {

    int x;
    int y;

    boolean wasAlreadyOn;
    boolean isKilled;
    boolean isFlagged;

    int amountOfTimesMovedHere = 0;
    /*
    0: Up
    1: Down
    2: Left
    3: Right
     */
    boolean[] went;
    /*
    TYPES:

    'R' : Robot
    'B' : Barrier
    'O' : Open
    'E' : End
    'P' : Path
    'X' : Empty
    */

    char type;

    public MazeTile(int x, int y) {
        //DEFAULT: Open
        type = 'O';
        this.x = x;
        this.y = y;
        wasAlreadyOn = false;
        isKilled = false;
        isFlagged = false;
        went = new boolean[4];
    }

}
