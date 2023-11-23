package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
import javafx.stage.Stage;

public class PageListeExos extends HeaderAbstract {

	private ComboBox<String> levelComboBox;
	private VBox exerciseContainer;


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Exercices Université");

		VBox root = createRootPane();
		Scene scene = new Scene(createScrollPane(root), 400, 600);
        primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private VBox createRootPane() {
		VBox root = new VBox();
		root.setSpacing(20);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: #f4f4f4;");

		Label titleLabel = new Label("Exercices d'application Université Paris 1 Panthéon-Sorbonne");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

		levelComboBox = createLevelComboBox();

		exerciseContainer = createExerciseContainer();

		root.getChildren().addAll(Header(),titleLabel, levelComboBox, exerciseContainer);

		return root;
	}

	private ComboBox<String> createLevelComboBox() {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("L1", "L2", "L3", "M1", "M2");
		comboBox.setPromptText("Sélectionnez un niveau");
		comboBox.setStyle("-fx-background-color: #fff; -fx-text-fill: #333; -fx-font-size: 14px;");
		comboBox.setOnAction(e -> updateExercises());
		return comboBox;
	}

	private VBox createExerciseContainer() {
		VBox container = new VBox();
		container.setSpacing(12);
		container.setPadding(new Insets(0));
		return container;
	}

	private ScrollPane createScrollPane(VBox content) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(content);
		scrollPane.setFitToWidth(true);
		return scrollPane;
	}

	private void updateExercises() {
		exerciseContainer.getChildren().clear();

		String selectedLevel = levelComboBox.getSelectionModel().getSelectedItem();
		if (selectedLevel != null) {
			switch (selectedLevel) {
			case "L1":
				addExercise("L1-Exo 1: Hello world");
				addExercise("L1-Exo 2: Premier programme");
				addExercise("L1-Exo 3: Conditions If-Else");
				addExercise("L1-Exo 4: Boucles While");
				addExercise("L1-Exo 5: Boucles For");
				addExercise("L1-Exo 6: Nombres");
				addExercise("L1-Exo 7: Chaines");
				addExercise("L1-Exo 8: Booléens");
				break;
			case "L2":
				addExercise("L2-Exo 1: Méthodes");
				addExercise("L2-Exo 2: Programmation Orientée Objet");
				addExercise("L2-Exo 3: Un Petit Chat");
				addExercise("L2-Exo 4: Un Tableau");
				addExercise("L2-Exo 5: Parcourir un tableau for/while");
				addExercise("L2-Exo 6: Parcourir un tableau for-each");
				addExercise("L2-Exo 7: Les Dictionnaires");
				break;
			case "L3":
				addExercise("L3-Exo 1: Structure Fondamentale du Langage");
				addExercise("L3-Exo 2: Poker Fermé");
				addExercise("L3-Exo 3: Première Classe");
				addExercise("L3-Exo 4: Ascenceurs de Tolbiac");
				addExercise("L3-Exo 5: Les Types Primitifs");
				addExercise("L3-Exo 6: Algorithme de César");
				addExercise("L2-Exo 7: Reconnaissance de mains dans un jeu de Poker");
				addExercise("L2-Exo 8: Les Méthodes");
				addExercise("L2-Exo 9: Lambda 1");
				addExercise("L2-Exo 10: Lambda 2");
				break;
			case "M1":
				addExercise("M1-Ex 1: Flux Java");
				addExercise("M1-Exo 2: Réflexion");
				addExercise("M1-Exo 3: Gestionnaire de Classes");
				addExercise("M1-Exo 4: Une Exception avancée");
				addExercise("M1-Exo 5: Programmation Concurrente");
				addExercise("M1-Exo 6: Injection de Dépendance");
				addExercise("M2-Exo 1: Introspection et manipulation d'une Classe");
				addExercise("M2-Exo 2: Conception orientée objet avancée");
				break;
			case "M2":
				addExercise("M1-Exo 7: Introduction au Machine Learning");
				addExercise("M2-Exo 4: Introduction à la Blockchain");
				addExercise("M2-Exo 3: Microservice et Conteneurs");
				addExercise("M2-Exo 4: Intégration de systèmes d'entreprise");
				addExercise("M2-Exo 5: Test avancés");
				addExercise("M2-Exo 6: Machine Learning en Java");
				addExercise("M2-Exo 7: Blockchain en Java");
				break;
			}
		}
	}

	private void addExercise(String title) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setStyle("-fx-background-color: #d0d0d0; -fx-background-radius: 10;");
		hbox.setSpacing(20);
		hbox.setPadding(new Insets(5));

		Hyperlink hyperlink = new Hyperlink(title);
		hyperlink.setStyle("-fx-font-size: 16px;");
		hyperlink.setOnAction(e -> handleExerciseClick(title));
		Hourglass hourGlass = new Hourglass();
		hyperlink.setOnMouseEntered(e -> {
			hyperlink.setCursor(Cursor.HAND);
			hyperlink.setStyle("-fx-font-size: 16px; -fx-text-fill: #007bff;");
		});
		hyperlink.setOnMouseExited(e -> {
			hyperlink.setCursor(Cursor.DEFAULT);
			hyperlink.setStyle("-fx-font-size: 16px;");
		});
		

		hbox.getChildren().add(hyperlink);

		Image hourglassImage = new Image(getClass().getResourceAsStream("start.png"));
		ImageView hourglassView = new ImageView();
		hourglassView.setFitWidth(16);
		hourglassView.setFitHeight(16);

		Button hourglassButton = new Button();
		hourglassButton.setGraphic(hourglassView);
		hourglassButton.setStyle("-fx-background-color: transparent;");
		//hourglassButton.setOnAction(e -> handleHourglassClick(title));
		hourglassButton.setOnAction(e ->{
			Image hourglassImage2 = new Image(getClass().getResourceAsStream(hourGlass.nextImage()));
			hourglassView.setImage(hourglassImage2);
			// hbox.getChildren().remove(hourglassButton);
			// hbox.getChildren().add(hourglassButton);
			// exerciseContainer.getChildren().remove(hbox);
			// exerciseContainer.getChildren().add(hbox);
			// hourglassButton.setGraphic(hourglassView);
			// hbox.getChildren().set(0, hourglassButton);
			// exerciseContainer.getChildren().set(0, hbox);
		});
		hourglassButton.setOnMouseEntered(e -> {
			hourglassButton.setCursor(Cursor.HAND);
			hourglassView.setScaleX(1.2);
			hourglassView.setScaleY(1.2);
		});
		hourglassButton.setOnMouseExited(e -> {
			hourglassButton.setCursor(Cursor.DEFAULT);
			hourglassView.setScaleX(1);
			hourglassView.setScaleY(1);
		});
		hourglassView.setImage(hourglassImage);
		hbox.getChildren().add(hourglassButton);

		exerciseContainer.getChildren().add(hbox);
	}

	private void handleExerciseClick(String exerciseTitle) {
		System.out.println("Exercice cliqué : " + exerciseTitle);
	}

	/*private void handleHourglassClick(String exerciseTitle) {
		System.out.println("Sablier cliqué pour l'exercice : " + exerciseTitle);
	}*/

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void applyStyle(String style) {
		
		throw new UnsupportedOperationException("Unimplemented method 'applyStyle'");
	}

	@Override
	public void runCode() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'runCode'");
	}

	@Override
	public void stopCode() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'stopCode'");
	}
}