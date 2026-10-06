package assignmenttracker;

public class Assignment {
    private String courseName;
    private String assignmentName;
    private String dueDate;
    private String priority;
    private boolean completed;

    public Assignment(String courseName, String assignmentName,
                      String dueDate, String priority) {

        this.courseName = courseName;
        this.assignmentName = assignmentName;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }
}
