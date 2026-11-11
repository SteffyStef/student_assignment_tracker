package assignmenttracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class AssignmentFileManager {

    public static void saveAssignments(ArrayList<Assignment> assignments) {

        try (FileWriter writer = new FileWriter("assignments.txt")) {

            for (Assignment assignment : assignments) {
                writer.write(
                        assignment.getCourseName() + "," +
                                assignment.getAssignmentName() + "," +
                                assignment.getDueDate() + "," +
                                assignment.getPriority() + "," +
                                assignment.isCompleted() + "\n"
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving assignments: " + e.getMessage());
        }

    }

    public static ArrayList<Assignment> loadAssignments() {

        ArrayList<Assignment> assignments = new ArrayList<>();
        File file = new File("assignments.txt");

        if (!file.exists()) {
            return assignments;
        }

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println("Reading: " + line);
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Assignment assignment = new Assignment(
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3]
                    );
                    if (Boolean.parseBoolean(parts[4])) {
                        assignment.markCompleted();
                    }

                    assignments.add(assignment);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading assignments: " + e.getMessage());
        }

        return assignments;
    }

}
