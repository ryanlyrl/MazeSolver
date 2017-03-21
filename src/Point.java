/**
 * Created by S199756066 on 3/21/2017.
 */
public class Point {

    public boolean canMoveE, canMoveW, canMoveN, canMoveS, travelled;
    public Coordinate coord;

    public Point(){
        canMoveE = false;
        canMoveN = false;
        canMoveS = false;
        canMoveW = false;
        travelled = false;
        this.coord = new Coordinate();
    }

    public Point(int x, int y){
        canMoveE = false;
        canMoveN = false;
        canMoveS = false;
        canMoveW = false;
        travelled = false;
        this.coord = new Coordinate(x, y);
    }

}
