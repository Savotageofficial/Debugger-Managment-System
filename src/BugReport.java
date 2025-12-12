import java.time.LocalDateTime;
import java.util.ArrayList;

public class BugReport {

    private String id;
    private String title;
    private String description;
    private Status status;
    private Severity severity;
    private Tester reporter;
    private Developer assignee;
    private Project assignedProject;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Attachment> attachments = new ArrayList<>();
    public BugReport(String id, String title, String description, Status status, Severity severity,
                     Tester reporter, Developer assignee,
                     Project assignedProject) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.severity = severity;
        this.reporter = reporter;
        this.assignee = assignee;
        this.assignedProject = assignedProject;

        this.dateCreated = LocalDateTime.now();
        this.dateUpdated = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.dateUpdated = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.dateUpdated = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.dateUpdated = LocalDateTime.now();
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
        this.dateUpdated = LocalDateTime.now();
    }

    public Tester getReporter() {
        return reporter;
    }

    public void setReporter(Tester reporter) {
        this.reporter = reporter;
        this.dateUpdated = LocalDateTime.now();
    }

    public Developer getAssignee() {
        return assignee;
    }

    public void setAssignee(Developer assignee) {
        this.assignee = assignee;
        this.dateUpdated = LocalDateTime.now();
    }

    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
        this.dateUpdated = LocalDateTime.now();
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        this.dateUpdated = LocalDateTime.now();
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
        this.dateUpdated = LocalDateTime.now();
    }
    public void changeStatus(Status newStatus){
        this.status= newStatus;
        this.dateUpdated=LocalDateTime.now();
    }
    public void assignTo(Developer developer){
        this.assignee=developer;
        this.dateUpdated = LocalDateTime.now();
        this.status= Status.ASSIGNED;
    }

}
