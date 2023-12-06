public class PlayingBoard {
    private char[][] board = new char[10][10];
    private int smallestShip = 2;

    public PlayingBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = '~';
            }
        }
    }





    public void removeTooSmallCavities(){
        int [] unShotTile = new int[2];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == '~'){
                    unShotTile[0] = i;
                    unShotTile[1] = j;
                    if(!TargetSelect.checkFit('h', unShotTile, smallestShip-, board) && !TargetSelect.checkFit('v', unShotTile, smallestShip-1, board)){
                        board[i][j] = 'O';
                    }
                }
            }
        }
    }

    public void updateBoard(int[] cordinate, char targetResult){
        board[cordinate[0]][cordinate[1]] = Character.toUpperCase(targetResult);
    }

    public char getBoardChar(int x, int y){ return board[x][y];}

    public boolean cordinateIsEmpty(int x, int y){ return board[x][y] == '~';}

    public char[][] getBoard(){ return board; }

    public void setSmallestShip(int newSmallest){ smallestShip = newSmallest; }

}
