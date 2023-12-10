import java.util.Scanner;
import java.util.ArrayList;
public class GamePlay {
    public String playMode = "Patrol Search";
    private Ship patrolBoat, submarine, destroyer, battleship, carrier;

    private ArrayList<Ship> unSunkHitShips = new ArrayList<Ship>();
    private ArrayList<Ship> sunkShips = new ArrayList<Ship>();

    private PlayingBoard board = new PlayingBoard();
    private TargettingMaps radar = new TargettingMaps();

    public void startGame(){
        patrolBoat = new Ship("Patrol Boat");
        submarine = new Ship("Submarine");
        destroyer = new Ship("Destroyer");
        battleship = new Ship("Battleship");   
        carrier = new Ship("Carrier");


        /// GAME LOOP \\\
        while(!playMode.equals("endGame")){
            makeGuess();
        }
    }

    public void makeGuess(){
        int[] target = targetSelect();
        String gridCord = TargetSelect.parseArray(target);
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
        if(unSunkHitShips.size() > 0){
            return TargetSelect.reTarget(unSunkHitShips.get(0), board);
        } else {
            return TargetSelect.randomTarget(radar, board);
        }
    }  
}