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

public abstract class HeaderAbstract extends Application {
    private final String DAY_MODE_STYLE = "day-mode";
    private final String NIGHT_MODE_STYLE = "night-mode";

    public void setDayMode() {
        applyStyle(this.DAY_MODE_STYLE);
    }

    public void setNightMode() {
        applyStyle(this.NIGHT_MODE_STYLE);
    }
    public String getDaymode(){
        return this.DAY_MODE_STYLE;
    }
    public String getNightMode(){
        return this.NIGHT_MODE_STYLE;
    }

    public abstract void applyStyle(String style);

    public Button createIconButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> action.run());
        return button;
    }

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

    public HBox createHBox(int spacing, Node... children) {
        HBox hbox = new HBox(spacing);
        hbox.getChildren().addAll(children);
        return hbox;
    }

    public VBox createVBox(int spacing, Node... children) {
        VBox vbox = new VBox(spacing);
        vbox.getChildren().addAll(children);
        return vbox;
    }

    public Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18px;");
        return button;
    }

    public Button createGreenButton(String text) {
        Button button = createButton(text);
        button.getStyleClass().add("green");
        return button;
    }

    public Button createRedButton(String text) {
        Button button = createButton(text);
        button.getStyleClass().add("red");
        return button;
    }

    public Label createLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("titleLabel");
        label.setMaxWidth(Double.MAX_VALUE); 
        label.setAlignment(Pos.CENTER);
        return label;
    }

    public TextArea createStyledTextArea(String style) {
        TextArea area = new TextArea();
        area.getStyleClass().addAll("text-area", style);
        area.setWrapText(true);
        return area;
    }
    public TextField createStyledTextField(String style){
        TextField textField = new TextField();
        textField.getStyleClass().addAll("text-area", style);
        return textField;
    }
    public Label createStyledLabel(String style){
        Label label = new Label();
        label.setWrapText(true);
        label.getStyleClass().addAll("label", style);
        return label;
    }

    public abstract void runCode();

    public abstract void stopCode();

    public HBox createHyperLinkHBoxHeader() {

        Hyperlink link1 = createImageHyperlink("", "image1.png", "https://java.miage.dev/home?filter=L3");
        Hyperlink link2 = createImageHyperlinkPetit("", "image2.png", "https://java.l3.miage.dev/index.html");
        Hyperlink link3 = createImageHyperlinkPetit("", "image3.png", "https://java.miage.dev/?gistId=aa37362b750d8656821aaab5fb70bd24");
        HBox hyperlink = new HBox(link1, link2, link3);

        link1.setOnAction(e -> {
            PageAccueil pageAccueil = new PageAccueil ();
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

        return hyperlink;
    }

    


    public HBox createHBoxDayNight(){
        Button sunButton = createIconButton("☀", this::setDayMode);
        Button moonButton = createIconButton("🌙", this::setNightMode);
        moonButton.getStyleClass().add("day-mode-button");
        sunButton.getStyleClass().add("day-mode-button");
        HBox modeDayNightHeaderHBoxRight = new HBox(sunButton,moonButton);
        return modeDayNightHeaderHBoxRight;
    }


    public HBox Header(){

        HBox sorbonne = new HBox();
        String cheminImage = "logosorb.png";
        Image image = new Image(getClass().getResourceAsStream(cheminImage));
        ImageView imageView = new ImageView(image);
        
        imageView.setFitWidth(220*1.2);
        imageView.setFitHeight(70*1.2);
        sorbonne.getChildren().add(imageView);
        
        Screen screen = Screen.getPrimary();
        HBox hyperLinkHeaderHBoxLeft = createHyperLinkHBoxHeader();

        HBox headerRight = createHBox(30, createHBoxDayNight());

        headerRight.setMaxWidth(screen.getBounds().getWidth()*0.4);
        sorbonne.setMaxWidth(screen.getBounds().getWidth()*0.2);
        sorbonne.setAlignment(Pos.CENTER);
        hyperLinkHeaderHBoxLeft.setMaxWidth(screen.getBounds().getWidth()*0.4);
        headerRight.setAlignment(Pos.CENTER_RIGHT);

        HBox headerHBox = new HBox(hyperLinkHeaderHBoxLeft,sorbonne,headerRight);
        HBox.setHgrow(headerRight, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(sorbonne, javafx.scene.layout.Priority.ALWAYS);

        HBox.setHgrow(hyperLinkHeaderHBoxLeft, javafx.scene.layout.Priority.ALWAYS);
        return headerHBox;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
