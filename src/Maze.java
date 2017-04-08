/**
 * Created by RobotStudent on 4/7/2017.
 */
public class Maze {
    MazeTile[][] tiles;

    public Maze(int xsize, int ysize) {
        tiles = new MazeTile[xsize][ysize];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new MazeTile(x,y);
            }
        }
    }

    public MazeTile getTile(int x, int y) {
        return tiles[x][y];
    }

    public MazeTile scan (char direction, int x, int y) {
        System.out.println("SCANNING: Direction: " + direction + ", x: " + x + ", y:" + y);
        if (direction == 'U' && (x - 1) > -1) {
            return getTile(x - 1, y);
        }
        else if (direction == 'D' && (x + 1) < tiles.length) {
            return getTile(x + 1, y);
        }
        else if (direction == 'L' && (y - 1) > -1) {
            System.out.println("?");
            return getTile(x, y - 1);
        }
        else if (direction == 'R' && (y + 1) < tiles[0].length) {
            return getTile(x, y + 1);
        }
        return null;
    }






}
