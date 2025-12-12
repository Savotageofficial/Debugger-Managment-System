import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String ID;
    private String name;
    private String description;
    private List<String> developersID=new ArrayList<String>();
//    private ArrayList<Bug> bugs=new ArrayList<>;

 public Project(String ID, String name, String description) {
     this.name = name;
     this.description = description;
     this.ID = ID;
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


}





















