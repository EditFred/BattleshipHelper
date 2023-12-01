import java.util.ArrayList;
public class TargettingMaps {
    public static String gameMode = "Patrol Search";

    private char [][][] everyTwoMaps;
    private char [][][] everyThreeMaps;
    private char [][][] everyFourMaps;
    private char [][][] everyFiveMaps = new char[5][10][10];
    private char [][] targetBoard;


    public TargettingMaps(){
        generateEveryPossibleTargetBoard();
        targetBoard = GameBoards.getBoard("Patrol Search");

    }
    



    private void generateEveryPossibleTargetBoard(){

        /// DEMONSTRATING COMPLEX MULTIDIMENSIONAL ARRAYS \\\
        /* The purpose of this function is to create alternate maps that will be needed to remap the current target board,
         * as there may be a more efficient path, the most efficient path changes according to hits/misses once the program starts to
         * find the boats on the field. New Maps are create for all sizes. Every 2, 3, 4, and 5.
         * BUT this time an additional map is create for each possible offset.
         * everyTwo, has one additional offset, everyThree has two possible offsets, everyFour has three, and everyFive has four.
         * 
         * To help understand how these maps are generated. We start Basic, and get more and more efficient as we progress down the function.
         */

        char[][] twoA = new char[10][10];
        char[][] twoB = new char[10][10];

        char[][] threeA = new char[10][10];
        char[][] threeB = new char[10][10];
        char[][] threeC = new char[10][10];

        char[][] fourA = new char[10][10];
        char[][] fourB = new char[10][10];
        char[][] fourC = new char[10][10];
        char[][] fourD = new char[10][10];

        char [][][] everyFourMaps = {fourA, fourB, fourC, fourD};

        char [][][] everyFiveMaps = new char[5][10][10];

        //Start every 2nd
        for(int i = 0; i < twoA.length; i++){
            for(int j = 0; j < twoA[i].length; j++){
                boolean even = ((i+j) % 2 == 0);
                
                if(even){
                    twoA[i][j] = 'X';
                } else {
                    twoA[i][j] = 'O';
                }
            }
        }
        for(int i = 0; i < twoB.length; i++){
            for(int j = 0; j < twoB[i].length; j++){
                boolean odd = ((i+j) % 2 == 1);
                
                if(odd){
                    twoB[i][j] = 'X';
                } else {
                    twoB[i][j] = 'O';
                }
            }
        }
        everyTwoMaps = new char[][][]{twoA, twoB};
        //Start every 3rd
        for(int i = 0; i < threeA.length; i++){
            for(int j = 0; j < threeA[i].length; j++){
                int xModulo = (i + 0) % 3;
                int yModulo = j % 3;
                boolean every3rd = (xModulo == yModulo);
                
                if(every3rd){
                    threeA[i][j] = 'X';
                } else {
                    threeA[i][j] = 'O';
                }
            }
        }
        for(int i = 0; i < threeB.length; i++){
            for(int j = 0; j < threeB[i].length; j++){
                int xMod = (i+1) % 3;
                int yMod = j % 3;
                boolean every3rd = (xMod == yMod);
                
                if(every3rd){
                    threeB[i][j] = 'X';
                } else {
                    threeB[i][j] = 'O';
                }
            }
        }
        for(int i = 0; i < threeC.length; i++){
            for(int j = 0; j < threeC[i].length; j++){
                int xMod = (i+2) % 3;
                int yMod = j % 3;
                boolean every3rd = (xMod == yMod);
                
                if(every3rd){
                    threeC[i][j] = 'X';
                } else {
                    threeC[i][j] = 'O';
                }
            }
        }
        everyThreeMaps = new char[][][]{threeA, threeB, threeC};
        //Start every 4th
        for(int i = 0; i < everyFourMaps.length; i++){
            for(int j = 0; j < everyFourMaps[i].length; j++){
                for(int k = 0; k < everyFourMaps[i][j].length; k++){
                    int xMod = (j + i) % 4;
                    int yMod = k % 4;
                    boolean everyX = (xMod == yMod);
                    
                    if(everyX){
                        everyFourMaps[i][j][k] = 'X';
                    } else {
                        everyFourMaps[i][j][k] = 'O';
                    }
                }
            }
        }
        //Start every 5th
        int mapIndex = 0;
        for(char[][] map : everyFiveMaps){
            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[i].length; j++){
                    if((i + mapIndex) % 5 == j % 5){
                        map[i][j] = 'X';
                    } else {
                        map[i][j] = 'O';
                    }
                }
            }
            mapIndex++;
        }
    }

    private void reGenerateTargettingBoard(int[][] currentBoard){
        ArrayList<ArrayList<Integer[]>> zones = new ArrayList<ArrayList<Integer[]>>();

    }

    private void findZoneBestMap(int[][] zone){

    }


    private static void printBoard(char[][] board){
        for(char[] row : board){
            for(char Char: row){
                System.out.print(Char);
            }
            System.out.println();
        }
    }
}
