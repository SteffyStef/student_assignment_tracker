package assignmenttracker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            System.out.println("Student Assignment Tracker");

        ArrayList<Assignment> assignments = AssignmentFileManager.loadAssignments();

            Scanner scanner = new Scanner(System.in);




        int choice = 0;

        while (choice != 4) {

            System.out.println("\n===== Student Assignment Tracker =====");
            System.out.println("1. Add Assignment");
            System.out.println("2. View Assignments");
            System.out.println("3. Mark Assignment Complete");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the leftover newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Remove the invalid input
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("You selected Add Assignment");


                System.out.print("Enter course name: ");
                String courseName = scanner.nextLine();

                System.out.print("Enter assignment name: ");
                String assignmentName = scanner.nextLine();

                System.out.print("Enter due date: ");
                String dueDate = scanner.nextLine();

                System.out.print("Enter priority (High, Medium, Low): ");
                String priority = scanner.nextLine();

                Assignment newAssignment = new Assignment(
                        courseName,
                        assignmentName,
                        dueDate,
                        priority
                );


                assignments.add(newAssignment);
                    AssignmentFileManager.saveAssignments(assignments);
                    break;


                case 2:
                    System.out.println("Your Assignments:");

                    for (Assignment assignment : assignments) {
                        System.out.println("Course: " + assignment.getCourseName());
                        System.out.println("Assignment: " + assignment.getAssignmentName());
                        System.out.println("Due Date: " + assignment.getDueDate());
                        System.out.println("Priority: " + assignment.getPriority());
                        System.out.println("Completed: " + assignment.isCompleted());
                        System.out.println("--------------------");
                    }
                    break;

                case 3:
                    System.out.println("You selected Mark Assignment Complete");
                    System.out.println("Select an assignment to complete:");

                    for (int i = 0; i < assignments.size(); i++) {
                        System.out.println((i + 1) + ". " +
                                assignments.get(i).getAssignmentName());
                    }
                    System.out.print("Enter assignment number: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    int assignmentNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (assignmentNumber >= 1 && assignmentNumber <= assignments.size()) {
                        assignments.get(assignmentNumber - 1).markCompleted();
                        AssignmentFileManager.saveAssignments(assignments);
                        System.out.println("Assignment marked complete!");
                    } else {
                        System.out.println("Invalid assignment number.");
                    }
                    break;

                case 4:
                    AssignmentFileManager.saveAssignments(assignments);
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
