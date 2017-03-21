/**
 * Created by S199756066 on 3/21/2017.
 */
public class Coordinate {

    public int x, y;

    public Coordinate(){
        this.x = -1;
        this.y = -1;
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

}
