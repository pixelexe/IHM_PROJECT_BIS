package application;

public class Hourglass {
    private int imageID;
    private static final String [] imageURL = {"start.png","synchroniser.png","passed.png"};

    public Hourglass(){
        this.imageID = 0;
    }
    public String nextImage(){
        this.imageID = (this.imageID +1) %3;
        return Hourglass.imageURL[this.imageID];
    }
    public int getImageID(){
        return this.imageID;
    }
}
