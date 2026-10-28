package assignmenttracker;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;


    public class AssignmentTrackerApp extends Application {

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Student Assignment Tracker");
            Label titleLabel = new Label("Student Assignment Tracker");
            titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #22D3EE;");
            TextField assignmentNameField = new TextField();
            assignmentNameField.setPromptText("Enter assignment name");
            TextField courseField = new TextField();
            courseField.setPromptText("Enter course name");
            DatePicker dueDatePicker = new DatePicker();
            dueDatePicker.setPromptText("Select due date");
            VBox assignmentForm = new VBox(15);
            assignmentForm.getChildren().addAll(
                    assignmentNameField,
                    courseField,
                    dueDatePicker
            );
            assignmentForm.setVisible(false);
            assignmentForm.setManaged(false);



            Button addButton = new Button("➕ Add Assignment");
            addButton.setStyle("-fx-background-color: #A78BFA; -fx-text-fill: #101827; -fx-font-size: 15px; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-padding: 12px 24px;");
            addButton.setOnAction(event -> {
                assignmentForm.setVisible(true);
                assignmentForm.setManaged(true);
            });

            VBox root = new VBox(20);
            root.setAlignment(Pos.TOP_CENTER);
            root.setStyle("-fx-padding: 30px; -fx-background-color: #101827;");
            root.getChildren().addAll(titleLabel, addButton, assignmentForm);

            Scene scene = new Scene(root, 800, 500);
            primaryStage.setScene(scene);

            primaryStage.setWidth(800);
            primaryStage.setHeight(500);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


