import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Developer extends User {

    private List<String> assignedProjectsIDs = new ArrayList<>();
    private List<String> assignedBugsIDs = new ArrayList<>();


    public Developer(String id, String name, String email, String password, List<String> assignedProjects, List<String> assignedBugs) {
        super(id, name, email, password, "developer");
        this.assignedProjectsIDs = assignedProjects;
        this.assignedBugsIDs = assignedBugs;
    }

    public Developer() {
        super();
    }

    public List<String> getAssignedProjectsIDs() {
        return assignedProjectsIDs;
    }

    public List<String> getAssignedBugs() {
        return assignedBugsIDs;
    }


    public void assignProject(String projectid) {
        if (projectid != null && !assignedProjectsIDs.contains(projectid)) {
            assignedProjectsIDs.add(projectid);
            StringBuilder newliststring = new StringBuilder("");

            for(String proid : assignedProjectsIDs){
                newliststring.append(proid + ",");
            }
            newliststring.deleteCharAt(newliststring.length() -1);
            FilesStorage.writeline("developer/" + this.getID() + ".txt" , 5 , newliststring.toString());

        }

    }

    public void removeProject(String projectid) {
        assignedProjectsIDs.remove(projectid);

        StringBuilder newliststring = new StringBuilder("");

        for(String proid : assignedProjectsIDs){
            newliststring.append(proid + ",");
        }
        newliststring.deleteCharAt(newliststring.length() -1);
        FilesStorage.writeline("developer/" + this.getID() + ".txt", 5,newliststring.toString());
    }


    public void assignBug(String bugid) {
        if (bugid != null && !assignedBugsIDs.contains(bugid)) {
            assignedBugsIDs.add(bugid);
            StringBuilder newliststring = new StringBuilder("");

            for(String bugID : assignedBugsIDs){
                newliststring.append(bugID + ",");
            }
            newliststring.deleteCharAt(newliststring.length() -1);
            FilesStorage.writeline("developer/" + this.getID() + ".txt", 5,newliststring.toString());
        }
    }

    public void removeBug(String bug) {
        assignedBugsIDs.remove(bug);
        StringBuilder newliststring = new StringBuilder("");

        for(String bugid : assignedBugsIDs){
            newliststring.append(bugid + ",");
        }
        newliststring.deleteCharAt(newliststring.length() -1);
        FilesStorage.writeline("developer/" + this.getID() + ".txt", 5,newliststring.toString());

    }
    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "developer";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(new Developer(
                        FilesStorage.readline("developer/" + file.getName() , 0),
                        FilesStorage.readline("developer/" + file.getName() , 1),
                        FilesStorage.readline("developer/" + file.getName() , 2),
                        FilesStorage.readline("developer/" + file.getName() , 3),
                        List.of(FilesStorage.readline("developer/" + file.getName() , 5).split(",")),
                        List.of(FilesStorage.readline("developer/" + file.getName() , 6).split(","))
                ));
            }
        }

        return Users;
    }
}