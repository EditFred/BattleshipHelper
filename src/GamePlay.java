import java.util.Scanner;
import java.util.ArrayList;
public class GamePlay {
    public String playMode = "Patrol Search";
    public boolean inGame = true;
    public Ship patrolBoat, submarine, destroyer, battleship, carrier;

    private ArrayList<Ship> unSunkHitShips = new ArrayList<Ship>();
    private ArrayList<Ship> sunkShips = new ArrayList<Ship>();

    private PlayingBoard board = new PlayingBoard();
    TargettingMaps radar = new TargettingMaps();

    public void startGame(){
        patrolBoat = new Ship("Patrol Boat");
        submarine = new Ship("Submarine");
        destroyer = new Ship("Destroyer");
        battleship = new Ship("Battleship");   
        carrier = new Ship("Carrier");


        /// GAME LOOP \\\
        while(!playMode.equals("endGame")){
            // System.out.println("Game loop: " + playMode);
            makeGuess();
        }
    }

    public void makeGuess(){
        int[] target = targetSelect();
        String gridCord = parseArray(target);
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Guess: " + gridCord);
        System.out.print("Did we hit? ");
        String result = input.nextLine().toLowerCase();

        if(result.equals("y") || result.equals("yes")){
            System.out.print("which boat? ");
            String hitBoat = input.nextLine().toLowerCase();
            hit(hitBoat, gridCord, target);
        } else {
            if(result.equals("end")){playMode = "endGame";} // kill game
            board.updateBoard(target, 'O');
        }
    }

    
    private void hit(String hitBoatResult, String gridCord, int[] target){
        char boatChar = hitBoatResult.charAt(0);
        switch(boatChar){
            case 'p':
            patrolBoat.gotHit(gridCord);
            board.updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(patrolBoat)){unSunkHitShips.add(patrolBoat);}
            if(patrolBoat.isSunk()){ sunkBoat(patrolBoat);}
            break;
            case 's':
            submarine.gotHit(gridCord);
            board.updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(submarine)){unSunkHitShips.add(submarine);}
            if(submarine.isSunk()){ sunkBoat(submarine);}
            break;
            case 'd':
            destroyer.gotHit(gridCord);
            board.updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(destroyer)){unSunkHitShips.add(destroyer);}
            if(destroyer.isSunk()){ sunkBoat(destroyer);}
            break;
            case 'b':
            battleship.gotHit(gridCord);
            board.updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(battleship)){unSunkHitShips.add(battleship);}
            if(battleship.isSunk()){ sunkBoat(battleship);}
            break;
            case 'c':
            carrier.gotHit(gridCord);
            board.updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(carrier)){unSunkHitShips.add(carrier);}
            if(carrier.isSunk()){ sunkBoat(carrier);}
            break;
            default:
            System.out.println("I'm not familiar with that boat...");
        }
    }

    private void sunkBoat(Ship ship){
        updateGameMode();
        board.removeTooSmallCavities();
        radar.reGenerateTargettingBoard(board.getBoard(), playMode);

        unSunkHitShips.remove(ship);
        sunkShips.add(ship);
        if(sunkShips.size() == 5){
            playMode = "endGame";
        }
    }
    private void updateGameMode(){
        if(patrolBoat.isSunk()){
            playMode = "Sub Search";
            radar.setMapBookPage(1);
            board.setSmallestShip(3);
            if(submarine.isSunk() && destroyer.isSunk()){
                playMode = "Battleship Search";
                radar.setMapBookPage(2);
                board.setSmallestShip(4);
                if(battleship.isSunk()){
                    playMode = "Carrier Search";
                    radar.setMapBookPage(3);
                    board.setSmallestShip(5);
                    if(carrier.isSunk()){
                        playMode = "endGame";
                    }
                }
            }
        }
    }

    private int[] targetSelect(){
        int[] target = new int[]{0,0};
        boolean searching = true;
        char [][] targetBoard = radar.getTargetMap();
        if(unSunkHitShips.size() > 0){
            target = TargetSelect.reTarget(unSunkHitShips.get(0), board);
        } else {
            while (searching){
                int randX = (int)Math.floor(Math.random() * 10);
                int randY = (int)Math.floor(Math.random() * 10);
                if(targetBoard[randX][randY] == 'X' && board.cordinateIsEmpty(randX, randY)){
                    target[0] = randX;
                    target[1] = randY;
                    searching = false;
                }
            }
        }
        return target;
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
        }
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
}