import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String ID;
    private String name;
    private String description;
    private String BugSummary;
    private List<String> developersID = new ArrayList<String>();
    private List<String> bugsid=new ArrayList<>();

 public Project(String ID, String name, String description , String bugSummary, List<String> developersID , List<String> bugsid) {
     this.ID = ID;
     this.name = name;
     this.description = description;
     this.BugSummary = bugSummary;
     this.developersID = developersID;
     this.bugsid = bugsid;
 }

    public Project() {
    }

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

    public List<String> getDevelopersID() {
        return developersID;
    }

    public List<String> getBugsid() {
        return bugsid;
    }

    public void readProjectsInfo() {
        try (FileReader fr = new FileReader("src/Projects.txt");
             BufferedReader bf = new BufferedReader(fr)) {

            String line;

            while ((line = bf.readLine()) != null) {
//                System.out.println(line);
                String parts[] =line.split(",");
                String fileID = parts[0];
                if(fileID.equals(ID)){
                    name = parts[1];
                    description = parts[2];
                }
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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

    public List<Project> getProjects() {
        String path = FilesStorage.FilePath + "projects";
        List<Project> projects = new ArrayList<Project>();
        File directory = new File(path);
        File[] files = directory.listFiles();



        if (files != null) {
            for (File file : files) {

                projects.add(new Project(
                        FilesStorage.readline("projects/" + file.getName() , 0),
                        FilesStorage.readline("projects/" + file.getName() , 1),
                        FilesStorage.readline("project/" + file.getName() , 2),
                        FilesStorage.readline("project/" + file.getName() , 3),
                        List.of(FilesStorage.readline("projects/" + file.getName() , 4).split(",")),
                        List.of(FilesStorage.readline("projects/" + file.getName() , 5).split(","))
                ));
            }
        }

        return projects;

    }



}





















