public class Utility {
    
    public static boolean deBug = false;

    public static void printBoard(char[][] board){
        for(char[] row : board){
            for(char Char: row){
                System.out.print(Char);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printTwoBoards(char[][] board, char[][] board2){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(board[i][j]);
            }
            System.out.print(" ");
            for(int j = 0; j < 10; j++){
                System.out.print(board2[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
