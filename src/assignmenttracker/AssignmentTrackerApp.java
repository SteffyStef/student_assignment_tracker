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
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonType;


    public class AssignmentTrackerApp extends Application {

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Student Assignment Tracker");
            ArrayList<Assignment> assignments = AssignmentFileManager.loadAssignments();
            Label titleLabel = new Label("Student Assignment Tracker");
            titleLabel.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #22D3EE;");
            TextField assignmentNameField = new TextField();
            assignmentNameField.setPromptText("Enter assignment name");
            TextField courseField = new TextField();
            courseField.setPromptText("Enter course name");
            DatePicker dueDatePicker = new DatePicker();
            dueDatePicker.setPromptText("Select due date");
            ComboBox<String> priorityBox = new ComboBox<>();
            priorityBox.getItems().addAll("High", "Medium", "Low");
            priorityBox.setPromptText("Select priority");
            VBox assignmentForm = new VBox(15);
            Button saveButton = new Button("Save Assignment");
            saveButton.setOnAction(event -> {
                String assignmentName = assignmentNameField.getText();
                String courseName = courseField.getText();
                String dueDate = dueDatePicker.getValue() == null
                        ? ""
                        : dueDatePicker.getValue().toString();
                String priority = priorityBox.getValue();
                if (assignmentName.isBlank() || courseName.isBlank()
                        || dueDate.isBlank() || priority == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Missing Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Please complete all fields.");
                    alert.showAndWait();
                    return;
                }

                Assignment newAssignment = new Assignment(
                        courseName,
                        assignmentName,
                        dueDate,
                        priority
                );

                assignments.add(newAssignment);
                AssignmentFileManager.saveAssignments(assignments);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Assignment Saved");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Your assignment was saved successfully!");
                successAlert.showAndWait();
                assignmentNameField.clear();
                courseField.clear();
                dueDatePicker.setValue(null);
                priorityBox.setValue(null);
                assignmentForm.setVisible(false);
                assignmentForm.setManaged(false);

            });

            assignmentForm.getChildren().addAll(
                    assignmentNameField,
                    courseField,
                    dueDatePicker,
                    priorityBox,
                    saveButton
            );
            assignmentForm.setVisible(false);
            assignmentForm.setManaged(false);

            ListView<String> assignmentListView = new ListView<>();
            assignmentListView.setPrefHeight(200);
            assignmentListView.setStyle(
                    "-fx-background-color: #1E293B;" +
                            "-fx-control-inner-background: #1E293B;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-background-radius: 10px;"
            );
            assignmentListView.setVisible(false);
            assignmentListView.setManaged(false);

            Button viewButton = new Button("View Assignments");
            viewButton.setStyle(
                    "-fx-background-color: #A78BFA;" +
                            "-fx-text-fill: #101827;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-padding: 10px 20px;"
            );
            Button deleteButton = new Button("Delete Assignment");
            deleteButton.setStyle(
                    "-fx-background-color: #F87171;" +
                            "-fx-text-fill: #101827;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-padding: 10px 20px;"
            );
            deleteButton.setOnAction(event -> {
                int selectedIndex = assignmentListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex == -1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Assignment Selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an assignment to delete.");
                    alert.showAndWait();
                    return;
                }
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirm Delete");
                confirmAlert.setHeaderText("Delete Assignment?");
                confirmAlert.setContentText("Are you sure you want to delete this assignment?");

                ButtonType response = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (response != ButtonType.OK) {
                    return;
                }
                assignments.remove(selectedIndex);
                AssignmentFileManager.saveAssignments(assignments);
                viewButton.fire();

            });
            Button completeButton = new Button("Mark Completed");
            completeButton.setStyle(
                    "-fx-background-color: #34D399;" +
                            "-fx-text-fill: #101827;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 10px;" +
                            "-fx-padding: 10px 20px;"
            );
            completeButton.setOnAction(event -> {
                int selectedIndex = assignmentListView.getSelectionModel().getSelectedIndex();

                if (selectedIndex == -1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Assignment Selected");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an assignment first.");
                    alert.showAndWait();
                    return;
                }
                Assignment selectedAssignment = assignments.get(selectedIndex);
                selectedAssignment.markCompleted();
                AssignmentFileManager.saveAssignments(assignments);
                viewButton.fire();

            });


            viewButton.setOnAction(event -> {
                assignmentListView.getItems().clear();

                for (Assignment assignment : assignments) {
                    assignmentListView.getItems().add(
                            assignment.getCourseName() + " - " +
                                    assignment.getAssignmentName() + " - " +
                                    assignment.getDueDate() + " - " +
                                    assignment.getPriority() + " - " +
                                    (assignment.isCompleted() ? "Completed" : "Pending")
                    );
                }
                assignmentListView.setVisible(true);
                assignmentListView.setManaged(true);

            });
            Button addButton = new Button("➕ Add Assignment");
            addButton.setStyle("-fx-background-color: #A78BFA; -fx-text-fill: #101827; -fx-font-size: 15px; -fx-font-weight: bold; -fx-background-radius: 10px; -fx-padding: 12px 24px;");
            addButton.setOnAction(event -> {
                assignmentForm.setVisible(true);
                assignmentForm.setManaged(true);
            });

            VBox root = new VBox(20);
            root.setAlignment(Pos.TOP_CENTER);
            root.setStyle("-fx-padding: 30px; -fx-background-color: #101827;");
            root.getChildren().addAll(
                    titleLabel,
                    addButton,
                    viewButton,
                    completeButton,
                    deleteButton,
                    assignmentForm,
                    assignmentListView
            );

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


