package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageAccueil extends HeaderAbstract {
    // Déclaration des éléments de l'interface utilisateur
    private static Label concepteurLabel;
    private static Label pourquoiMiageLabel;
    private static VBox acceuil;

    @Override
    public void start(Stage primaryStage) {
        // Configuration de la fenêtre principale
        primaryStage.setTitle("MIAGE CODE CRAFTING");

        // Création du titre
        Text titleLabel = new Text("MIAGE CODE CRAFTING");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 40));

        // Création des liens vers les réseaux sociaux
        Hyperlink link = createImageHyperlinkRes("", "linkedin.png", "https://www.linkedin.com/in/nicolasherbaut/");
        Hyperlink link2 = createImageHyperlinkRes2("", "sorbonne.png",
                "https://www.pantheonsorbonne.fr/page-perso/nherbaut");
        HBox reseauBox = new HBox(20);
        reseauBox.getChildren().addAll(link, link2);

        // Label pour présenter le concepteur
        concepteurLabel = createStyledLabel(getDaymode());
        concepteurLabel.setText(
                "Je suis Nicolas Herbaut, maître de conférences associé à l'Université Paris 1 Panthéon-Sorbonne. Mon expertise se situe dans les domaines de la blockchain pour les systèmes d'information, la softwarization réseau (SDN, NFV), la livraison de contenu et la techno-économie. J'ai obtenu mon doctorat en informatique à l'Université de Bordeaux, et depuis septembre 2018, je suis professeur associé à Paris 1 Panthéon-Sorbonne. Mes compétences comprennent les réseaux bayésiens, les modèles graphiques probabilistes, la gestion de Hyperledger Fabric, et j'ai contribué à divers projets de recherche. En plus de mon engagement académique, j'ai également participé à des initiatives bénévoles dans le domaine de l'informatique en France.");
        concepteurLabel.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        // Label pour expliquer pourquoi le site a été créé
        pourquoiMiageLabel = createStyledLabel(getDaymode());
        pourquoiMiageLabel.setText(
                "J'ai créé ce site afin de permettre une facilitation de l'utilisation de mes ressources. Je mets à disposition sur ce site des exercices ainsi que des cours pour l'entiereté des mes étudiants en L2, L3, M1 et M2");
        pourquoiMiageLabel.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        // Titre pour la section des ressources
        Text ressourcesTitle = new Text("\nRESSOURCES");
        ressourcesTitle.setFont(Font.font("Consolas", FontWeight.BOLD, 40));

        // Boutons pour accéder aux cours et aux exercices
        Button button1 = new Button("Cours");
        Button button2 = new Button("Exercice");

        // Configuration des boutons
        String policeConsolas = "Consolas";
        double taillePolice = 16;
        double largeurBouton = 100;
        double hauteurBouton = 40;

        button1.setFont(Font.font(policeConsolas, taillePolice));
        button2.setFont(Font.font(policeConsolas, taillePolice));

        button1.setMinSize(largeurBouton, hauteurBouton);
        button2.setMinSize(largeurBouton, hauteurBouton);

        button1.setStyle("-fx-background-color: rgb(154, 152, 152); -fx-border-color: black;");
        button2.setStyle("-fx-background-color: rgb(154, 152, 152); -fx-border-color: black;");

        // Gestion des événements de survol pour les boutons
        button1.setOnMouseEntered(e -> button1.setStyle("-fx-background-color: lightgray; -fx-border-color: black;"));
        button1.setOnMouseExited(
                e -> button1.setStyle("-fx-background-color: rgb(154, 152, 152); -fx-border-color: black;"));

        button2.setOnMouseEntered(e -> button2.setStyle("-fx-background-color: lightgray; -fx-border-color: black;"));
        button2.setOnMouseExited(
                e -> button2.setStyle("-fx-background-color: rgb(154, 152, 152); -fx-border-color: black;"));

        // Action des boutons
        button1.setOnAction(e -> getHostServices().showDocument("https://java.l3.miage.dev/index.html"));
        button2.setOnAction(e -> {
            PageListeExos pageExos = new PageListeExos();
            Stage stage = new Stage();
            pageExos.start(stage);
            Stage currentStage = (Stage) button2.getScene().getWindow();
            currentStage.close();
        });

        // Configuration de la mise en page de la page d'accueil
        acceuil = new VBox(20);
        acceuil.setAlignment(Pos.TOP_LEFT);
        acceuil.getChildren().addAll(Header(), titleLabel, reseauBox, concepteurLabel, pourquoiMiageLabel,
                ressourcesTitle, button1,
                button2);

        // Configuration de la scène
        Scene scene = new Scene(acceuil, 1500, 700);
        updateStyles(concepteurLabel, pourquoiMiageLabel, scene);
        scene.getStylesheets().add(getClass().getResource("PageAccueil.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void updateStyles(Label concepteurLabel, Label pourquoiMiageLabel, Scene scene) {
        // Méthode pour mettre à jour les styles de l'interface utilisateur
    }

    // Méthodes pour créer des hyperliens avec des images
    public Hyperlink createImageHyperlinkRes(String text, String imageUrl, String url) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(80);
        imageView.setFitHeight(75);

        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setGraphic(imageView);
        hyperlink.setOnAction(e -> getHostServices().showDocument(url));

        return hyperlink;
    }

    public Hyperlink createImageHyperlinkRes2(String text, String imageUrl, String url) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(76);
        imageView.setFitHeight(70);

        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setGraphic(imageView);
        hyperlink.setOnAction(e -> getHostServices().showDocument(url));

        return hyperlink;
    }

    // Méthode main pour lancer l'application JavaFX
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void applyStyle(String style) {
        // Méthode pour appliquer un style à certains éléments de l'interface utilisateur
        concepteurLabel.getStyleClass().removeAll(getDaymode(), getNightMode());
        concepteurLabel.getStyleClass().add(style);
        pourquoiMiageLabel.getStyleClass().removeAll(getDaymode(), getNightMode());
        pourquoiMiageLabel.getStyleClass().add(style);
        acceuil.getStyleClass().removeAll(getDaymode(), getNightMode());
        acceuil.getStyleClass().add(style);
    }

    // Méthodes non implémentées (à compléter si nécessaire)
    @Override
    public void runCode() {
        throw new UnsupportedOperationException("Unimplemented method 'runCode'");
    }

    @Override
    public void stopCode() {
        throw new UnsupportedOperationException("Unimplemented method 'stopCode'");
    }
}
