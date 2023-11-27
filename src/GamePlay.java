import java.util.Scanner;
import java.util.ArrayList;
public class GamePlay {
    public String playMode = "Patrol Search";
    public boolean inGame = true;
    public Ship patrolBoat, submarine, destroyer, battleship, carrier;

    private ArrayList<Ship> unSunkHitShips = new ArrayList<Ship>();
    private Ship[] sunkHitShips;

    public char[][] currentBoard = new char[10][10];

    public void startGame(){
        patrolBoat = new Ship("Patrol Boat");
        submarine = new Ship("Submarine");
        destroyer = new Ship("Destroyer");
        battleship = new Ship("Battleship");   
        carrier = new Ship("Carrier");

        for(int i = 0; i < currentBoard.length; i++){
            for(int j = 0; j < currentBoard[i].length; j++){
                currentBoard[i][j] = '~';
            }
        }

        /// GAME LOOP \\\
        while(!playMode.equals("endGame")){
            System.out.println("continue game");
            System.out.println("Game loop: " + playMode);
            makeGuess();
            System.out.println("end loop");
        }
    }

    public void makeGuess(){
        int[] target = targetSelect();
        String gridCord = parseArray(target);
        Scanner input = new Scanner(System.in);

        System.out.println("Guess: " + gridCord);
        System.out.print("Did we hit? ");
        String result = input.nextLine().toLowerCase();

        if(result.equals("y") || result.equals("yes")){
            System.out.println("which boat?");
            String hitBoat = input.nextLine().toLowerCase();
            hit(hitBoat, gridCord, target);
        } else {
            updateBoard(target, 'O');
        }
    }


    private void hit(String hitBoatResult, String gridCord, int[] target){
        char boatChar = hitBoatResult.charAt(0);
        Boolean sunk;
        switch(boatChar){
            case 'p':
            patrolBoat.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(patrolBoat)){unSunkHitShips.add(patrolBoat);}
            if(patrolBoat.isSunk()){ sunkBoat(patrolBoat);}
            //clear impossible opens
            break;
            case 's':
            submarine.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(submarine.isSunk()){ sunkBoat(submarine);}
            break;
            case 'd':
            destroyer.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(destroyer.isSunk()){ sunkBoat(destroyer);}
            break;
            case 'b':
            battleship.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(battleship.isSunk()){ sunkBoat(battleship);}
            break;
            case 'c':
            carrier.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(carrier.isSunk()){ sunkBoat(carrier);}
            break;
            default:
            System.out.println("I'm not familiar with that boat...");
        }
    }

    private void sunkBoat(Ship ship){
        inGame = false;
        playMode = "endGame";
        System.out.println("The " + ship.name + " has been sunk!");
        updateGameMode();
        //real functions from here
        unSunkHitShips.remove(ship);
    }
    private void updateGameMode(){
        if(patrolBoat.isSunk()){
            playMode = "Sub Search";
            if(submarine.isSunk() && destroyer.isSunk()){
                playMode = "Battleship Search";
                if(battleship.isSunk()){
                    playMode = "Carrier Search";
                    if(carrier.isSunk()){
                        playMode = "endGame";
                    }
                }
            }
        }
    }
    private void updateBoard(int[] target, char targetResult){
        currentBoard[target[0]][target[1]] = Character.toUpperCase(targetResult);
        for(char[] row : currentBoard){
            for(char Char: row){
                System.out.print(Char);
            }
            System.out.println();
        }
    }

    public int[] parseCord(String cord){
        int[] arrayCord = new int[2];
        switch (cord.charAt(0)){
            case 'A':
            arrayCord[0] = 0;
            break;
            case 'B':
            arrayCord[0] = 1;
            break;
            case 'C':
            arrayCord[0] = 2;
            break;
            case 'D':
            arrayCord[0] = 3;
            break;
            case 'E':
            arrayCord[0] = 4;
            break;
            case 'F':
            arrayCord[0] = 5;
            break;
            case 'G':
            arrayCord[0] = 6;
            break;
            case 'H':
            arrayCord[0] = 7;
            break;
            case 'I':
            arrayCord[0] = 8;
            break;
            case 'J':
            arrayCord[0] = 9;
            break;
            default:
            arrayCord[0] = 0;
            break;
        };
        arrayCord[1] = Integer.valueOf(cord.substring(1)) - 1;
        return arrayCord;
    }
    public String parseArray(int[] arrayCord){
        String Cord;

        switch(arrayCord[0]){
            case 0:
            Cord = "A";
            break;
            case 1:
            Cord = "B";
            break;
            case 2:
            Cord = "C";
            break;
            case 3:
            Cord = "D";
            break;
            case 4:
            Cord = "E";
            break;
            case 5:
            Cord = "F";
            break;
            case 6:
            Cord = "G";
            break;
            case 7:
            Cord = "H";
            break;
            case 8:
            Cord = "I";
            break;
            case 9:
            Cord = "J";
            break;
            default: 
            Cord = "X";
            break;
        }
        String column = String.valueOf(arrayCord[1]+1);
        Cord += column;
        return Cord;
    }

    public String getMode(){
        return playMode;
    }

    private int[] targetSelect(){
        int[] target = new int[]{0,0};
        boolean searching = true;
        char[][] targetBoard = GameBoards.getBoard(playMode);

        while (searching){
            int randX = (int)Math.floor(Math.random() * 10); //0
            int randY = (int)Math.floor(Math.random() * 10); //5
            if(targetBoard[randX][randY] == 'X' && currentBoard[randX][randY] == '~'){
                target[0] = randX;
                target[1] = randY;
                searching = false;
            }
        }
        return target;
    }

    private int[] reTarget(){
        //horizontal vs vertical fit check
        //if both true random select
        //if second hit, continue,
        //if miss after second hit, change direction
        

        return 0,0;
    }

}
