import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugReport {

    private String id; // basic
    private String title; // basic
    private String description; // basic
    private Status status; // nice , enum missing
    private Severity severity; // nice , enum missing
    private Tester reporter; // nice , tester class missing
    private Developer assignee;// nice
    private Project assignedProject; // nice
    private LocalDateTime dateCreated; // excellent
    private LocalDateTime dateUpdated; // excellent
    private List<Comment> comments = new ArrayList<>(); //nice , i changed the type of the comments to LIST<> since it has more functions and allows us to switch between List types not just ArrayList
    private List<Attachment> attachments = new ArrayList<>(); // nice , same thing here too

    public BugReport(String id,
                     String title,
                     String description,
                     Status status,
                     Severity severity,
                     Tester reporter,
                     Developer assignee,
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
    } //perfect , just remove assignee from here since it can be null as it is **optional**

    public String getId() {
        return id;
    } //perfect

    public void setId(String id) {
        this.id = id;
    } //ID cannot be set after initialization , remove setter

    public String getTitle() {
        return title;
    } //perfect

    public void setTitle(String title) {
        this.title = title;
        this.dateUpdated = LocalDateTime.now();
    } //Bug report shouldn't change title after being made , remove this setter

    public String getDescription() {
        return description;
    } //perfect

    public void setDescription(String description) {
        this.description = description;
        this.dateUpdated = LocalDateTime.now();
    } //perfect

    public Status getStatus() {
        return status;
    } //perfect

    public void setStatus(Status status) {
        this.status = status;
        this.dateUpdated = LocalDateTime.now();
    } // collides with change status , same functionality different names , remove setter and leave the change status

    public Severity getSeverity() {
        return severity;
    } // perfect

    public void setSeverity(Severity severity) {
        this.severity = severity;
        this.dateUpdated = LocalDateTime.now();
    } //perfect

    public Tester getReporter() {
        return reporter;
    } // perfect

    public void setReporter(Tester reporter) {
        this.reporter = reporter;
        this.dateUpdated = LocalDateTime.now();
    } //this will set the new reporter if the bug fix gets denied , excellent work

    public Developer getAssignee() {
        return assignee;
    } //perfect

    public void setAssignee(Developer assignee) {
        this.assignee = assignee;
        this.dateUpdated = LocalDateTime.now();
    } //assignee cannot be set , instead you use the Assign to function , remove this setter

    public Project getAssignedProject() {
        return assignedProject;
    } //perfect

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
        this.dateUpdated = LocalDateTime.now();
    } // cannot re Assign the bug to another project , unlogical to do so , remove this setter

    public LocalDateTime getDateCreated() {
        return dateCreated;
    } // perfect

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    } // perfect

    public ArrayList<Comment> getComments() {
        return comments;
    } // perfect

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    } // perfect

    public void addComment(Comment comment) {
        comments.add(comment);
        this.dateUpdated = LocalDateTime.now();
    } //EXCELLENT , i would've done it in another way but this is way better!

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
        this.dateUpdated = LocalDateTime.now();
    } // same excellence as addComment function , nice
    public void changeStatus(Status newStatus){
        this.status= newStatus;
        this.dateUpdated=LocalDateTime.now();
    } // Perfect , although, this collides with the status setter so you should remove the setter above
    public void assignTo(Developer developer){
        this.assignee=developer;
        this.dateUpdated = LocalDateTime.now();
        this.status= Status.ASSIGNED;
    } //perfecto

}
