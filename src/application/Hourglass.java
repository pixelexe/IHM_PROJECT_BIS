package application;

public class Hourglass {
    private int imageID;
    // liste des png et l'ordre de rotation
    private static final String [] imageURL = {"start.png","synchroniser.png","passed.png"};

    public Hourglass(){
        this.imageID = 0;
    }
    public String nextImage(){
        // renvois l'image suivante dans la liste
        this.imageID = (this.imageID +1) %3;
        return Hourglass.imageURL[this.imageID];
    }
    public int getImageID(){
        return this.imageID;
    }
}
