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
}
