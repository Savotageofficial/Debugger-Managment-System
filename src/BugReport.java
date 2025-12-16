import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class BugReport {

    private String ID;
    private String title;
    private String description;
    private Status status;
    private Severity severity;
    private Tester reporter;
    private Developer assignee;
    private Project assignedProject;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private List<Comment> comments = new ArrayList<>();
    private List<Attachment> attachments = new ArrayList<>();
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BugReport(String id,
            String title,
            String description,
            Status status,
            Severity severity,
            Tester reporter,
            Developer assignee,
            Project assignedProject,
            List<Comment> comments,
            List<Attachment> attachments) {

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
        this.comments = comments;
        this.attachments = attachments;
    }

    public BugReport() {
    }

    public String getID() {
        return ID;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.dateUpdated = LocalDateTime.now();
        FilesStorage.writeline("bugreports" + ID + ".txt", 9, dateUpdated.format(format));

    }

    public Status getStatus() {
        return status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
        this.dateUpdated = LocalDateTime.now();
        FilesStorage.writeline("bugreports" + ID + ".txt", 9, dateUpdated.format(format));

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

    public Project getAssignedProject() {
        return assignedProject;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        StringBuilder commentsString = new StringBuilder("");

        for (Comment com : comments) {
            commentsString.append(com.getId() + ",");
        }

        commentsString.deleteCharAt(commentsString.length() - 1);
        FilesStorage.writeline("bugreports/" + ID + ".txt", 10, commentsString.toString());
        this.dateUpdated = LocalDateTime.now();
        FilesStorage.writeline("bugreports/" + ID + ".txt" , 9 , dateUpdated.format(format));
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
        this.dateUpdated = LocalDateTime.now();
    }
    public void changeStatus(Status newStatus){
        this.status= newStatus;
        this.dateUpdated=LocalDateTime.now();
        FilesStorage.writeline("bugreports/" + ID + ".txt" , 3 , newStatus.name());
    }
    public void assignTo(Developer developer){
        this.assignee=developer;
        this.dateUpdated = LocalDateTime.now();
        this.status= Status.ASSIGNED;
    }

    public List<BugReport> getBugReports() {
        String path = FilesStorage.FilePath + "bugreports";
        List<BugReport> bugReports = new ArrayList<BugReport>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                List<String> testerlist = FilesStorage.readlines("tester/" + FilesStorage.readline("bugreports/" + file.getName() , 5) + ".txt");

                Tester tester = new Tester(testerlist.get(0) , testerlist.get(1) , testerlist.get(2) , testerlist.get(3));


                        bugReports.add(FilesStorage.fetchBugData(file.getName().replace(".txt" , "")));
            }
        }

        return bugReports;

    }

    @Override
    public String toString() {
        return title + " | " + status + " | " + severity;
    }

}
