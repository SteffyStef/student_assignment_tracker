package assignmenttracker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

            System.out.println("Student Assignment Tracker");

            ArrayList<Assignment> assignments = new ArrayList<>();

            Scanner scanner = new Scanner(System.in);

        Assignment assignment1 = new Assignment(
                "CS399",
                "Project Plan",
                "October 10",
                "High"
        );
        assignments.add(assignment1);

        Assignment assignment2 = new Assignment(
                "CS301",
                "Java Skills Demo",
                "October 20",
                "Medium"
        );

        assignments.add(assignment2);

        assignment1.markCompleted();


        int choice = 0;

        while (choice != 4) {



            for (Assignment assignment : assignments) {
                System.out.println(assignment.getCourseName());
                System.out.println(assignment.getAssignmentName());
                System.out.println(assignment.getDueDate());
                System.out.println(assignment.getPriority());
                System.out.println(assignment.isCompleted());
                System.out.println("--------------------");
            }

            System.out.println("\n===== Student Assignment Tracker =====");
            System.out.println("1. Add Assignment");
            System.out.println("2. View Assignments");
            System.out.println("3. Mark Assignment Complete");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the leftover newline
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
                    int assignmentNumber = scanner.nextInt();
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


    }
}
