package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PageExercice extends HeaderAbstract {

    // Déclaration des éléments de l'interface utilisateur
    private TextArea inputTextArea;
    private TextArea outputTextArea;
    private VBox root;
    private VBox bottomVBox;
    private boolean dayOrNight;

    @Override
    public void start(Stage primaryStage) {

        // Création de l'en-tête de la page
        Label titleLabel = createLabel(" L3 Exercice 1 : Hello World");
        titleLabel.getStyleClass().addAll("title-label", "consolas-font");
        root = createVBox(10, Header(), titleLabel);

        // Création de la zone de texte pour l'entrée du code Java
        inputTextArea = createStyledTextArea(getDaymode());
        inputTextArea.setText("public class HelloWorld {\n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World !\"); \n" +
                "    } \n" +
                "}");
        inputTextArea.getStyleClass().add("consolas-font");

        // Création de la zone de texte pour la sortie du code
        outputTextArea = createStyledTextArea(getDaymode());
        outputTextArea.setText("Output will appear here...");
        outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");
        outputTextArea.getStyleClass().addAll("outputTextArea", "consolas-font");

        // Gestion des changements dans la zone de sortie
        outputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Output will appear here...")) {
                outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");
            } else {
                outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 1.0);");
            }
            if (newValue.isEmpty()) {
                outputTextArea.setText("Output will appear here...");
                if (this.dayOrNight == false) {
                    outputTextArea.setStyle("-fx-text-fill: white");
                }
            }
        });

        // Création de la boîte de boutons
        HBox buttonBox = createStyledButtonBox(getDaymode());

        // Création de l'étiquette pour la sortie
        Label outputLabel = createOutputLabel(" Output : ");
        outputLabel.getStyleClass().add("output-label");

        // Création de la boîte principale du bas de la page
        bottomVBox = createVBox(0, buttonBox, outputLabel, outputTextArea);
        bottomVBox.getChildren().get(0).getStyleClass().add("buttonVBox");
        bottomVBox.getChildren().get(0).getStyleClass().add("output");

        // Création du panneau de bord principal
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(root);
        borderPane.setCenter(inputTextArea);
        borderPane.setBottom(bottomVBox);

        // Création de la scène
        Scene scene = new Scene(borderPane, 900, 700);
        scene.getStylesheets().add(getClass().getResource("stylePageExercice.css").toExternalForm());
        primaryStage.setTitle("MIAGE CODE CRAFTING");

        // Configuration du mode jour/nuit
        setDayMode();

        // Configuration de la scène principale
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // Méthode pour appliquer un style spécifié
    public void applyStyle(String style) {
        if (style == getDaymode()) {
            this.dayOrNight = true;
        } else {
            this.dayOrNight = false;
        }
        HBox buttonBox = (HBox) this.bottomVBox.getChildren().get(0);
        buttonBox.getStyleClass().removeAll(getDaymode(), getNightMode());
        buttonBox.getStyleClass().addAll("buttonbox2", style);

        Label labelOutPut = (Label) this.bottomVBox.getChildren().get(1);
        labelOutPut.getStyleClass().removeAll(getDaymode(), getNightMode());
        labelOutPut.getStyleClass().addAll("labeloutput", style);

        this.bottomVBox.getStyleClass().removeAll(getDaymode(), getNightMode());
        this.bottomVBox.getStyleClass().addAll("labeloutput", style);

        if (style.equals("night-mode")) {
            outputTextArea.setStyle("-fx-text-fill: white;");
        } else {
            outputTextArea.setStyle("-fx-text-fill: black;");
            outputTextArea.setEditable(false);
        }

        inputTextArea.getStyleClass().removeAll(getDaymode(), getNightMode());
        inputTextArea.getStyleClass().add(style);
        root.getStyleClass().removeAll(getDaymode(), getNightMode());
        root.getStyleClass().add(style);
        outputTextArea.getStyleClass().removeAll(getDaymode(), getNightMode());
        outputTextArea.getStyleClass().add(style);
    }

    // Méthode pour créer la boîte de boutons stylisée
    public HBox createStyledButtonBox(String style) {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().removeAll(getDaymode(), getNightMode());
        buttonBox.getStyleClass().add("title-label");

        // Création du bouton d'action
        MenuButton actionButton = createActionMenuButton();
        Button getInstructionsButton = createButton("Get instructions");

        // Définition des actions des boutons

        getInstructionsButton.setOnAction(e -> {
            TextFlow lienaide = new TextFlow();
            Hyperlink lienHyper = new Hyperlink("https://java.l3.miage.dev/langage_java/premiere_classe.html");
            lienHyper.setOnAction(event -> getHostServices().showDocument(lienHyper.getText()));
            lienaide.getChildren().add(lienHyper);

            outputTextArea.setText("N'oubliez pas d'utiliser System.out.println();\nVotre code doit avoir un MAIN !\nPour plus d'information voir le cours chapitre 1 : Introduction  ");
            outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");
            outputTextArea.getStyleClass().addAll("outputTextArea", "consolas-font");

            if (!this.dayOrNight) {
                outputTextArea.setStyle("-fx-text-fill: white");
            }

        });

        Button runButton = createGreenButton("RUN");
        Button stopButton = createRedButton("STOP");

        runButton.getStyleClass().add("green-button");
        stopButton.getStyleClass().add("red-button");

        // Ajout des boutons à la boîte
        buttonBox.getChildren().addAll(
                actionButton,
                getInstructionsButton,
                new Label(),
                runButton,
                stopButton);

        // Définition des actions des boutons
        runButton.setOnAction(e -> runCode());
        stopButton.setOnAction(e -> stopCode());
        buttonBox.setPadding(new Insets(20, 20, 20, 10));
        return buttonBox;
    }

    // Méthode pour créer le bouton de menu d'action
    public MenuButton createActionMenuButton() {
        MenuButton menuButton = new MenuButton("Action");
        menuButton.getItems().addAll(
                new MenuItem("Ouvrir dans un nouvel onglet"),
                new MenuItem("Enregistrer en tant que nouvelle capture"),
                new MenuItem("Mes captures"),
                new MenuItem("Télécharger en tant que Maven"),
                new MenuItem("Demander de l'aide pour ce code"));

        menuButton.setPrefWidth(120);
        menuButton.setPrefHeight(30);

        menuButton.setStyle("-fx-font-size: 18px;");

        return menuButton;
    }

    // Méthode pour créer l'étiquette de sortie
    private Label createOutputLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("title-label");
        return label;
    }

    // Méthode pour exécuter le code
    public void runCode() {
        // Simulation de l'exécution du code et affichage du résultat
        String codeOutput = "Hello World !";
        outputTextArea.setText(codeOutput);
        if (this.dayOrNight == false) {
            outputTextArea.setStyle("-fx-text-fill: white");
        }
    }

    // Méthode pour arrêter l'exécution du code
    public void stopCode() {
        // Effacement de la zone de sortie
        outputTextArea.clear();
    }

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        launch(args);
    }
}
