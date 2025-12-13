import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String ID;
    private String name;
    private String description;
    private String BugSummary;
    private List<String> developersID=new ArrayList<String>();
    private List<BugReport> bugs = new ArrayList<>();

 public Project(String ID, String name, String description,String BugSummary) {
     this.name = name;
     this.description = description;
     this.ID = ID;
     this.BugSummary = BugSummary;
 }
//     this.bugs = new ArrayList<>();
 public String getID() {
     return ID;
 }
 public String getName() {
     return name;
 }
 public String getDescription() {
     return description;
 }
 public String getBugSummary() {
     return BugSummary;
 }

    public void setBugSummary(String bugSummary) {
        BugSummary = bugSummary;
    }
    public List<BugReport> getBugs() {
        return bugs;
    }

   //method 1
    public String readProjectFile() {
      return FilesStorage.readline("projects/"+ID+".txt",1);
 }

//public List<String> readProjectFile() {
//
//    String line;
//    List<String> lines = new ArrayList<>();
//
//    try (BufferedReader br =
//                 new BufferedReader(new FileReader("projects/pro1.txt"))) {
//
//        while ((line = br.readLine()) != null) {
//            lines.add(line);
//        }
//
//    } catch (Exception e) {
//        System.out.println("Error reading project info: " + e.getMessage());
//    }
//
//    return lines;
//}


    //method 2

    public void addDeveloper(String dev){
     String[] Devs = FilesStorage.readline("projects/" + ID + ".txt" , 4).split(",");
     List<String> Devslist = new ArrayList<String>(List.of(Devs));
     Devslist.add(dev);
     StringBuilder newline = new StringBuilder("");
     for (String developer : Devslist){
         newline.append(developer + ",");
     }
     newline.deleteCharAt(newline.length() -1);
     FilesStorage.writeline("projects/" + ID + ".txt" , 4 , newline.toString());
    }

    //method 3
    public void removeDeveloper(String dev) {
        String[] Devs_2 = FilesStorage.readline("projects/" + ID + ".txt", 4).split(",");
        List<String> Devs_2List = new ArrayList<>(List.of(Devs_2));

        if (Devs_2List.contains(dev)) {
            Devs_2List.remove(dev);
        } else {
            System.out.println("Developer not found!");
            return;
        }
        StringBuilder newline = new StringBuilder("");
        for (String developer : Devs_2List) {
            newline.append(developer).append(",");
        }
        if (newline.length() > 0) {
            newline.deleteCharAt(newline.length() - 1);
        }
        FilesStorage.writeline("projects/" + ID + ".txt", 4, newline.toString());
    }
    //method 4
    public void addBug(BugReport bug) {

        bugs.add(bug);
        String existing = FilesStorage.readline("projects/" + ID + ".txt", 5);
        String newLine;
        if (existing == null || existing.isEmpty()) {
            newLine = bug.getId();
        } else {
            newLine = existing + "," + bug.getId();
        }
        FilesStorage.writeline("projects/" + ID + ".txt", 5, newLine);
    }

    //method 5
    public List<BugReport> getBugsPerStatus(Status status) {

        List<BugReport> result = new ArrayList<>();

        for (BugReport bug : bugs) {
            if (bug.getStatus() == status) {
                result.add(bug);
            }
        }

        return result;
    }
    //method 6
    public boolean removeBug(String bugId) {
        BugReport toRemove = null;

        for (BugReport bug : bugs) {
            if (bug.getId().equals(bugId)) {
                toRemove = bug;
                break;
            }
        }

        if (toRemove == null) {
            System.out.println("Bug not found");
            return false;
        }

        bugs.remove(toRemove);

        String path = "projects/" + ID + ".txt";
        String existing = FilesStorage.readline(path, 5);

        if (existing == null || existing.isEmpty()) {
            return true;
        }

        List<String> ids = new ArrayList<>(List.of(existing.split(",")));
        ids.remove(bugId);

        StringBuilder newline = new StringBuilder();
        for (String id : ids) {
            newline.append(id).append(",");
        }

        if (newline.length() > 0) {
            newline.deleteCharAt(newline.length() - 1);
        }

        FilesStorage.writeline(path, 5, newline.toString());

        return true;
    }

    //method 7
    public void loadBugsFromFile() {

        bugs.clear();

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/bugs.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                String bugId = parts[0];
                String title = parts[1];
                String desc = parts[2];
                Status status = Status.valueOf(parts[3]);
                Severity severity = Severity.valueOf(parts[4]);
                String projectId = parts[5];

                if (projectId.equals(this.ID)) {
                    BugReport bug = new BugReport(bugId,title, desc, status, severity,null, this);
                    bugs.add(bug);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading bugs: " + e.getMessage());
        }
    }
















}





















