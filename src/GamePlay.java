import java.util.Scanner;

public class GamePlay {
    public String shipMode = "Patrol Search";
    public Ship patrolBoat, submarine, destroyer, battleship, carrier;

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
            System.out.println(result.toLowerCase());
        }
    }


    private void hit(String hitBoatResult, String gridCord, int[] target){
        char boatChar = hitBoatResult.charAt(0);
        Boolean sunk;
        switch(boatChar){
            case 'p':
            patrolBoat.gotHit(gridCord);
            //did sink?
            break;
            case 's':
            submarine.gotHit(gridCord);
            break;
            case 'd':
            destroyer.gotHit(gridCord);
            break;
            case 'b':
            battleship.gotHit(gridCord);
            break;
            case 'c':
            carrier.gotHit(gridCord);
            break;
            default:
            System.out.println("I'm not familiar with that boat...");
        }
    }

    private void updateBoard(int[] target, String )

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
        return shipMode;
    }

    private int[] targetSelect(){
        int[] target = new int[]{0,0};
        boolean searching = true;
        char[][] targetBoard = GameBoards.getBoard(shipMode);

        while (searching){
            int randY = (int)Math.floor(Math.random() * 10);
            int randX = (int)Math.floor(Math.random() * 10);
            if(targetBoard[randY][randX] == 'X' && currentBoard[randY][randX] == '~'){
                target[0] = randX;
                target[1] = randY;
                searching = false;
            }
        }
        return target;
    }



}
