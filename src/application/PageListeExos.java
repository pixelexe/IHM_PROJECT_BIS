package application;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PageListeExos extends HeaderAbstract {

	private ComboBox<String> levelComboBox;
	private VBox exerciseContainer;
	VBox root;
	private List<HBox> listeHbox = new ArrayList<HBox>();
	ComboBox comboBox2;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Exercices Université");

		root = createRootPane();
		Scene scene = new Scene(createScrollPane(root), 400, 600);
		scene.getStylesheets().add(getClass().getResource("stylesListeExos.css").toExternalForm());
		applyStyle(getDaymode());

		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void applyStyle(String style) {
		// HEADER
		HBox Header = (HBox) root.getChildren().get(0);
		// LABEL
		Label label = (Label) root.getChildren().get(1);

		VBox Vbox = (VBox) root.getChildren().get(3);

		root.setStyle("-fx-background-color :rgb(131, 131, 131);");
		Header.getStyleClass().removeAll(getDaymode(), getNightMode());
		Header.getStyleClass().addAll(style, "header");
		label.getStyleClass().removeAll(getDaymode(), getNightMode());
		label.getStyleClass().addAll(style, "titlelabel");
		Vbox.getStyleClass().removeAll(getDaymode(), getNightMode());
		Vbox.getStyleClass().addAll(style, "linked");
		for (HBox hBox : listeHbox) {
			hBox.getStyleClass().removeAll(getDaymode(), getNightMode());
			hBox.getStyleClass().addAll(style, "smallhbox");
			Hyperlink hyper = (Hyperlink) hBox.getChildren().get(0);
			hyper.getStyleClass().removeAll(getDaymode(), getNightMode());
			hyper.getStyleClass().addAll(style, "linked");
		}

	}

	private void applyStyleToChildren(Pane parent, String style) {
		for (Node child : parent.getChildren()) {
			if (child instanceof Pane) {
				applyStyleToChildren((Pane) child, style);
			}
			child.getStyleClass().removeAll("day-mode", "night-mode");

		}
	}

	private VBox createRootPane() {
		VBox root = new VBox();
		root.setSpacing(20);
		// root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: #f4f4f4;");

		Label titleLabel = new Label("Exercices d'application Université Paris 1 Panthéon-Sorbonne");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

		levelComboBox = createLevelComboBox();

		exerciseContainer = createExerciseContainer();

		root.getChildren().addAll(Header(), titleLabel, levelComboBox, exerciseContainer);
		root.getStyleClass().addAll(getNightMode());

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
					listeHbox.clear();
					listeHbox.add(addExercise("L1-Exo 1: Hello world"));
					listeHbox.add(addExercise("L1-Exo 2: Premier programme"));
					listeHbox.add(addExercise("L1-Exo 3: Conditions If-Else"));
					listeHbox.add(addExercise("L1-Exo 4: Boucles While"));
					listeHbox.add(addExercise("L1-Exo 5: Boucles For"));
					listeHbox.add(addExercise("L1-Exo 6: Nombres"));
					listeHbox.add(addExercise("L1-Exo 7: Chaines"));
					listeHbox.add(addExercise("L1-Exo 8: Booléens"));
					for (HBox hBox : listeHbox) {
						hBox.getStyleClass().addAll("ListeHBox");
					}
					break;
				case "L2":
					listeHbox.clear();
					listeHbox.add(addExercise("L2-Exo 1: Méthodes"));
					listeHbox.add(addExercise("L2-Exo 2: Programmation Orientée Objet"));
					listeHbox.add(addExercise("L2-Exo 3: Un Petit Chat"));
					listeHbox.add(addExercise("L2-Exo 4: Un Tableau"));
					listeHbox.add(addExercise("L2-Exo 5: Parcourir un tableau for/while"));
					listeHbox.add(addExercise("L2-Exo 6: Parcourir un tableau for-each"));
					listeHbox.add(addExercise("L2-Exo 7: Les Dictionnaires"));
					for (HBox hBox : listeHbox) {
						hBox.getStyleClass().addAll("ListeHBox");
					}
					break;
				case "L3":
					listeHbox.clear();
					listeHbox.add(addExercise("L3-Exo 1: Structure Fondamentale du Langage"));
					listeHbox.add(addExercise("L3-Exo 2: Poker Fermé"));
					listeHbox.add(addExercise("L3-Exo 3: Première Classe"));
					listeHbox.add(addExercise("L3-Exo 4: Ascenceurs de Tolbiac"));
					listeHbox.add(addExercise("L3-Exo 5: Les Types Primitifs"));
					listeHbox.add(addExercise("L3-Exo 6: Algorithme de César"));
					listeHbox.add(addExercise("L2-Exo 7: Reconnaissance de mains dans un jeu de Poker"));
					listeHbox.add(addExercise("L2-Exo 8: Les Méthodes"));
					listeHbox.add(addExercise("L2-Exo 9: Lambda 1"));
					listeHbox.add(addExercise("L2-Exo 10: Lambda 2"));
					for (HBox hBox : listeHbox) {
						hBox.getStyleClass().addAll("ListeHBox");
					}
					break;
				case "M1":
					listeHbox.clear();
					listeHbox.add(addExercise("M1-Ex 1: Flux Java"));
					listeHbox.add(addExercise("M1-Exo 2: Réflexion"));
					listeHbox.add(addExercise("M1-Exo 3: Gestionnaire de Classes"));
					listeHbox.add(addExercise("M1-Exo 4: Une Exception avancée"));
					listeHbox.add(addExercise("M1-Exo 5: Programmation Concurrente"));
					listeHbox.add(addExercise("M1-Exo 6: Injection de Dépendance"));
					listeHbox.add(addExercise("M1-Exo 7: Introduction au Machine Learning"));

					for (HBox hBox : listeHbox) {
						hBox.getStyleClass().addAll("ListeHBox");
					}
					break;
				case "M2":
					listeHbox.clear();
					listeHbox.add(addExercise("M2-Exo 1: Introspection et manipulation d'une Classe"));
					listeHbox.add(addExercise("M2-Exo 2: Conception orientée objet avancée"));
					listeHbox.add(addExercise("M1-Exo 3: Introduction au Machine Learning"));
					listeHbox.add(addExercise("M2-Exo 4: Introduction à la Blockchain"));
					listeHbox.add(addExercise("M2-Exo 5: Microservice et Conteneurs"));
					listeHbox.add(addExercise("M2-Exo 6: Intégration de systèmes d'entreprise"));
					listeHbox.add(addExercise("M2-Exo 7: Test avancés"));
					listeHbox.add(addExercise("M2-Exo 8: Machine Learning en Java"));
					listeHbox.add(addExercise("M2-Exo 9: Blockchain en Java"));
					for (HBox hBox : listeHbox) {
						hBox.getStyleClass().addAll("ListeHBox");
					}
					break;
			}
		}
	}

	private HBox addExercise(String title) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		// hbox.setStyle("-fx-background-color: #d0d0d0; -fx-background-radius: 10;");
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
		// hourglassButton.setOnAction(e -> handleHourglassClick(title));
		hourglassButton.setOnAction(e -> {
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
		return hbox;
	}

	private void handleExerciseClick(String exerciseTitle) {
		System.out.println("Exercice cliqué : " + exerciseTitle);
	}

	/*
	 * private void handleHourglassClick(String exerciseTitle) {
	 * System.out.println("Sablier cliqué pour l'exercice : " + exerciseTitle);
	 * }
	 */

	public static void main(String[] args) {
		launch(args);
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