import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tester extends User {

    public Tester(String id, String email, String name, String password) {
        super(id, email, name, password, "Tester");
    }

    public Tester() {
    }

    public void reportBug(Project project, String title, String description, Severity severity, List<Comment> comments,
            List<Attachment> attachments) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }

        BugReport bugreport = new BugReport(
                Auth.generateID("bug"),
                title,
                description,
                Status.NEW,
                severity,
                this,
                null,
                project,
                comments,
                attachments);


        List<String> bugs = project.getBugsid();

        if (bugs == null) {
            bugs = new ArrayList<>();
        }


        List<String> cleanBugs = new ArrayList<>();
        for (String b : bugs) {
            if (b != null && !b.equalsIgnoreCase("null") && !b.trim().isEmpty()) {
                cleanBugs.add(b);
            }
        }

        cleanBugs.add(bugreport.getID());
        StringBuilder newline = new StringBuilder("");

        for (String bug : cleanBugs) {
            newline.append(bug + ",");
        }

        newline.deleteCharAt(newline.length() - 1);

        FilesStorage.writeline("projects/" + project.getID() + ".txt", 5, newline.toString());

        FilesStorage.createBugFile(bugreport);
    }

    public void verifyBug(BugReport bug, Status newStatus) {
        if (newStatus != Status.FIXED && newStatus != Status.CLOSED && newStatus != Status.IN_PROGRESS) {
            throw new IllegalArgumentException("Tester can only set status to FIXED or CLOSED");
        }
        bug.changeStatus(newStatus);
    }

    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "tester";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(FilesStorage.fetchTester(file.getName().replace(".txt", "")));
            }
        }

        return Users;
    }
}