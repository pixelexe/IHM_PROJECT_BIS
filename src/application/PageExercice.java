package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PageExercice extends HeaderAbstract {

    private TextArea inputTextArea;
    private TextArea outputTextArea;
    private VBox root;

    @Override
    public void start(Stage primaryStage) {

        // label
        Label titleLabel = createLabel(" L3 Exercice 1 : Hello World");
        titleLabel.getStyleClass().add("root");
        titleLabel.getStyleClass().add("title-label");

        root = createVBox(10, Header(), titleLabel);
        // root.getStyleClass().add("root");

        inputTextArea = createStyledTextArea(getDaymode());
        inputTextArea.setText("class HelloWorld \n" +
                "{ \n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World !\"); \n" +
                "    } \n" +
                "}");

        outputTextArea = createStyledTextArea(getDaymode());
        outputTextArea.setText("Output will appear here ...");
        outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");


        outputTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Output will appear here ...")) {
                outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");
            } else {
                outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 1.0);");
            }
            if (newValue.isEmpty()) {
                outputTextArea.setText("Output will appear here ...");
            }
        });

        HBox buttonBox = createStyledButtonBox(getDaymode());

        VBox bottomVBox = createVBox(0, buttonBox, createOutputLabel(" Output : "), outputTextArea);

        bottomVBox.getChildren().get(0).getStyleClass().add("buttonVBox");
        bottomVBox.getChildren().get(1).getStyleClass().add("output");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(root);
        borderPane.setCenter(inputTextArea);
        borderPane.setBottom(bottomVBox);

        Scene scene = new Scene(borderPane, 900, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        setDayMode();

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void applyStyle(String style) {
        inputTextArea.getStyleClass().removeAll(getDaymode(), getNightMode());
        inputTextArea.getStyleClass().add(style);
        root.getStyleClass().removeAll(getDaymode(), getNightMode());
        root.getStyleClass().add(style);
        outputTextArea.getStyleClass().removeAll(getDaymode(), getNightMode());
        outputTextArea.getStyleClass().add(style);

    }

    public HBox createStyledButtonBox(String style) {
        HBox buttonBox = new HBox(10);
        // buttonBox.setAlignment(Pos.BASELINE_CENTER);

        // BOUTON A GAUCHE
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        /*
         * //cette ligne ca met la hbox couleur merde donc si on change la couleur merde
         * en black c'est bon
         * buttonBox.getStyleClass().add("night-mode"); // Pour la HBox des boutons
         */
        buttonBox.getStyleClass().removeAll(getDaymode(), getNightMode());

        buttonBox.getStyleClass().add("button-box"); // Ajoutez 'button-box' ici

        buttonBox.getStyleClass().add(style);

        // Menu bouton pour menu déroulant de action
        MenuButton actionButton = createActionMenuButton();
        Button getInstructionsButton = createButton("Get Instructions");
        Button runButton = createGreenButton("RUN");
        Button stopButton = createRedButton("STOP");

        /*
         * actionButton.getStyleClass().removeAll("day-mode", "night-mode");
         * getInstructionsButton.getStyleClass().removeAll("day-mode", "night-mode");
         * //Style bouton pour couleur
         * runButton.getStyleClass().removeAll("day-mode", "night-mode");
         * stopButton.getStyleClass().removeAll(getDaymode(), getNightMode());
         */

        runButton.getStyleClass().add("green-button");
        stopButton.getStyleClass().add("red-button");

        buttonBox.getChildren().addAll(
                actionButton,
                getInstructionsButton,
                new Label(), // Ajoutez un espace (ou tout autre nœud) entre les boutons
                runButton,
                stopButton);

        runButton.setOnAction(e -> runCode());
        stopButton.setOnAction(e -> stopCode());

        // Add padding to the top of the button box
        buttonBox.setPadding(new Insets(20, 20, 20, 10)); // padding autour des boutons

        return buttonBox;
    }

    // MENU DEROULANT POUR ACTION
    public MenuButton createActionMenuButton() {
        MenuButton menuButton = new MenuButton("Action");
        menuButton.getItems().addAll(
                new MenuItem("Open in a new Tab"),
                new MenuItem("Save as a new Snip"),
                new MenuItem("My snips"),
                new MenuItem("Download as Maven"),
                new MenuItem("Request Help with this code"));
        // Set the preferred width to match other buttons
        menuButton.setPrefWidth(120); // Use the actual width of other buttons
        menuButton.setPrefHeight(30); // Use the actual height of otwoher buttons, if needed

        // Add action handlers for each menu item if necessary
        // Example:
        // menuButton.getItems().get(0).setOnAction(e -> openInNewTab());
        menuButton.setStyle("-fx-font-size: 18px;");

        return menuButton;
    }

    // NOAM pour modifier le style de output
    private Label createOutputLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("output-label"); // Ajoutez la nouvelle classe ici.
        return label;
    }

    public void runCode() {
        String codeOutput = "Hello world !";
        outputTextArea.setText(codeOutput);
    }

    public void stopCode() {
        outputTextArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }

}