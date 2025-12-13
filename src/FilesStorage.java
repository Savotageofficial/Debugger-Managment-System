import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FilesStorage {
    static String FilePath = "FileStorage/";


    public static void append(String input){
        try(FileWriter writer = new FileWriter(FilePath, true)) {
            writer.append((input + "\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readline(String target, int index){
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(FilesStorage.FilePath + target))){

            lines = reader.readAllLines();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(index);
    }
     static List<String> readlines(String targetpath){
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(FilesStorage.FilePath + targetpath))){

            lines = reader.readAllLines();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }


    public static void SaveUserData(User user){

    //todo(waiting for developer & admin & tester classes)
    }

    public static Project fetchProjectData(String projectid){
        List<String> projectlist = FilesStorage.readlines("projects/" + projectid + ".txt");

        return new Project(projectlist.get(0) , projectlist.get(1) , projectlist.get(2) , projectlist.get(3) , List.of(projectlist.get(4).split(",")), List.of(projectlist.get(5).split(",")));
    }

    public static BugReport fetchBugData(String bugid){
        List<String> buglist = FilesStorage.readlines("bugreports/" + bugid + ".txt");
        List<String> testerlist = FilesStorage.readlines("tester/" + FilesStorage.readline("bugreports/" + bugid + ".txt", 5) + ".txt");
        List<String> projectlist = FilesStorage.readlines("projects/" + FilesStorage.readline("bugreports/" + bugid + ".txt", 7) + ".txt");


        Tester tester = new Tester(testerlist.get(0) , testerlist.get(1) , testerlist.get(2) , testerlist.get(3));


        Project project = new Project(projectlist.get(0) , projectlist.get(1) , projectlist.get(2) , projectlist.get(3) , List.of(projectlist.get(4).split(",")) , List.of(projectlist.get(5).split(",")));

        String devid = FilesStorage.readline("bugreports/" + bugid + ".txt", 6);

        Developer developer = null;

        if (!(devid.equalsIgnoreCase("null"))){
            List<String> devlist = FilesStorage.readlines("developer/" + devid + ".txt");
            developer = new Developer(projectlist.get(0) , projectlist.get(1) , projectlist.get(2) , projectlist.get(3) , List.of(projectlist.get(4).split(",")) , List.of(projectlist.get(5).split(",")));
        }





        return new BugReport(buglist.get(0) , buglist.get(1) , buglist.get(2) , Status.valueOf(buglist.get(3)) , Severity.valueOf(buglist.get(4)), tester , developer , project);
    }



    public static void writeline(String target, int index , String text){
        ArrayList<String> lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath + target))){

            lines = new ArrayList<>(reader.readAllLines());
            lines.set(index , text);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileWriter writer = new FileWriter(FilePath + target)) {
            for(int i = 0 ; i < lines.size() ; i++){
                writer.append(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void writefile(String Collection , List<String> lines , String name){

        try(FileWriter writer = new FileWriter(FilePath + Collection + "/" + name)) {
            for(int i = 0 ; i < lines.size() ; i++){
                writer.append(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
