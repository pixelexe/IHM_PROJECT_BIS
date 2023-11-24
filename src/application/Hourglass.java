package application;

public class Hourglass {
    private int imageID;
    
    // Liste des noms de fichiers PNG et leur ordre de rotation
    private static final String[] imageURL = {"start.png", "synchroniser.png", "passed.png"};

    // Constructeur de la classe Hourglass
    public Hourglass() {
        this.imageID = 0;
    }

    // Méthode pour obtenir le chemin de l'image suivante
    public String nextImage() {
        this.imageID = (this.imageID + 1) % 3;
        return Hourglass.imageURL[this.imageID];
    }

    // Méthode pour obtenir l'identifiant de l'image actuelle
    public int getImageID() {
        return this.imageID;
    }
}
