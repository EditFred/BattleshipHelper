public class TargetSelect {

    private static char turnAround(char direction){
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

    public static boolean outOfBounds(int[] nextTarget){
        for(int n : nextTarget){
            if(n < 0 || n > 9){
                return true;
            }
        }
        return false;
    }

    public static int[] goUp(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0]-1;
        newCord[1] = cord[1];
        return newCord;
    }
    public static int[] goDown(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0]+1;
        newCord[1] = cord[1];
        return newCord;
    }
    public static int[] goRight(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0];
        newCord[1] = cord[1]+1;
        return newCord;
    }
    public static int[] goLeft(int[] cord){
        int[] newCord = new int[2];
        newCord[0] = cord[0];
        newCord[1] = cord[1]-1;
        return newCord;
    }
    private static int[] goFromRandom(char direction, int[] cord){
        switch(direction){
            case 'l':
                return goLeft(cord);
            case 'u':
                return goUp(cord);
            case 'r':
                return goRight(cord);
            case 'd':
                return goDown(cord);
        }
        return goRight(cord);
    }

    public static int[] reTarget(Ship ship, PlayingBoard board){
        boolean retargeting = true;
        int[] firstHit = parseCord(ship.getHitsLocation()[0]);
        int[] target = firstHit;
        int[] newTarget = target;

        if(ship.getOrientation().equals("unknown")){
            if(!checkFit('v', parseCord(ship.getHitsLocation()[0]), ship.getLength() - 1 , board.getBoard())){
                ship.setOrientation("horizontal");
            } else if(!checkFit('h', parseCord(ship.getHitsLocation()[0]), ship.getLength() - 1, board.getBoard())) {
                ship.setOrientation("vertical");
            }
        }
        if(ship.getOrientation().equals("unknown")){
            char[] directions = {'u', 'r', 'd', 'l'};
            int direcIndex = (int)Math.floor(Math.random() * 4);
            char direction = directions[direcIndex];

            while(retargeting){
                if(checkFit(direction, parseCord(ship.getHitsLocation()[0]), ship.getLength() - 1, board.getBoard())){
                    newTarget = goFromRandom(direction, target);
                    retargeting = false;
                } else {
                    direction = turnAround(direction);
                    newTarget = goFromRandom(direction, target);
                    retargeting = false;
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
                    } else if(board.cordinateIsEmpty(newTarget[0],newTarget[1])){
                        retargeting = false;
                    } else if (board.getBoardChar(newTarget[0],newTarget[1]) == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                } else {
                    newTarget = goDown(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(board.cordinateIsEmpty(newTarget[0],newTarget[1])){
                        retargeting = false;
                    } else if (board.getBoardChar(newTarget[0],newTarget[1]) == ship.getHitSig()){
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
                    } else if(board.cordinateIsEmpty(newTarget[0],newTarget[1])){
                        retargeting = false;
                    } else if (board.getBoardChar(newTarget[0],newTarget[1]) == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                } else {
                    newTarget = goLeft(newTarget);
                    if(outOfBounds(newTarget)){
                        direction = turnAround(direction);
                    } else if(board.cordinateIsEmpty(newTarget[0],newTarget[1])){
                        retargeting = false;
                    } else if (board.getBoardChar(newTarget[0],newTarget[1]) == ship.getHitSig()){
                        continue;
                    } else {
                        direction = turnAround(direction);
                    }
                }
            }
        }       

        return newTarget;
    }

    //refactor
    public static boolean checkFit(char direction, int[] startCord, int lengthNeeded, char[][] currentBoard){
        int needed = lengthNeeded;
        int open = 0;
        boolean checking = true;
        int[] firstHit = startCord;
        int[] nextCheck = {firstHit[0], firstHit[1]};

        switch (direction){
            case 'h':
                boolean rightOpen = true;
                while (checking){
                    if(rightOpen){
                        nextCheck = goRight(nextCheck);
                        if(outOfBounds(nextCheck)){
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            rightOpen = false;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            rightOpen = false;
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
                            checking = false;
                            break;
                        }
                    }
                }
                return open >= needed;

            case 'v':
                boolean upOpen = true;
                while (checking){
                    if(upOpen){
                        nextCheck = goUp(nextCheck);
                        if(outOfBounds(nextCheck)){
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            upOpen = false;
                        } else if(currentBoard[nextCheck[0]][nextCheck[1]] == '~'){
                            open++;
                            continue;
                        } else {
                            nextCheck[0] = firstHit[0];
                            nextCheck[1] = firstHit[1];
                            upOpen = false;
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
                            checking = false;
                            break;
                        }
                    }
                }
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
                return open >= needed;
            default:
            return true;
        }
    }

    public static int[] parseCord(String cord){
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
    public static String parseArray(int[] arrayCord){
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
