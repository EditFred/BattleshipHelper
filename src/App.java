import java.util.Scanner;
public class App {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("What's your name?");

        String name = input.next();
        SayHello intro = new SayHello(name);
        GamePlay game1 = new GamePlay();

        game1.parseCord("A1");
        // GameBoards.printAllBoards();

    }
}