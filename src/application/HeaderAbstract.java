package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

// Classe abstraite pour fournir des fonctionnalités communes aux pages avec en-tête
public abstract class HeaderAbstract extends Application {
    // Constantes pour les styles de mode jour et nuit
    private final String DAY_MODE_STYLE = "day-mode";
    private final String NIGHT_MODE_STYLE = "night-mode";

    // Méthode pour définir le mode jour
    public void setDayMode() {
        applyStyle(this.DAY_MODE_STYLE);
    }

    // Méthode pour définir le mode nuit
    public void setNightMode() {
        applyStyle(this.NIGHT_MODE_STYLE);
    }

    // Méthode pour obtenir le style du mode jour
    public String getDaymode() {
        return this.DAY_MODE_STYLE;
    }

    // Méthode pour obtenir le style du mode nuit
    public String getNightMode() {
        return this.NIGHT_MODE_STYLE;
    }

    // Méthode abstraite pour appliquer le style spécifié
    public abstract void applyStyle(String style);

    // Méthode pour créer un bouton avec une action associée
    public Button createIconButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> action.run());
        return button;
    }

    // Méthode pour créer un lien hypertexte avec une image et une action associée
    public Hyperlink createImageHyperlink(String text, String imageUrl, String url) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(200);
        imageView.setFitHeight(70);

        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setGraphic(imageView);
        hyperlink.setOnAction(e -> getHostServices().showDocument(url));

        return hyperlink;
    }

    // Méthode pour créer un lien hypertexte plus petit avec une image et une action
    // associée
    public Hyperlink createImageHyperlinkPetit(String text, String imageUrl, String url) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(68);
        imageView.setFitHeight(72);

        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setGraphic(imageView);
        hyperlink.setOnAction(e -> getHostServices().showDocument(url));

        return hyperlink;
    }

    public Hyperlink createImageHyperlinkSorb(String text, String imageUrl, String url) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(280);
        imageView.setFitHeight(85);

        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setGraphic(imageView);
        hyperlink.setOnAction(e -> getHostServices().showDocument(url));

        return hyperlink;
    }

    // Méthode pour créer une HBox avec un espacement spécifié et des enfants
    public HBox createHBox(int spacing, Node... children) {
        HBox hbox = new HBox(spacing);
        hbox.getChildren().addAll(children);
        return hbox;
    }

    // Méthode pour créer une VBox avec un espacement spécifié et des enfants
    public VBox createVBox(int spacing, Node... children) {
        VBox vbox = new VBox(spacing);
        vbox.getChildren().addAll(children);
        return vbox;
    }

    // Méthode pour créer un bouton avec une taille de police spécifiée
    public Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18px;");
        return button;
    }

    // Méthode pour créer un bouton vert avec une taille de police spécifiée
    public Button createGreenButton(String text) {
        Button button = createButton(text);
        button.getStyleClass().add("green");
        return button;
    }

    // Méthode pour créer un bouton rouge avec une taille de police spécifiée
    public Button createRedButton(String text) {
        Button button = createButton(text);
        button.getStyleClass().add("red");
        return button;
    }

    // Méthode pour créer une étiquette avec un texte centré et une classe de style
    // spécifiée
    public Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("titleLabel");
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    // Méthode pour créer une zone de texte stylisée avec une classe de style
    // spécifiée
    public TextArea createStyledTextArea(String style) {
        TextArea area = new TextArea();
        area.getStyleClass().addAll("text-area", style);
        area.setWrapText(true);
        return area;
    }

    // Méthode pour créer un champ de texte stylisé avec une classe de style
    // spécifiée
    public TextField createStyledTextField(String style) {
        TextField textField = new TextField();
        textField.getStyleClass().addAll("text-area", style);
        return textField;
    }

    // Méthode pour créer une étiquette stylisée avec une classe de style spécifiée
    public Label createStyledLabel(String style) {
        Label label = new Label();
        label.setWrapText(true);
        label.getStyleClass().addAll("label", style);
        return label;
    }

    // Méthode abstraite pour exécuter du code spécifique à la page
    public abstract void runCode();

    // Méthode abstraite pour arrêter du code spécifique à la page
    public abstract void stopCode();

    // Méthode pour créer une HBox avec des liens hypertextes pour la navigation et
    // les boutons jour/nuit
    public HBox createHyperLinkHBoxHeader() {
        // Création de liens hypertextes avec des images pour la navigation
        Hyperlink link1 = createImageHyperlink("", "image1.png", "https://java.miage.dev/home?filter=L3");
        Hyperlink link2 = createImageHyperlinkPetit("", "image2.png", "https://java.l3.miage.dev/index.html");
        Hyperlink link3 = createImageHyperlinkPetit("", "image3.png",
                "https://java.miage.dev/?gistId=aa37362b750d8656821aaab5fb70bd24");

        // Ajout d'actions aux liens hypertextes pour changer de page
        link1.setOnAction(e -> {
            PageAccueil pageAccueil = new PageAccueil();
            Stage stage = new Stage();
            pageAccueil.start(stage);
            Stage currentStage = (Stage) link2.getScene().getWindow();
            currentStage.close();
        });

        link3.setOnAction(e -> {
            PageListeExos pageExos = new PageListeExos();
            Stage stage = new Stage();
            pageExos.start(stage);
            Stage currentStage = (Stage) link2.getScene().getWindow();
            currentStage.close();
        });

        // Création d'une HBox pour contenir les liens hypertextes
        HBox hyperlink = new HBox(link1, link2, link3);

        return hyperlink;
    }

    // Méthode pour créer une HBox avec des boutons jour/nuit
    public HBox createHBoxDayNight() {
        // Création de boutons pour le mode jour et nuit
        Button sunButton = createIconButton("☀", this::setDayMode);
        Button moonButton = createIconButton("🌙", this::setNightMode);
        moonButton.getStyleClass().add("day-mode-button");
        sunButton.getStyleClass().add("day-mode-button");

        // Création d'une HBox pour contenir les boutons jour/nuit
        HBox modeDayNightHeaderHBoxRight = new HBox(sunButton, moonButton);

        return modeDayNightHeaderHBoxRight;
    }

    // Méthode pour créer l'en-tête de la page dans une classe abstraite
    public HBox Header() {
        HBox sorbonne = new HBox();
        Hyperlink sorb = createImageHyperlinkSorb("", "logosorb.png",
                "https://miage.pantheonsorbonne.fr/miage-sorbonne");
        sorbonne.getChildren().add(sorb);
        Screen screen = Screen.getPrimary();

        // Création des liens hypertextes pour la navigation
        HBox hyperLinkHeaderHBoxLeft = createHyperLinkHBoxHeader();

        // Création des boutons jour/nuit
        HBox headerRight = createHBox(30, createHBoxDayNight());

        // Ajustement de la largeur maximale des éléments de l'en-tête
        headerRight.setMaxWidth(screen.getBounds().getWidth() * 0.4);
        sorbonne.setMaxWidth(screen.getBounds().getWidth() * 0.2);

        // Alignement des éléments de l'en-tête
        sorbonne.setAlignment(Pos.CENTER);
        hyperLinkHeaderHBoxLeft.setMaxWidth(screen.getBounds().getWidth() * 0.4);
        headerRight.setAlignment(Pos.CENTER_RIGHT);

        // Création de l'en-tête final avec les éléments alignés
        HBox headerHBox = new HBox(hyperLinkHeaderHBoxLeft, sorbonne, headerRight);
        HBox.setHgrow(headerRight, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(sorbonne, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(hyperLinkHeaderHBoxLeft, javafx.scene.layout.Priority.ALWAYS);
        headerHBox.setStyle("-fx-padding: 20px;");

        return headerHBox;
    }

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        launch(args);
    }
}
