import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tester extends User {

    public Tester(String id ,String email, String name, String password) {
        super(id ,email, name, password, "Tester");
    }

    public Tester() {
    }

    public void reportBug(String projectID, String title, String description, Severity severity) {
        if (projectID == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }

        List<String> projectfile = FilesStorage.readlines("projects/" + projectID + ".txt");
        Project project = new Project(projectID , projectfile.get(1) , projectfile.get(2) , projectfile.get(3) , List.of(projectfile.get(4).split(",")), List.of(projectfile.get(5).split(",")));
        BugReport bugreport = new BugReport(
                Auth.generateID("bug"),
                title,
                description,
                Status.NEW,
                severity,
                this,
                null,
                project
        );
//        project.addBugReport(bug);

        List<String> bugs = List.of(FilesStorage.readline("projects/" + projectID + ".txt" , 5).split(","));

        bugs.add(bugreport.getID());
        StringBuilder newline = new StringBuilder("");

        for (String bug : bugs){
            newline.append(bug + ",");
        }

        newline.deleteCharAt(newline.length() -1);

        FilesStorage.writeline("projects/" + projectID + ".txt" , 5 , newline.toString());

        List<String> bugreportStringList;
        bugreportStringList = List.of(bugreport.getID() , bugreport.getTitle() , bugreport.getDescription() , bugreport.getReporter().getID() , bugreport.getAssignee().getID() , bugreport.getAssignedProject().getID() , bugreport.getDateCreated().toString() , bugreport.getDateUpdated().toString());


        FilesStorage.writefile("BugReports" , bugreportStringList , bugreport.getID());
    }



    public void verifyBug(BugReport bug, Status newStatus) {
        if (newStatus != Status.FIXED && newStatus != Status.CLOSED) {
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
                Users.add(new Tester(
                        FilesStorage.readline("tester/" + file.getName() , 0),
                        FilesStorage.readline("tester/" + file.getName() , 1),
                        FilesStorage.readline("tester/" + file.getName() , 2),
                        FilesStorage.readline("tester/" + file.getName() , 3)
                ));
            }
        }

        return Users;
    }
}