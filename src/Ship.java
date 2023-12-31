public class Ship {
    public String name;
    private char hitSignature;
    private int length;

    private int hits = 0;
    private boolean sunk = false;
    private String[] hitCords;
    private String orientation = "unknown";

    public Ship(String name){
        this.name = name;

        switch(name){
            case "Patrol Boat":
            this.length = 2;
            this.hitSignature = 'P';
            hitCords = new String[2];
            break;
            case "Submarine":
            this.length = 3;
            this.hitSignature = 'S';
            hitCords = new String[3];
            break;
            case "Destroyer":
            this.length = 3;
            this.hitSignature = 'D';
            hitCords = new String[3];
            break;
            case "Battleship":
            this.length = 4;
            this.hitSignature = 'B';
            hitCords = new String[4];
            break;
            case "Carrier":
            this.length = 5;
            this.hitSignature = 'C';
            hitCords = new String[5];
            break;
            default:
            this.length = 5;
            break;
        }
    }

    public void gotHit(String cords){
        hitCords[hits] = cords;
        hits++;
        if (hits == 2){
            setOrientationFromHits();
        }
        if(hits >= length){
            sunk = true;
            System.out.println("We sunk the " + name + "!");
        } else {
            System.out.println("The " + name + " has been hit!");
        }
    }

    public String getOrientation(){
        return orientation;
    }

    private void setOrientationFromHits(){
        if(hitCords[0].charAt(0) == hitCords[1].charAt(0)){
            orientation = "horizontal";
        } else {
            orientation = "vertical";
        }
    }

    public void setOrientation(String orientation) { this.orientation = orientation ; }

    public char getHitSig() { return hitSignature; }

    public String[] getHitsLocation(){ return hitCords; }

    public boolean isSunk(){ return sunk; }

    public int getLength(){ return length; }
}
