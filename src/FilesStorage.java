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



    public static void writeline(int index , String text){
        ArrayList<String> lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath))){

            lines = new ArrayList<>(reader.readAllLines());
            lines.set(index , text);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileWriter writer = new FileWriter(FilePath)) {
            for(int i = 0 ; i < lines.size() ; i++){
                writer.append(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
