import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BugReport {

    private String ID; // basic
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

        this.ID = id;
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

    public BugReport() {
    }

    public String getID() {
        return ID;
    } //perfect


    public String getTitle() {
        return title;
    } //perfect

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

    public Project getAssignedProject() {
        return assignedProject;
    } //perfect

    public LocalDateTime getDateCreated() {
        return dateCreated;
    } // perfect

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    } // perfect

    public List<Comment> getComments() {
        return comments;
    } // perfect

    public List<Attachment> getAttachments() {
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

    public List<BugReport> getBugReports() {
        String path = FilesStorage.FilePath + "bugreports";
        List<BugReport> bugReports = new ArrayList<BugReport>();
        File directory = new File(path);
        File[] files = directory.listFiles();



        if (files != null) {
            for (File file : files) {
                List<String> testerlist = FilesStorage.readlines("tester/" + FilesStorage.readline("bugreports/" + file.getName() , 5) + ".txt");

                Tester tester = new Tester(testerlist.get(0) , testerlist.get(1) , testerlist.get(2) , testerlist.get(3));


                List<String> projectlist = FilesStorage.readlines("projects/" + FilesStorage.readline("bugreports/" + file.getName() , 5) + ".txt");

                Project project = new Project(projectlist.get(0) , projectlist.get(1) , projectlist.get(2) , projectlist.get(3) , List.of(projectlist.get(4).split(",")) , List.of(projectlist.get(5).split(",")));

                List<String> devlist = FilesStorage.readlines("developer/" + FilesStorage.readline("bugreports/" + file.getName() , 5) + ".txt");

                Developer developer = new Developer(projectlist.get(0) , projectlist.get(1) , projectlist.get(2) , projectlist.get(3) , List.of(projectlist.get(4).split(",")) , List.of(projectlist.get(5).split(",")));

                bugReports.add(new BugReport(
                        FilesStorage.readline("bugreports/" + file.getName() , 0),
                        FilesStorage.readline("bugreports/" + file.getName() , 1),
                        FilesStorage.readline("bugreports/" + file.getName() , 2),
                        Status.valueOf(FilesStorage.readline("bugreports/" + file.getName() , 3).toUpperCase()),
                        Severity.valueOf(FilesStorage.readline("bugreports/" + file.getName() , 4).toUpperCase()),
                        tester,
                        developer,
                        project
                ));
            }
        }

        return bugReports;

    }
}
