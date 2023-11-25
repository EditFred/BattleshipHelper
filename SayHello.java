public class SayHello {
    String name;

    public SayHello(String name){
        this.name = name;
        System.out.printf("Hello there %s!\n", this.name);
    }
}
