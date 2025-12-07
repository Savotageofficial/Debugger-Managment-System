import java.io.*;
import java.util.List;

public abstract class FilesStorage {
    static String FilePath = "FileStorage/test.txt";


    public static void append(String input){
        try(FileWriter writer = new FileWriter(FilePath, true)) {
            writer.append((input + "\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readline(int index){
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(FilesStorage.FilePath))){

            lines = reader.readAllLines();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(index);
    }
}
