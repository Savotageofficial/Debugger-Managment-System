import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Select Mode:\n1. GUI\n2. Terminal");
        int mode = input.nextInt();
        input.nextLine();

        if (mode == 1) {
            new LoginFrame().setVisible(true);
            return;
        }

        System.out.println("------BUG MANAGMENT SYSTEM-------");

        System.out.print("enter your email: ");
        String email = input.nextLine();
        System.out.print("enter your password: ");
        String pass = input.nextLine();

        User u = Auth.Login(email, pass);

        while (u == null) {
            System.out.println("invalid login credentials");
            System.out.print("enter your email: ");
            email = input.nextLine();
            System.out.print("enter your password: ");
            pass = input.nextLine();
            u = Auth.Login(email, pass);
        }

        System.out.println("Welcome " + u.Name + "!");
        System.out.println("you are a/an " + u.UserType + "\n\n" + "--------------------");

        if (u.UserType.equalsIgnoreCase("admin")) {

            int choice = -1;

            while (choice != 0) {

                System.out.println("\n ADMIN MENU ");
                System.out.println("1- Create Project");
                System.out.println("2- View Projects");
                System.out.println("3- Generate Report");
                System.out.println("4- Assign Developer To Project");
                System.out.println("5- Create Account");
                System.out.println("6- Assign Bug to Developer");
                System.out.println("0- Logout");

                System.out.print("Choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("------Create Project------");
                        System.out.print("Enter Project Name: ");
                        String pName = input.nextLine();
                        System.out.print("Enter Description: ");
                        String pDesc = input.nextLine();

                        if (pName.isEmpty()) {
                            System.out.println("Name cannot be empty!");
                        } else {
                            String pId = Auth.generateID("project");
                            ((Admin) u).createProject(pId, pName, pDesc);
                            System.out.println("Project Created Successfully!");
                        }
                        break;
                    case 2:
                        System.out.println("------View Projects------");
                        File folder = new File(FilesStorage.FilePath + "projects");
                        File[] files = folder.listFiles();
                        if (files != null) {
                            List<String[]> projList = new ArrayList<>();
                            for (File file : files) {
                                String pid = file.getName().replace(".txt", "");
                                String name = FilesStorage.readline("projects/" + file.getName(), 1);
                                String desc = FilesStorage.readline("projects/" + file.getName(), 2);
                                projList.add(new String[] { pid, name, desc });
                                System.out.println(
                                        projList.size() + ". ID: " + pid + " | Name: " + name + " | Desc: " + desc);
                            }
                            System.out.println("0. Back\nSelect project to delete (or 0 to back): ");
                            int delChoice = input.nextInt();
                            input.nextLine();
                            if (delChoice > 0 && delChoice <= projList.size()) {
                                String[] target = projList.get(delChoice - 1);
                                System.out.print("Are you sure you want to delete " + target[1] + "? (y/n): ");
                                if (input.nextLine().equalsIgnoreCase("y")) {
                                    ((Admin) u).deletproject(target[0]);
                                    System.out.println("Project Deleted.");
                                }
                            }
                        } else {
                            System.out.println("No projects found.");
                        }
                        break;
                    case 3:
                        System.out.println("------Generate Report------");
                        System.out.println("Projects: " + (new File(FilesStorage.FilePath + "projects").list().length));
                        System.out.println("Bugs: " + (new File(FilesStorage.FilePath + "bugreports").list().length));
                        System.out.println(
                                "Developers: " + (new File(FilesStorage.FilePath + "developer").list().length));
                        System.out.println("Testers: " + (new File(FilesStorage.FilePath + "tester").list().length));
                        break;
                    case 4:
                        System.out.println("------Assign Developer To Project------");
                        List<Project> allProjs = new Project().getProjects();
                        if (allProjs.isEmpty()) {
                            System.out.println("No projects.");
                            break;
                        }
                        for (int i = 0; i < allProjs.size(); i++)
                            System.out.println((i + 1) + ". " + allProjs.get(i).getName());
                        System.out.print("Select Project: ");
                        int pIdx = input.nextInt() - 1;
                        input.nextLine();
                        if (pIdx < 0 || pIdx >= allProjs.size())
                            break;

                        List<User> allDevs = new Developer().getUsers();
                        if (allDevs.isEmpty()) {
                            System.out.println("No developers.");
                            break;
                        }
                        for (int i = 0; i < allDevs.size(); i++)
                            System.out.println((i + 1) + ". " + allDevs.get(i).Name);
                        System.out.print("Select Developer: ");
                        int dIdx = input.nextInt() - 1;
                        input.nextLine();
                        if (dIdx < 0 || dIdx >= allDevs.size())
                            break;

                        String projId = allProjs.get(pIdx).getID();
                        String devId = allDevs.get(dIdx).getID();

                        // Logic from AssignDeveloperToProjectFrame
                        String devsLine = FilesStorage.readline("projects/" + projId + ".txt", 4);
                        if (devsLine == null || devsLine.trim().isEmpty() || devsLine.equalsIgnoreCase("null")) {
                            FilesStorage.writeline("projects/" + projId + ".txt", 4, devId);
                        } else {
                            if (!Arrays.asList(devsLine.split(",")).contains(devId)) {
                                FilesStorage.writeline("projects/" + projId + ".txt", 4, devsLine + "," + devId);
                            }
                        }
                        String projsLine = FilesStorage.readline("developer/" + devId + ".txt", 5);
                        if (projsLine == null || projsLine.trim().isEmpty() || projsLine.equalsIgnoreCase("null")) {
                            FilesStorage.writeline("developer/" + devId + ".txt", 5, projId);
                        } else {
                            if (!Arrays.asList(projsLine.split(",")).contains(projId)) {
                                FilesStorage.writeline("developer/" + devId + ".txt", 5, projsLine + "," + projId);
                            }
                        }
                        System.out.println("Assigned successfully.");
                        break;
                    case 5:
                        System.out.println("------Create Account------");
                        System.out.println("1. Developer\n2. Tester\n3. Admin");
                        int typeC = input.nextInt();
                        input.nextLine();
                        System.out.print("Name: ");
                        String n = input.nextLine();
                        System.out.print("Email: ");
                        String e = input.nextLine();
                        System.out.print("Password: ");
                        String newPass = input.nextLine();

                        // Check email exists
                        boolean exists = false;
                        for (User us : new Developer().getUsers())
                            if (us.Email.equalsIgnoreCase(e))
                                exists = true;
                        for (User us : new Tester().getUsers())
                            if (us.Email.equalsIgnoreCase(e))
                                exists = true;
                        for (User us : new Admin().getUsers())
                            if (us.Email.equalsIgnoreCase(e))
                                exists = true;

                        if (exists) {
                            System.out.println("Email already exists!");
                        } else {
                            if (typeC == 1)
                                ((Admin) u).creatdeveloper(n, e, newPass, "developer");
                            else if (typeC == 2)
                                ((Admin) u).creattester(n, e, newPass, "tester");
                            else if (typeC == 3)
                                ((Admin) u).creatadmin(n, e, newPass);
                            System.out.println("Account created.");
                        }
                        break;
                    case 6:
                        System.out.println("------Assign Bug to Developer------");
                        List<User> developersList = new Developer().getUsers();
                        if (developersList == null || developersList.isEmpty()) {
                            System.out.println("No developers found.");
                            break;
                        }

                        for (int i = 0; i < developersList.size(); i++) {
                            System.out.println((i + 1) + ". " + developersList.get(i).Name);
                        }
                        System.out.print("Select Developer: ");
                        int devChoice = input.nextInt();
                        input.nextLine();

                        if (devChoice < 1 || devChoice > developersList.size()) {
                            System.out.println("Invalid selection.");
                            break;
                        }

                        User selectedDeveloper = developersList.get(devChoice - 1);
                        String selDevId = selectedDeveloper.getID();

                        // Load bugs for selected developer based on assigned projects
                        String devProjectsLine = FilesStorage.readline("developer/" + selDevId + ".txt", 5);
                        List<BugReport> availableBugs = new ArrayList<>();
                        List<String> availableBugsIds = new ArrayList<>();

                        if (devProjectsLine != null && !devProjectsLine.equalsIgnoreCase("null")
                                && !devProjectsLine.trim().isEmpty()) {
                            String[] projIds = devProjectsLine.split(",");
                            for (String pid : projIds) {
                                if (pid == null || pid.trim().isEmpty())
                                    continue;
                                Project p = FilesStorage.fetchProjectData(pid);
                                if (p != null && p.getBugsid() != null) {
                                    for (String bid : p.getBugsid()) {
                                        if (bid != null && !bid.trim().isEmpty() && !availableBugsIds.contains(bid)) {
                                            BugReport b = FilesStorage.fetchBugData(bid);
                                            if (b != null) {
                                                availableBugs.add(b);
                                                availableBugsIds.add(bid);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (availableBugs.isEmpty()) {
                            System.out.println("No bugs available for this developer (check assigned projects).");
                        } else {
                            for (int i = 0; i < availableBugs.size(); i++) {
                                System.out.println((i + 1) + ". " + availableBugs.get(i).getTitle() + " ("
                                        + availableBugs.get(i).getAssignedProject().getID() + ")");
                            }
                            System.out.print("Select Bug to Assign: ");
                            int bugChoice = input.nextInt();
                            input.nextLine();

                            if (bugChoice < 1 || bugChoice > availableBugs.size()) {
                                System.out.println("Invalid selection.");
                            } else {
                                BugReport bugToAssign = availableBugs.get(bugChoice - 1);
                                String bugId = bugToAssign.getID();

                                String existingBugs = FilesStorage.readline("developer/" + selDevId + ".txt", 6);
                                boolean alreadyAssigned = false;
                                if (existingBugs != null && !existingBugs.equalsIgnoreCase("null")
                                        && !existingBugs.trim().isEmpty()) {
                                    if (Arrays.asList(existingBugs.split(",")).contains(bugId)) {
                                        alreadyAssigned = true;
                                    }
                                }

                                if (alreadyAssigned) {
                                    System.out.println("Bug Already Assigned to this Developer!");
                                } else {
                                    if (existingBugs == null || existingBugs.trim().isEmpty()
                                            || existingBugs.equalsIgnoreCase("null")) {
                                        FilesStorage.writeline("developer/" + selDevId + ".txt", 6, bugId);
                                    } else {
                                        FilesStorage.writeline("developer/" + selDevId + ".txt", 6,
                                                existingBugs + "," + bugId);
                                    }
                                    FilesStorage.writeline("bugreports/" + bugId + ".txt", 6, selDevId);
                                    System.out.println("Bug Assigned Successfully!");
                                }
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Logout");
                        break;
                }
            }

            System.out.println("exiting!");
            Thread.sleep(10);

        } else if (u.UserType.equalsIgnoreCase("tester") || u instanceof Tester) {

            Tester tes = (Tester) u;

            Project pro = new Project();

            List<Project> projects = pro.getProjects();

            boolean continueSelectingProjects = true;

            while (continueSelectingProjects) {
                System.out.println("\n------Choose Preferred Project------\n");

                for (int i = 0; i < projects.toArray().length; i++) {
                    Project project = projects.get(i);
                    System.out.println(i + "\t" + project.getName());
                }
                System.out.println(projects.size() + "\tExit");

                System.out.print("choice: ");
                int choice = input.nextInt();
                input.nextLine();
                if (choice == projects.size()) {
                    System.out.println("Goodbye!");
                    continueSelectingProjects = false;
                    continue;
                }

                Project selectedproject = projects.get(choice);

                List<BugReport> bugReports = new ArrayList<BugReport>();

                for (String bugid : selectedproject.getBugsid()) {
                    BugReport bug = FilesStorage.fetchBugData(bugid);
                    bugReports.add(bug);
                }

                System.out.println("\n------Current Bug Reports------\n");

                for (int i = 0; i < bugReports.toArray().length; i++) {
                    System.out.println(i + "\t" + bugReports.get(i).getTitle() + "\t"
                            + bugReports.get(i).getStatus() + "\t" + bugReports.get(i).getSeverity());
                }

                System.out.println("\n------Actions------");
                System.out.println("1\tReport Bug");
                System.out.println("2\tVerify Bug Fix");
                System.out.println("3\tView Comments");
                System.out.println("4\tExit");

                System.out.print("choice: ");
                int actionChoice = input.nextInt();
                input.nextLine();

                switch (actionChoice) {
                    case 1:

                        System.out.print("Enter Bug Title: ");
                        String Bugtitle = input.nextLine();

                        System.out.print("Enter Bug Description: ");
                        String BugDescription = input.nextLine();

                        System.out.print("Enter Bug Severity: ");
                        String BugSeverity = input.nextLine();

                        tes.reportBug(selectedproject, Bugtitle, BugDescription,
                                Severity.valueOf(BugSeverity.toUpperCase()), null, null);

                        System.out.println("Bug Reported successfully!");
                        break;

                    case 2:
                        System.out.println("\n------FIXED BUGS------");
                        BugReport bugInterface = new BugReport();

                        List<BugReport> allbugs = bugInterface.getBugReports();
                        List<BugReport> bugs = new ArrayList<>();

                        for (BugReport bug : allbugs) {
                            if (bug.getStatus() == Status.FIXED) {
                                bugs.add(bug);
                            }
                        }

                        if (bugs.toArray().length == 0) {
                            System.out.println("\n\nTHERE ARE NO FIXED BUGS");
                            break;
                        }

                        for (int i = 0; i < bugs.toArray().length; i++) {
                            System.out.println(i + "\t" + bugs.get(i).getTitle() + "\t"
                                    + bugs.get(i).getAssignedProject().getName());
                        }

                        System.out.print("choice: ");
                        int bugchoice = input.nextInt();
                        input.nextLine();

                        while (!(bugchoice >= 0 && bugchoice < bugs.toArray().length)) {
                            System.out.println("Invalid Choice");
                            System.out.println("choice: ");
                            bugchoice = input.nextInt();
                            input.nextLine();
                        }
                        BugReport selectedBug = bugs.get(bugchoice);

                        System.out.println("------" + selectedBug.getTitle() + "------\n");

                        System.out.println("Status : " + selectedBug.getStatus().name());

                        int newchoice = 0;

                        while (newchoice != 2 && newchoice != 1) {
                            System.out.print("verify fix? 1:Yes , 2:No : ");

                            newchoice = input.nextInt();
                            input.nextLine();
                            if (newchoice == 2) {
                                break;
                            } else {
                                System.out.println("Bug Closed!");

                                tes.verifyBug(selectedBug, Status.CLOSED);
                                break;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("------View Comments------");
                        if (bugReports.isEmpty()) {
                            System.out.println("No bugs in this project.");
                        } else {
                            for (int i = 0; i < bugReports.size(); i++) {
                                System.out.println(i + ". " + bugReports.get(i).getTitle());
                            }
                            System.out.print("Select Bug: ");
                            int bIdx = input.nextInt();
                            input.nextLine();
                            if (bIdx >= 0 && bIdx < bugReports.size()) {
                                BugReport b = bugReports.get(bIdx);
                                List<Comment> comments = b.getComments();
                                if (comments == null || comments.isEmpty())
                                    System.out.println("No comments.");
                                else {
                                    for (Comment c : comments) {
                                        System.out.println("Author: " + c.getAuthor());
                                        System.out.println("Date: " + c.getDateCreated());
                                        System.out.println("Comment: " + c.getText());
                                        System.out.println("-------------------");
                                    }
                                }
                            }
                        }
                        break;

                    case 4:
                        continueSelectingProjects = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        } else if (u.UserType.equalsIgnoreCase("developer") || u instanceof Developer) {
            Developer dev = (Developer) u;
            while (true) {
                List<String> projectsIDs = dev.getAssignedProjectsIDs();
                List<Project> projects = new ArrayList<>();
                if (projectsIDs != null) {
                    for (String projectid : projectsIDs) {
                        if (projectid != null && !projectid.equals("null") && !projectid.trim().isEmpty()) {
                            Project project = FilesStorage.fetchProjectData(projectid);
                            if (project != null)
                                projects.add(project);
                        }
                    }
                }

                System.out.println("\n------Assigned Projects------");
                if (projects.isEmpty())
                    System.out.println("(No projects assigned)");
                for (int i = 0; i < projects.size(); i++) {
                    System.out.println((i + 1) + ". " + projects.get(i).getName());
                }
                System.out.println("0. Exit");
                System.out.print("Choice: ");
                int pChoice = input.nextInt();
                input.nextLine();

                if (pChoice == 0)
                    break;
                if (pChoice < 1 || pChoice > projects.size()) {
                    System.out.println("Invalid.");
                    continue;
                }

                Project selectedProject = projects.get(pChoice - 1);

                while (true) {
                    System.out.println("\n------Bugs in " + selectedProject.getName() + "------");
                    List<BugReport> bugReports = new ArrayList<>();
                    if (selectedProject.getBugsid() != null) {
                        for (String bugid : selectedProject.getBugsid()) {
                            if (bugid != null && !bugid.trim().isEmpty()) {
                                BugReport bug = FilesStorage.fetchBugData(bugid);
                                if (bug != null)
                                    bugReports.add(bug);
                            }
                        }
                    }

                    if (bugReports.isEmpty())
                        System.out.println("(No bugs)");
                    for (int i = 0; i < bugReports.size(); i++) {
                        System.out.println((i + 1) + ". " + bugReports.get(i).getTitle() + " ["
                                + bugReports.get(i).getStatus() + "]");
                    }
                    System.out.println("0. Back");
                    System.out.print("Select Bug: ");
                    int bChoice = input.nextInt();
                    input.nextLine();

                    if (bChoice == 0)
                        break;
                    if (bChoice < 1 || bChoice > bugReports.size()) {
                        System.out.println("Invalid.");
                        continue;
                    }

                    BugReport selectedBug = bugReports.get(bChoice - 1);

                    while (true) {
                        System.out.println("\n--- Bug: " + selectedBug.getTitle() + " ---");
                        System.out.println("Status: " + selectedBug.getStatus());
                        System.out.println("1. Update Status");
                        System.out.println("2. Add Comment");
                        System.out.println("3. View Comments");
                        System.out.println("0. Back");
                        System.out.print("Action: ");
                        int aChoice = input.nextInt();
                        input.nextLine();

                        if (aChoice == 0)
                            break;

                        if (aChoice == 1) {
                            dev.updateBugStatus(selectedBug);
                            System.out.println("Status updated to: " + selectedBug.getStatus());
                        } else if (aChoice == 2) {
                            System.out.print("Enter comment: ");
                            String cText = input.nextLine();
                            if (!cText.trim().isEmpty()) {
                                String cid = FilesStorage.generateCommentId();
                                Comment com = new Comment(cid, cText, dev.getID());
                                FilesStorage.createCommentFile(com);
                                selectedBug.addComment(com);
                                System.out.println("Comment added.");
                            }
                        } else if (aChoice == 3) {
                            List<Comment> comments = selectedBug.getComments();
                            if (comments == null || comments.isEmpty())
                                System.out.println("No comments.");
                            else {
                                for (Comment c : comments) {
                                    System.out.println("Author: " + c.getAuthor() + " | Date: " + c.getDateCreated());
                                    System.out.println(c.getText());
                                    System.out.println("---");
                                }
                            }
                        }
                    }
                }
            }
        }
        input.close();
    }

    private static void printProgressBar(int iteration, int total) {
        int barLength = 50;

        double percent = ((double) iteration / total) * 100;

        int filledLength = (int) (barLength * iteration / total);

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("-");
            }
        }

        String progress = String.format("\rProgress: |%s| %.1f%% (%d/%d)",
                bar.toString(), percent, iteration, total);
        System.out.print(progress);

        System.out.flush();
    }
}