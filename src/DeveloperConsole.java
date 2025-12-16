import java.util.*;

public class DeveloperConsole {

    public static void start(Developer dev, Scanner input) {

        Project pro = new Project();
        List<Project> projects = pro.getProjects();

        System.out.println("\n--- Developer Console ---");

        for (int i = 0; i < projects.size(); i++) {
            System.out.println(i + " - " + projects.get(i).getName());
        }

        System.out.print("Choose project: ");
        int choice = input.nextInt();
        input.nextLine();

        Project selectedproject = projects.get(choice);

        List<BugReport> bugReports = new ArrayList<>();

        for (String bugid : selectedproject.getBugsid()) {
            BugReport bug = FilesStorage.fetchBugData(bugid);
            bugReports.add(bug);
        }

        System.out.println("\n------Choose Bug to Alter------");

        for (int i = 0; i < bugReports.size(); i++) {
            System.out.println(i + " - " + bugReports.get(i));
        }

        System.out.print("Choice: ");
        int bugChoice = input.nextInt();
        input.nextLine();

        BugReport selectedBug = bugReports.get(bugChoice);

        System.out.println("Selected Bug: " + selectedBug.getTitle());
    }
}

