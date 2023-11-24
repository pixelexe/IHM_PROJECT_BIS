package application;

import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.VBox;
import javafx.scene.Cursor;
import javafx.stage.Stage;

public class PageListeExos extends HeaderAbstract {

	private ComboBox<String> levelComboBox;
	private VBox exerciseContainer;
	VBox root;
	private List<HBox> listeHbox = new ArrayList<HBox>();

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
		//obtenir tout les conteneurs de root pour pouvoir changer leurs style
		HBox Header = (HBox) root.getChildren().get(0);
		Label label = (Label) root.getChildren().get(1);
		VBox Vbox = (VBox) root.getChildren().get(3);

		//on retir le style de base et on le remplace par le nouveau style choisie
		//jour / nuit
		root.setStyle("-fx-background-color :rgb(131, 131, 131);");
		Header.getStyleClass().removeAll(getDaymode(), getNightMode());
		Header.getStyleClass().addAll(style, "header");
		label.getStyleClass().removeAll(getDaymode(), getNightMode());
		label.getStyleClass().addAll(style, "titlelabel");
		Vbox.getStyleClass().removeAll(getDaymode(), getNightMode());
		Vbox.getStyleClass().addAll(style, "linked");

		//obtention des hyperlinks des exercices pour changer leurs CSS
		for (HBox hBox : listeHbox) {
			hBox.getStyleClass().removeAll(getDaymode(), getNightMode());
			hBox.getStyleClass().addAll(style, "smallhbox");
			Hyperlink hyper = (Hyperlink) hBox.getChildren().get(0);
			hyper.getStyleClass().removeAll(getDaymode(), getNightMode());
			hyper.getStyleClass().addAll(style, "linked");
		}
	}

	private VBox createRootPane() {
		//creation de la VBox principale
		VBox root = new VBox();
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: #f4f4f4;");

		Label titleLabel = new Label("EXERCICES D'APPLICATION - POO");
		titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

		levelComboBox = createLevelComboBox();

		exerciseContainer = createExerciseContainer();
		// on lui ajoute tout les childrens
		root.getChildren().addAll(Header(), titleLabel, levelComboBox, exerciseContainer);
		root.getStyleClass().addAll(getNightMode());

		return root;
	}

	private ComboBox<String> createLevelComboBox() {
		// création de la liste déroulante avec comboBox
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("L2", "L3", "M1", "M2");
		comboBox.setPromptText("Sélectionnez un niveau");
		comboBox.setStyle("-fx-background-color: #fff; -fx-text-fill: #333; -fx-font-size: 14px;");
		comboBox.setOnAction(e -> updateExercises());
		return comboBox;
	}

	private VBox createExerciseContainer() {
		// creation de la VBox qui contient les link des exos
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
		//sert à dynamiser la scroll
		exerciseContainer.getChildren().clear();
		String selectedLevel = levelComboBox.getSelectionModel().getSelectedItem();
		if (selectedLevel != null) {
			listeHbox.clear();

			switch (selectedLevel) {
				case "L2":
					addExercisesForLevel("L2", 12);
					break;
				case "L3":
					addExercisesForLevel("L3", 12);
					break;
				case "M1":
					addExercisesForLevel("M1", 12);
					break;
				case "M2":
					addExercisesForLevel("M2", 12);
					break;
			}
		}
	}

	private void addExercisesForLevel(String level, int numberOfExercises) {
		//liste des exercices 
		List<String> uniqueSubjects = Arrays.asList("Java Programming", "Object-Oriented Programming", "Advanced Java",
				"Java Mastery");

		for (int i = 1; i <= numberOfExercises; i++) {
			String subject = uniqueSubjects.get(i % uniqueSubjects.size());
			String exerciseName = String.format("%s-Exo %d: %s", level, i, subject);
			listeHbox.add(addExercise(exerciseName));
		}

		for (HBox hBox : listeHbox) {
			hBox.getStyleClass().addAll("ListeHBox");
		}
	}

	private void handleExerciseClick(String exerciseTitle) {
		//sert a switch entre les pages
		Button bouton = new Button("changement de page");
		
		PageExercice pageExercice = new PageExercice();
		Stage stage = new Stage();
		pageExercice.start(stage);
		
		Stage currentStage = (Stage) bouton.getScene().getWindow();
		currentStage.close();
	}
	
	

	private HBox addExercise(String title) {
		//ajoute les exercices

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		hbox.setPadding(new Insets(5));
		// création d l'hyperlink pour acceder aux exercices
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
		//image
		Image hourglassImage = new Image(getClass().getResourceAsStream("start.png"));
		ImageView hourglassView = new ImageView();
		hourglassView.setFitWidth(25);
		hourglassView.setFitHeight(25);
		//l'image est dans un bouton
		Button hourglassButton = new Button();
		hourglassButton.setGraphic(hourglassView);
		hourglassButton.setStyle("-fx-background-color: transparent;");
		hourglassButton.setOnAction(e -> {
			//on remplace l'ancienne image par la nouvelle
			Image hourglassImage2 = new Image(getClass().getResourceAsStream(hourGlass.nextImage()));
			hourglassView.setImage(hourglassImage2);
		});
		//augmente la taille quand on passe par dessus le boutton
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

	public static void main(String[] args) {
		launch(args);
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