/**
 * Created by S199756066 on 3/21/2017.
 */
public class Main {

    public static void main(String[] args) {
        boolean[][] block = new boolean[7][7];
        boolean[][] block2 = new boolean[7][7];
        for(int i = 0;i < block.length;i++){
            for(int j = 0;j < block.length;j++){
                block[i][j] = false;
                block2[i][j] = false;
            }
        }


        //block[0][1] = true;
        //block[2][2] = true;
        block2[0][0] = true;
        block2[0][1] = true;
        block2[0][2] = true;
        block2[0][3] = true;
        block2[0][4] = true;
        block2[0][6] = true;


        Maze maze = new Maze(7, 4, block, block2);
        Solver solve = new Solver(maze);
        System.out.println(solve);
//        for(int i = 0;i < solve.xSize;i++){
//            System.out.println(solve.points[i][0]);
//        }
        solve.run();
    }

}
