/**
 * Created by S199756066 on 3/21/2017.
 */
public class Point {

    public boolean canMoveE, canMoveW, canMoveN, canMoveS, travelled;
    public Coordinate coord;

    public Point(){
        canMoveE = true;
        canMoveN = true;
        canMoveS = true;
        canMoveW = true;
        travelled = false;
        this.coord = new Coordinate();
    }

    public Point(int x, int y){
        canMoveE = true;
        canMoveN = true;
        canMoveS = true;
        canMoveW = true;
        travelled = false;
        this.coord = new Coordinate(x, y);
    }

}
