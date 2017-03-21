/**
 * Created by S199756066 on 3/21/2017.
 */
public class Main {

    public static void main(String[] args) {
        boolean[][] block = new boolean[4][4];
        boolean[][] block2 = new boolean[4][4];
        for(int i = 0;i < block.length;i++){
            for(int j = 0;j < block.length;j++){
                block[i][j] = false;
                block2[i][j] = false;
            }
        }

        block[0][1] = true;
        block[2][2] = true;
        block2[0][0] = true;
        block2[1][2] = true;


        Maze maze = new Maze(4, 4, block, block2);
        System.out.println(maze);
    }

}
