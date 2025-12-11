import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Developer extends User {

//    private List<Project> assignedProjects = new ArrayList<>();
//    private List<BugReport> assignedBugs = new ArrayList<>();

    public Developer(String name, String email, String password, String userType) {
        super(name, email, password, userType);
    }

    public Developer() {
        super();
    }

//    public Developer(String name, String email, String password) {
//        super(name, email, password);
//        this.userType = "Developer";
//    }


//    public List<Project> getAssignedProjects() {
//        return assignedProjects;
//    }
//
//    public List<BugReport> getAssignedBugs() {
//        return assignedBugs;
//    }
//
//
//    public void assignProject(Project project) {
//        if (project != null && !assignedProjects.contains(project)) {
//            assignedProjects.add(project);
//        }
//    }
//
//    public void removeProject(Project project) {
//        assignedProjects.remove(project);
//    }
//
//
//    public void assignBug(BugReport bug) {
//        if (bug != null && !assignedBugs.contains(bug)) {
//            assignedBugs.add(bug);
//        }
//    }
//
//    public void removeBug(BugReport bug) {
//        assignedBugs.remove(bug);
//    }


    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "developer";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(new Developer(
                        FilesStorage.readline("developer/" + file.getName() , 1),
                        FilesStorage.readline("developer/" + file.getName() , 2),
                        FilesStorage.readline("developer/" + file.getName() , 3),
                        FilesStorage.readline("developer/" + file.getName() , 4)
                ));
            }
        }

        return Users;
    }
}