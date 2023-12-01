import java.util.Scanner;
public class App {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("What's your name?");

        String name = input.next();
        SayHello intro = new SayHello(name);
        GamePlay game1 = new GamePlay();

        // GameBoards.reMapTargetBoard();

        TargettingMaps target = new TargettingMaps();

        GameBoards.printAllBoards();
        game1.parseCord("A1");
        game1.startGame();
        // GameBoards.printAllBoards();

    }
}