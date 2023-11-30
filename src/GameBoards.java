public class GameBoards {
    private static char[][] everyTwo = {
        {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'}
    };

    private static char[][] everyThree = {
        {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'},
        {'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O'},
        {'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
        {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'},
        {'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O'},
        {'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
        {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'},
        {'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O'},
        {'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
        {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'}
    };

    private static char[][] everyFour = {
        {'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X'},
        {'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
        {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O'},
        {'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X'},
        {'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
        {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O'},
        {'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O'},
        {'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X'}
    };

    private static char[][] everyFive = {
        {'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
        {'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
        {'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O'},
        {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O'},
        {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X'},
        {'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
        {'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
        {'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O'},
        {'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O'},
        {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X'}
    };

    private static void printBoard(char[][] board){
        for(char[] row : board){
            for(char Char: row){
                System.out.print(Char);
            }
            System.out.println();
        }
    }

    public static void printAllBoards(){
        printBoard(everyTwo);
        System.out.println();
        printBoard(everyThree);
        System.out.println();
        printBoard(everyFour);
        System.out.println();
        printBoard(everyFive);
    }

    private static void reMapTargetBoard(){
        char[][] twoA = new char[10][10];
    }






    public static char[][] getBoard(String gamePhase){
        switch (gamePhase){
            case "Patrol Search":
            return everyTwo;
            case "Sub Search":
            return everyThree;
            case "Battleship Search":
            return everyFour;
            case "Carrier Search":
            return everyFive;
            default: 
            return everyTwo;
        }
    }
}
