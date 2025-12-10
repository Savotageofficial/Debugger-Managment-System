import java.io.*;

public class Main {
    public static void main(String[] args) {


        //TODO this will be the Home Page
        System.out.println(FilesStorage.readlines());

        FilesStorage.writeline(2 , "ahmed");
        System.out.println(FilesStorage.readline(2));


    }
}