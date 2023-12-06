import java.util.Scanner;
public class App {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("What's your name?");

        String name = input.next();
        Player player = new Player(name);
        player.sayHello();
        GamePlay game = new GamePlay();

        game.startGame();
        player.sayGoodBye();
    }
}