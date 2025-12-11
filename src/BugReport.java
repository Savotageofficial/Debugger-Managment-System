//import java.util.ArrayList;
//
//public class BugReport {
//
//    private String ID;
//    private String title;
//    private String description;
//    private Status status;
//    private Severity severity;
//
//    private Tester reporter;
//    private Developer assignee;
//    private Project assignedProject;
//
//    private String dateCreated;
//    private String dateUpdated;
//
//    private ArrayList<Comment> comments;
//    private ArrayList<Attachment> attachments;
//
//    public BugReport(String ID, String title, String description,
//                     Severity severity, Tester reporter, Project assignedProject,
//                     String dateCreated) {
//
//        this.ID = ID;
//        this.title = title;
//        this.description = description;
//        this.severity = severity;
//        this.reporter = reporter;
//        this.assignedProject = assignedProject;
//
//        this.status = BugStatus.NEW; // default
//        this.dateCreated = dateCreated;
//        this.comments = new ArrayList<>();
//        this.attachments = new ArrayList<>();
//    }
//
//    public String getId() {
//        return ID;
//    }
//
//    public void setId(String id) {
//        this.ID = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public BugStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(BugStatus status) {
//        this.status = status;
//    }
//
//    public Severity getSeverity() {
//        return severity;
//    }
//
//    public void setSeverity(Severity severity) {
//        this.severity = severity;
//    }
//
//    public Tester getReporter() {
//        return reporter;
//    }
//
//    public void setReporter(Tester reporter) {
//        this.reporter = reporter;
//    }
//
//    public Developer getAssignee() {
//        return assignee;
//    }
//
//    public void setAssignee(Developer assignee) {
//        this.assignee = assignee;
//    }
//
//    public Project getAssignedProject() {
//        return assignedProject;
//    }
//
//    public void setAssignedProject(Project assignedProject) {
//        this.assignedProject = assignedProject;
//    }
//
//    public String getDateCreated() {
//        return dateCreated;
//    }
//
//    public String getDateUpdated() {
//        return dateUpdated;
//    }
//
//    public void setDateUpdated(String dateUpdated) {
//        this.dateUpdated = dateUpdated;
//    }
//
//    public ArrayList<Comment> getComments() {
//        return comments;
//    }
//
//    public ArrayList<Attachment> getAttachments() {
//        return attachments;
//    }
//
//    public void addComment(Comment comment) {
//        comments.add(comment);
//    }
//
//    public void addAttachment(Attachment attachment) {
//        attachments.add(attachment);
//    }
//}
public class BugReport {
    private BugStatus status;
    private Severity severity;
}
