package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PageAccueil extends HeaderAbstract {
    private static Label concepteurLabel;
    private static Label pourquoiMiageLabel;
    private static VBox root;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MIAGE CODE CRAFTINGgg");

        Text titleLabel = new Text("MIAGE CODE CRAFTING");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 40));

        Hyperlink link = new Hyperlink("https://www.linkedin.com/in/nicolasherbaut/");
        link.setOnAction(e -> getHostServices().showDocument(link.getText()));
        link.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        Hyperlink link2 = new Hyperlink("https://www.pantheonsorbonne.fr/page-perso/nherbaut");
        link2.setOnAction(e -> getHostServices().showDocument(link2.getText()));
        link2.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        concepteurLabel = createStyledLabel(getDaymode());
        concepteurLabel.setText(
                "Je suis Nicolas Herbaut, maître de conférences associé à l'Université Paris 1 Panthéon-Sorbonne. Mon expertise se situe dans les domaines de la blockchain pour les systèmes d'information, la softwarization réseau (SDN, NFV), la livraison de contenu et la techno-économie. J'ai obtenu mon doctorat en informatique à l'Université de Bordeaux, et depuis septembre 2018, je suis professeur associé à Paris 1 Panthéon-Sorbonne. Mes compétences comprennent les réseaux bayésiens, les modèles graphiques probabilistes, la gestion de Hyperledger Fabric, et j'ai contribué à divers projets de recherche. En plus de mon engagement académique, j'ai également participé à des initiatives bénévoles dans le domaine de l'informatique en France.");
        concepteurLabel.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        pourquoiMiageLabel = createStyledLabel(getDaymode());
        pourquoiMiageLabel.setText(
                "J'ai créé ce site afin de permettre une facilitation de l'utilisation de mes ressources. Je mets à disposition sur ce site des exercices ainsi que des cours pour l'entiereté des mes étudiants en L2, L3, M1 et M2");
        pourquoiMiageLabel.setFont(Font.font("Consolas", FontWeight.NORMAL, 18));

        Text ressourcesTitle = new Text("\nRESSOURCES");
        ressourcesTitle.setFont(Font.font("Consolas", FontWeight.BOLD, 40));

        Button button1 = new Button("Cours");
        Button button2 = new Button("Exercice");

        String policeConsolas = "Consolas";
        double taillePolice = 16; 

        button1.setFont(Font.font(policeConsolas, taillePolice));
        button2.setFont(Font.font(policeConsolas, taillePolice));

        double largeurBouton = 100; 
        double hauteurBouton = 40;
        button1.setMinSize(largeurBouton, hauteurBouton);
        button2.setMinSize(largeurBouton, hauteurBouton);

        button1.setOnAction(e -> getHostServices().showDocument("https://java.l3.miage.dev/index.html"));
        button2.setOnAction(e -> {
            PageListeExos pageExos = new PageListeExos();
            Stage stage = new Stage();
            pageExos.start(stage);
            Stage currentStage = (Stage) button2.getScene().getWindow();
            currentStage.close();
        });

        root = new VBox(20);
        root.setStyle("-fx-padding: 20px;");
        root.setAlignment(Pos.TOP_LEFT);
        root.getChildren().addAll(Header(), titleLabel, link, link2, concepteurLabel, pourquoiMiageLabel,
                ressourcesTitle, button1,
                button2);

        Scene scene = new Scene(root, 1500, 700);
        updateStyles(concepteurLabel, pourquoiMiageLabel, scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void updateStyles(Label concepteurLabel, Label pourquoiMiageLabel, Scene scene) {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void applyStyle(String style) {
        concepteurLabel.getStyleClass().removeAll(getDaymode(), getNightMode());
        concepteurLabel.getStyleClass().add(style);
        pourquoiMiageLabel.getStyleClass().removeAll(getDaymode(), getNightMode());
        pourquoiMiageLabel.getStyleClass().add(style);
        root.getStyleClass().removeAll(getDaymode(), getNightMode());
        root.getStyleClass().add(style);
    }

    @Override
    public void runCode() {
        throw new UnsupportedOperationException("Unimplemented method 'runCode'");
    }

    @Override
    public void stopCode() {
        throw new UnsupportedOperationException("Unimplemented method 'stopCode'");
    }
}