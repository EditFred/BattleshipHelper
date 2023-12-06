import java.util.ArrayList;
public class TargettingMaps {
    private int mapBookPageIndex = 0;

    private char [][] targetBoard = new char[10][10];
    private char[][][][] mapBook = new char[4][][][];




    public TargettingMaps(){
        generateTargetBoard();
        generateTargettingMapBook();
    }
    
    public char[][] getTargetMap(){
        return targetBoard;
    }

    public void setMapBookPage(int index){
        mapBookPageIndex = index;
    }

    private void generateTargetBoard(){
        for(int i = 0; i < targetBoard.length; i++){
            for(int j = 0; j < targetBoard[i].length; j++){
                if((i+j) % 2 == 0){
                    targetBoard[i][j] = 'X';
                } else {
                    targetBoard[i][j] = 'O';
                }
            }
        }
    }

    private void generateTargettingMapBook(){

        /// DEMONSTRATING 4 DIMENSIONAL ARRAYS \\\
        /* The purpose of this function is to create alternate maps that will be needed to remap the current target board,
         * as there may be a more efficient path, the most efficient path changes according to hits/misses once the program starts to
         * find the boats on the field. New Maps are create for all sizes. Every 2, 3, 4, and 5.
         * BUT this time an additional map is create for each possible offset.
         * everyTwo, has one additional offset, everyThree has two possible offsets, everyFour has three, and everyFive has four.
         * 
         */

        for(int i = 0; i < mapBook.length; i++){
            int[] mirrorList = {9,8,7,6,5,4,3,2,1,0};
            int[] chartsNeededList = {2,6,8,10};
            int chartsNeeded = chartsNeededList[i];
            int modulosNum = i + 2;
            char [][][] charts = new char[chartsNeeded][10][10];
            for(int j = 0; j < (chartsNeeded / 2); j++){
                char[][] chart = charts[j];
                char[][] chartMirror = charts[j + (chartsNeeded/2)];
                for(int k = 0; k < 10; k++){
                    for(int l = 0; l < 10; l++){
                        if((k + j ) % modulosNum == l % modulosNum){
                            chart[k][l] = 'X';
                            chartMirror[mirrorList[k]][l] = 'X';
                        } else {
                            chart[k][l] = 'O';
                            chartMirror[mirrorList[k]][l] = 'O';
                        }
                    }
                }
            }
            mapBook[i] = charts;
        }
    }

    public void reGenerateTargettingBoard(char[][] currentBoard, String searchMode){
        ArrayList<ArrayList<int[]>> zones = findZones(currentBoard, searchMode);

        for(int i = 0; i < zones.size(); i++){
            char[][] bestMap = findZoneBestMap(searchMode, zones.get(i));
            adjustTargetMap(bestMap, zones.get(i));
        }
    }

    private ArrayList<ArrayList<int[]>> findZones(char[][] currentBoard,  String searchMode){
        ArrayList<ArrayList<int[]>> zones = new ArrayList<ArrayList<int[]>>();
        char[][] copyBoard = new char[10][10];

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                copyBoard[i][j] = currentBoard[i][j];
            }
        }

        int currentZone = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(copyBoard[i][j] == '~'){
                    ArrayList<int[]>  zone = new ArrayList<int[]>();
                    int[] cords = {i,j};
                    zone.add(cords);
                    copyBoard[i][j] = (char)((currentZone + 1) +'0');
                    for(int k = 0; k < zone.size(); k++){
                        checkSurrounding(zone.get(k), zone, copyBoard, (char)((currentZone + 1) +'0'));
                    }
                    zones.add(zone);
                    currentZone++;
                }
            }
        }
        return zones;
    }

    private char[][] findZoneBestMap(String searchMode, ArrayList<int[]> zone){

        int bestMapTargetsNeeded = 100;
        int bestMapIndex = 0;

        int currentMapIndex = 0;

        for(char[][] map : mapBook[mapBookPageIndex]){
            int targets = 0;
            for(int i = 0; i < zone.size(); i++){
                int [] cord = zone.get(i);
                if(map[cord[0]][cord[1]] == 'X'){
                    targets++;
                }
            }
            if(targets < bestMapTargetsNeeded){
                bestMapTargetsNeeded = targets;
                bestMapIndex = currentMapIndex;
            }
            currentMapIndex++;
        }
        return mapBook[mapBookPageIndex][bestMapIndex];
    }

    private void adjustTargetMap(char[][] bestMap, ArrayList<int[]> zone){
        for(int i = 0; i < zone.size(); i++){
            int[] cordinates = zone.get(i);
            targetBoard[cordinates[0]][cordinates[1]] = bestMap[cordinates[0]][cordinates[1]];
        }
    }

    private boolean inBounds(int[] nextTarget){
        for(int n : nextTarget){
            if(n < 0 || n > 9){
                return false;
            }
        }
        return true;
    }
    private int[] goUp(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0]-1;
        newCord[1] = cord[1];
        return newCord;
    }
    private int[] goDown(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0]+1;
        newCord[1] = cord[1];
        return newCord;
    }
    private int[] goRight(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0];
        newCord[1] = cord[1]+1;
        return newCord;
    }
    private int[] goLeft(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0];
        newCord[1] = cord[1]-1;
        return newCord;
    }

    private void checkSurrounding(int[] cord, ArrayList<int[]> currentZone, char[][] boardCopy, char zoneCount){
        int[] up, down, right, left = new int[2];
        up = goUp(cord);
        down = goDown(cord);
        right = goRight(cord);
        left = goLeft(cord);

        if(inBounds(up) && boardCopy[up[0]][up[1]] == '~'){
            currentZone.add(up);
            boardCopy[up[0]][up[1]] = zoneCount;
        }
        if(inBounds(down) && boardCopy[down[0]][down[1]] == '~'){
            currentZone.add(down);
            boardCopy[down[0]][down[1]] = zoneCount;
        }
        if(inBounds(right) && boardCopy[right[0]][right[1]] == '~'){
            currentZone.add(right);
            boardCopy[right[0]][right[1]] = zoneCount;
        }
        if(inBounds(left) && boardCopy[left[0]][left[1]] == '~'){
            currentZone.add(left);
            boardCopy[left[0]][left[1]] = zoneCount;
        }
    }

    private void printBoard(char[][] board){
        for(char[] row : board){
            for(char Char: row){
                System.out.print(Char);
            }
            System.out.println();
        }
        System.out.println();
    }
    private void printZone(ArrayList<int[]> zone){

            System.out.println("Zone :");
        for(int i = 0; i< zone.size(); i++){
            int [] cord = zone.get(i);
            System.out.printf("[%s, %s] ,", cord[0], cord[1]);
        }
        System.out.println();
    }
}
