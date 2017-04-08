/**
 * Created by RobotStudent on 4/7/2017.
 */
public class Robot {

    int x;
    int y;

    /*
     DIRECTIONS:
     'U': Up
     'D': Down
     'L': Left
     'R': Right
     */

    char direction;

    public Robot() {
        direction = 'R';
        x = 0;
        y = 0;
    }

    public void move (char direction) {
        this.direction = direction;
        if (direction == 'U') {
            x -= 1;
        }
        else if (direction == 'D') {
            x += 1;
        }
        else if (direction == 'L') {
            y -= 1;
        }
        else if (direction == 'R') {
            y += 1;
        }
        else {
            System.out.println("Invalid Direction");
        }
    }
}
