public class Ship {
    // private boolean hit = false;
    private int hits = 0;
    private boolean sunk = false;

    public String name;
    private int length;

    public Ship(String name){
        this.name = name;

        switch(name){
            case "Patrol Boat":
            this.length = 2;
            case "Submarine":
            this.length = 3;
            case "Destroyer":
            this.length = 3;
            case "Battleship":
            this.length = 4;
            case "Carrier":
            this.length = 5;
            default:
            this.length = 5;
        }
    }

    public void gotHit(){
        hits++;
        if(hits >= length){
            sunk = true;
        }
    }

    public boolean isSunk(){ return sunk; }

}
