package assignmenttracker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Student Assignent Tracker");
        ArrayList<Assignment> assignments = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

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

        for (Assignment assignment : assignments) {
            System.out.println(assignment.getCourseName());
            System.out.println(assignment.getAssignmentName());
            System.out.println(assignment.getDueDate());
            System.out.println(assignment.getPriority());
            System.out.println(assignment.isCompleted());
            System.out.println("--------------------");
        }


    }
}
