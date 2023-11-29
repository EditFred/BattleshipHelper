import java.util.Scanner;
import java.util.ArrayList;
public class GamePlay {
    public String playMode = "Patrol Search";
    public boolean inGame = true;
    public Ship patrolBoat, submarine, destroyer, battleship, carrier;

    private ArrayList<Ship> unSunkHitShips = new ArrayList<Ship>();
    private ArrayList<Ship> sunkShips = new ArrayList<Ship>();

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
            if(result.equals("end")){playMode = "endGame";} // kill game
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
            if(!unSunkHitShips.contains(submarine)){unSunkHitShips.add(submarine);}
            if(submarine.isSunk()){ sunkBoat(submarine);}
            break;
            case 'd':
            destroyer.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(destroyer)){unSunkHitShips.add(destroyer);}
            if(destroyer.isSunk()){ sunkBoat(destroyer);}
            break;
            case 'b':
            battleship.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(battleship)){unSunkHitShips.add(battleship);}
            if(battleship.isSunk()){ sunkBoat(battleship);}
            break;
            case 'c':
            carrier.gotHit(gridCord);
            updateBoard(target, boatChar);
            if(!unSunkHitShips.contains(carrier)){unSunkHitShips.add(carrier);}
            if(carrier.isSunk()){ sunkBoat(carrier);}
            break;
            default:
            System.out.println("I'm not familiar with that boat...");
        }
    }

    private void sunkBoat(Ship ship){
        System.out.println("The " + ship.name + " has been sunk!");
        updateGameMode();
        //real functions from here
        unSunkHitShips.remove(ship);
        sunkShips.add(ship);
        if(sunkShips.size() == 5){
            playMode = "endGame";
            System.out.println("That's a wrap! GG");
        }
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
        if(unSunkHitShips.size() > 0){
            target = reTarget(unSunkHitShips.get(0));
        } else {
            while (searching){
                int randX = (int)Math.floor(Math.random() * 10);
                int randY = (int)Math.floor(Math.random() * 10);
                if(targetBoard[randX][randY] == 'X' && currentBoard[randX][randY] == '~'){
                    target[0] = randX;
                    target[1] = randY;
                    searching = false;
                }
            }
        }
        return target;
    }

    private int[] reTarget(Ship ship){
        boolean retargeting = true;
        int[] firstHit = parseCord(ship.getHitsLocation()[0]);
        int[] target = firstHit;
        int[] newTarget = target;

        System.out.println("Retargeting " + ship.name );

        if(ship.getOrientation().equals("unknown")){
            System.out.println("checking for orientation fit");
            if(!checkFit('v', ship)){
                System.out.println("Does not fit vertical");
                ship.setOrientation("horizontal");
            } else if(!checkFit('h', ship)) {
                System.out.println("Does not fit horizontal");
                ship.setOrientation("vertical");
            }
        }
        if(ship.getOrientation().equals("unknown")){
            char[] directions = {'u', 'r', 'd', 'l'};
            int direcIndex = (int)Math.floor(Math.random() * 4);
            char direction = directions[direcIndex];

            while(retargeting){
                if(checkFit(direction, ship)){
                    newTarget = goFromRandom(direction, target);
                    retargeting = false;
                } else {
                    direction = turnAround(direction);
                }
            }
        } else if(ship.getOrientation().equals("vertical")){
            char[] directions = {'u', 'd'};
            char direction = directions[(int)Math.floor(Math.random() * 2)];

            while(retargeting){
                if(direction == 'u'){
                    newTarget = goUp(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(currentBoard[newTarget[0]][newTarget[1]] == '~'){
                        retargeting = false;
                    } else if (currentBoard[newTarget[0]][newTarget[1]] == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                } else {
                    newTarget = goDown(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(currentBoard[newTarget[0]][newTarget[1]] == '~'){
                        retargeting = false;
                    } else if (currentBoard[newTarget[0]][newTarget[1]] == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                }
            }
        } else if(ship.getOrientation().equals("horizontal")){
            char[] directions = {'r', 'l'};
            char direction = directions[(int)Math.floor(Math.random() * 2)];

            while(retargeting){
                if(direction == 'r'){
                    newTarget = goRight(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(currentBoard[newTarget[0]][newTarget[1]] == '~'){
                        retargeting = false;
                    } else if (currentBoard[newTarget[0]][newTarget[1]] == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                } else {
                    newTarget = goLeft(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(currentBoard[newTarget[0]][newTarget[1]] == '~'){
                        retargeting = false;
                    } else if (currentBoard[newTarget[0]][newTarget[1]] == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                }
            }
        }       

        return newTarget;
    }

    //retargeting assist functions
    //direction can be changed to 1 and -1 to represent right/down and left/up
    private boolean outOfBounds(int[] nextTarget){
        for(int n : nextTarget){
            if(n < 0 || n > 9){
                return true;
            }
        }
        return false;
    }
    private char turnAround(char direction){
        switch (direction){
            case 'r':
                return 'l';
            case 'l':
                return 'r';
            case 'u':
                return 'd';
            case 'd':
                return 'u';
            default:
                return 'x';
        }
    }
    private int[] goUp(int[] cord){
        int[] newCord = cord;
        newCord[0] = newCord[0]-1;
        return newCord;
    }
    private int[] goDown(int[] cord){
        int[] newCord = cord;
        newCord[0] = newCord[0]+1;
        return newCord;
    }
    private int[] goRight(int[] cord){
        int[] newCord = cord;
        newCord[1] = newCord[1]+1;
        return newCord;
    }
    private int[] goLeft(int[] cord){
        int[] newCord = cord;
        newCord[1] = newCord[1]-1;
        return newCord;
    }
    private int[] goFromRandom(char direction, int[] cord){
        int[] newCord = cord;
        switch(direction){
            case 'l':
                newCord = goLeft(newCord);
                return newCord;
            case 'u':
                newCord = goUp(newCord);
                return newCord;
            case 'r':
                newCord = goRight(newCord);
                return newCord;
            case 'd':
                newCord = goDown(newCord);
                return newCord;
        }
        return newCord;
    }


    /* MASSIVE BUT IMPORTANT FUNCTION: checks if a ship can fit before making a valid guess */

    private boolean checkFit(char direction, Ship ship){
        int needed = ship.getLength() - 1;
        int open = 0;
        boolean checking = true;
        int[] firstHit = parseCord(ship.getHitsLocation()[0]);
        int[] nextCheck = {firstHit[0], firstHit[1]};

        System.out.println("First hit is " + firstHit[0] + firstHit[1]);

        switch (direction){
            case 'h':
                boolean rightOpen = true;
                while (checking){
                    if(rightOpen){
                        nextCheck = goRight(nextCheck);
                        if(outOfBounds(nextCheck)){
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            System.out.println("Should be " + firstHit[0] + firstHit[1]);
                            System.out.println("Back to first hit " + nextCheck[0] + nextCheck[1]);
                            rightOpen = false;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            rightOpen = false;
                            System.out.println("going left (inside checkFit)");
                        }
                    } else {
                        nextCheck = goLeft(nextCheck);
                        if(outOfBounds(nextCheck)){
                            checking = false;
                            break;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            System.out.println("done left");
                            checking = false;
                            break;
                        }
                    }
                }
                System.out.println("returning " + (open >= needed));
                return open >= needed;

            case 'v':
                boolean upOpen = true;
                while (checking){
                    if(upOpen){
                        nextCheck = goUp(nextCheck);
                        if(outOfBounds(nextCheck)){
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            System.out.println("Should be " + firstHit[0] + firstHit[1]);
                            System.out.println("Back to first hit " + nextCheck[0] + nextCheck[1]);
                            upOpen = false;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            upOpen = false;
                            System.out.println("going down (inside checkFit)");
                        }
                    } else {
                        nextCheck = goDown(nextCheck);
                        if(outOfBounds(nextCheck)){
                            checking = false;
                            break;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            System.out.println("done down");
                            checking = false;
                            break;
                        }
                    }
                }
                    System.out.println("returning " + (open >= needed));
                    return open >= needed;
            case 'u':
                while (checking){
                    nextCheck = goUp(nextCheck);
                    if(outOfBounds(nextCheck)){
                        checking = false;
                    } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                        open++;
                        continue;
                    } else {
                        checking = false;
                    }
                }
                System.out.println("Needs: " + needed + " Open: " + open);
                return open >= needed;
            case 'd':
                while (checking){
                    nextCheck = goDown(nextCheck);
                    if(outOfBounds(nextCheck)){
                        checking = false;
                    } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                        open++;
                        continue;
                    } else {
                        checking = false;
                    }
                }
                System.out.println("Needs: " + needed + " Open: " + open);
                return open >= needed;
            case 'l':
                while (checking){
                    nextCheck = goLeft(nextCheck);
                    if(outOfBounds(nextCheck)){
                        checking = false;
                    } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                        open++;
                        continue;
                    } else {
                        checking = false;
                    }
                }
                System.out.println("Needs: " + needed + " Open: " + open);
                return open >= needed;
            case 'r':
                while (checking){
                    nextCheck = goRight(nextCheck);
                    if(outOfBounds(nextCheck)){
                        checking = false;
                    } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                        open++;
                        continue;
                    } else {
                        checking = false;
                    }
                }
                System.out.println("Needs: " + needed + " Open: " + open);
                return open >= needed;
            default:
            return true;
        }
    }

    //can fit vertical up
    //can fit horizontal
    //can go up
    //can go down
    //can go right
    //can go left

}
