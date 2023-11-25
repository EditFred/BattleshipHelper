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

    public static void printAllBoards(){
        printBoard(everyTwo);
        System.out.println();
        printBoard(everyThree);
        System.out.println();
        printBoard(everyFour);
        System.out.println();
        printBoard(everyFive);
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
