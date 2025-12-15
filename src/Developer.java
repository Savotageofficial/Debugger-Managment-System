import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Developer extends User {

    private List<String> assignedProjectsIDs = new ArrayList<>();
    private List<String> assignedBugsIDs = new ArrayList<>();

    public Developer(String id ,String name, String email, String password) {
        super(id ,name, email, password, "developer");
    }

    public Developer(String id, String name, String email, String password, List<String> assignedProjects, List<String> assignedBugs) {
        super(id, name, email, password, "developer");
        this.assignedProjectsIDs = assignedProjects;
        this.assignedBugsIDs = assignedBugs;
    }

    public Developer() {
        super();
    }

//    public Developer(String name, String email, String password) {
//        super(name, email, password);
//        this.userType = "Developer";
//    }


    public List<String> getAssignedProjectsIDs() {
        return assignedProjectsIDs;
    }

    public List<String> getAssignedBugs() {
        return assignedBugsIDs;
    }


    public void assignProject(String projectid) {
        if (projectid != null && !assignedProjectsIDs.contains(projectid)) {
            assignedProjectsIDs.add(projectid);
        }
    }

    public void removeProject(String projectid) {
        assignedProjectsIDs.remove(projectid);
    }


    public void assignBug(String bugid) {
        if (bugid != null && !assignedBugsIDs.contains(bugid)) {
            assignedBugsIDs.add(bugid);
        }
    }

    public void removeBug(String bug) {
        assignedBugsIDs.remove(bug);
    }

    public void updateBugStatus(BugReport bug){
        Status status = bug.getStatus();
        if (status == Status.NEW){
            bug.changeStatus(Status.IN_PROGRESS);

        } else if (status == Status.ASSIGNED) {
            bug.changeStatus(Status.IN_PROGRESS);

        } else if (status == Status.IN_PROGRESS) {
            bug.changeStatus(Status.FIXED);
        }
    }


    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "developer";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(FilesStorage.fetchDeveloper(file.getName().replace(".txt" , "")));
            }
        }

        return Users;
    }
}