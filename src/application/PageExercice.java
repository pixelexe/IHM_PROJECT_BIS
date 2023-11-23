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

        Label titleLabel = createLabel(" L3 Exercice 1 : Hello World");
        titleLabel.getStyleClass().add("title-label");

        root = createVBox(10, Header(), titleLabel);

        inputTextArea = createStyledTextArea(getDaymode());
        inputTextArea.setText("public class HelloWorld {\n" +
                "    public static void main(String args[]) \n" +
                "    { \n" +
                "        System.out.println(\"Hello, World !\"); \n" +
                "    } \n" +
                "}");

        outputTextArea = createStyledTextArea(getDaymode());
        outputTextArea.setText("Output will appear here ...");
        outputTextArea.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.6);");
        outputTextArea.getStyleClass().addAll("outputTextArea");
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
        bottomVBox.getChildren().get(0).getStyleClass().add("output");

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

        buttonBox.setAlignment(Pos.CENTER_LEFT);

        buttonBox.getStyleClass().removeAll(getDaymode(), getNightMode());
        buttonBox.getStyleClass().add("title-label");

        MenuButton actionButton = createActionMenuButton();
        Button getInstructionsButton = createButton("Get Instructions");
        Button runButton = createGreenButton("RUN");
        Button stopButton = createRedButton("STOP");

        runButton.getStyleClass().add("green-button");
        stopButton.getStyleClass().add("red-button");

        buttonBox.getChildren().addAll(
                actionButton,
                getInstructionsButton,
                new Label(),
                runButton,
                stopButton);

        runButton.setOnAction(e -> runCode());
        stopButton.setOnAction(e -> stopCode());
        buttonBox.setPadding(new Insets(20, 20, 20, 10));
        return buttonBox;
    }

    public MenuButton createActionMenuButton() {
        MenuButton menuButton = new MenuButton("Action");
        menuButton.getItems().addAll(
                new MenuItem("Open in a new Tab"),
                new MenuItem("Save as a new Snip"),
                new MenuItem("My snips"),
                new MenuItem("Download as Maven"),
                new MenuItem("Request Help with this code"));

        menuButton.setPrefWidth(120); 
        menuButton.setPrefHeight(30); 

        menuButton.setStyle("-fx-font-size: 18px;");

        return menuButton;
    }

    private Label createOutputLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("title-label"); 
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