import java.util.ArrayList;
import java.util.List;


public class Developer extends User {

    private List<Project> assignedProjects = new ArrayList<>();
    private List<BugReport> assignedBugs = new ArrayList<>();


    public Developer(String name, String email, String password) {
        super(name, email, password);
        this.userType = "Developer";
    }


    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public List<BugReport> getAssignedBugs() {
        return assignedBugs;
    }


    public void assignProject(Project project) {
        if (project != null && !assignedProjects.contains(project)) {
            assignedProjects.add(project);
        }
    }

    public void removeProject(Project project) {
        assignedProjects.remove(project);
    }


    public void assignBug(BugReport bug) {
        if (bug != null && !assignedBugs.contains(bug)) {
            assignedBugs.add(bug);
        }
    }

    public void removeBug(BugReport bug) {
        assignedBugs.remove(bug);
    }


//    public static List<Developer> showUsers(List<User> users) {
//        List<Developer> devs = new ArrayList<>();
//        if (users == null) return devs;
//
//        for (User u : users) {
//            if (u instanceof Developer) {
//                devs.add((Developer) u);
//            }
//        }
//        return devs;
//    }

    @Override
    public List<String> getUsers(String target) {
        target= "developer";
        return super.getUsers(target);
    }
}