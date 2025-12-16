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
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        FilesStorage.writeline("bugreports" + ID + ".txt" , 9 , dateUpdated.format(format));

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
        FilesStorage.writeline("bugreports" + ID + ".txt" , 9 , dateUpdated.format(format));

    }

    public Tester getReporter() {
        return reporter;
    }

    public void setReporter(Tester reporter) {
        this.reporter = reporter;
        this.dateUpdated = LocalDateTime.now();
        FilesStorage.writeline("bugreports" + ID + ".txt" , 9 , dateUpdated.format(format));
    }
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
        StringBuilder commentsString = new StringBuilder("");

        for (Comment com : comments){
            commentsString.append(com.getId());
        }
        FilesStorage.writeline("bugreports" + ID + ".txt" , 10 , commentsString.toString());
        this.dateUpdated = LocalDateTime.now();
        FilesStorage.writeline("bugreports" + ID + ".txt" , 9 , dateUpdated.format(format));
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
