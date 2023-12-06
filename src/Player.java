public class Player {
    private String name;

    public Player(String name){
        this.name = name;
    }    

    public void sayHello(){
        System.out.printf("Hello there %s!\n", name);
    }

    public void sayGoodBye(){
        System.out.printf("%s, we did it! That's a wrap, GG!\n", name);
    }
}
