package assignmenttracker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Student Assignent Tracker");

        Assignment assignment1 = new Assignment(
                "CS399",
                "Project Plan",
                "October 10",
                "High"
        );

        System.out.println(assignment1.getCourseName());
        System.out.println(assignment1.getAssignmentName());
        System.out.println(assignment1.getDueDate());
        System.out.println(assignment1.getPriority());
        System.out.println(assignment1.isCompleted());

        assignment1.markCompleted();

        System.out.println("After marking complete:");
        System.out.println(assignment1.isCompleted());
    }
}
