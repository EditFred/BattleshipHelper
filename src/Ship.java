public class Ship {
    // private boolean hit = false;
    private int hits = 0;
    private boolean sunk = false;

    public String name;
    private int length;
    private String[] hitCords;
    private String orientation = "unknown";

    public Ship(String name){
        this.name = name;

        switch(name){
            case "Patrol Boat":
            this.length = 2;
            hitCords = new String[2];
            break;
            case "Submarine":
            this.length = 3;
            hitCords = new String[3];
            break;
            case "Destroyer":
            this.length = 3;
            hitCords = new String[3];
            break;
            case "Battleship":
            this.length = 4;
            hitCords = new String[4];
            break;
            case "Carrier":
            this.length = 5;
            hitCords = new String[5];
            break;
            default:
            this.length = 5;
            break;
        }
    }

    public void gotHit(String cords){
        System.out.println(name + " got hit at " + cords);

        hitCords[hits] = cords;
        hits++;
        if (hits == 2){
            setOrientation();
        }
        if(hits >= length){
            sunk = true;
        }
    }

    public String getOrientation(){
        return orientation;
    }

    private void setOrientation(){
        if(hitCords[0].charAt(0) == hitCords[1].charAt(0)){
            orientation = "horizontal";
        } else {
            orientation = "vertical";
        }
    }

    public String[] getHitsLocation(){ return hitCords; }

    public boolean isSunk(){ return sunk; }

}
