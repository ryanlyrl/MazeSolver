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

    @Override
    public String toString(){
        String output = "Point at " + this.coord + "\nCan move ";
        if(this.canMoveE){
            output += "E ";
        }
        if(this.canMoveS){
            output += "S ";
        }
        if(this.canMoveW){
            output += "W ";
        }
        if(this.canMoveN){
            output += "N ";
        }
        return output;
    }

}
