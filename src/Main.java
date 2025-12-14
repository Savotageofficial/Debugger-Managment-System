import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("------BUG MANAGMENT SYSTEM-------");
        // Project p=new Project("pro1","","");
        // p.addDeveloper("dev4");

        // User dev = new Admin();
        // List<User> devlist = dev.getUsers();
        // for (User developer : devlist) {
        // System.out.println(developer.getID());
        // }

        // TODO this will be the Home Page
        Scanner input = new Scanner(System.in);

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
            System.out.println("awaiting admin flowchart & completion of class\n");
            for (int i = 0; i <= 100; i++) {
                printProgressBar(i, 100);
                // Simulate work being done
                Thread.sleep(50);
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
                System.out.println("3\tExit");

                System.out.print("choice: ");
                int actionChoice = input.nextInt();
                input.nextLine(); // consume newline

                switch (actionChoice) {
                    case 1: // Add Bug


                        System.out.print("Enter Bug Title: ");
                        String Bugtitle = input.nextLine();

                        System.out.print("Enter Bug Description: ");
                        String BugDescription = input.nextLine();

                        System.out.print("Enter Bug Severity: ");
                        String BugSeverity = input.nextLine();

                        tes.reportBug(selectedproject ,Bugtitle, BugDescription, Severity.valueOf(BugSeverity.toUpperCase()), null , null);


                        System.out.println("Bug Reported successfully!");
                        break;

                    case 2: // Verify Bug Fix
                        System.out.println("\n------FIXED BUGS------");
                        BugReport bugInterface = new BugReport();

                        List<BugReport> allbugs = bugInterface.getBugReports();
                        List<BugReport> bugs = new ArrayList<>();

                        for (BugReport bug : allbugs){
                            if(bug.getStatus() == Status.FIXED) {
                                bugs.add(bug);
                            }
                        }

                        if (bugs.toArray().length == 0){
                            System.out.println("\n\nTHERE ARE NO FIXED BUGS");
                            break;
                        }

                        for(int i = 0 ; i < bugs.toArray().length ; i++){
                            System.out.println(i + "\t" + bugs.get(i).getTitle() + "\t" + bugs.get(i).getAssignedProject().getName());
                        }

                        System.out.print("choice: ");
                        int bugchoice = input.nextInt();
                        input.nextLine(); // consume newline

                        while(!(bugchoice >= 0 && bugchoice < bugs.toArray().length)){
                            System.out.println("Invalid Choice");
                            System.out.println("choice: ");
                            bugchoice = input.nextInt();
                            input.nextLine(); // consume newline
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
                            } else{
                                System.out.println("Bug Closed!");

                                tes.verifyBug(selectedBug , Status.CLOSED);
                                break;
                            }
                        }



                    case 3: // Exit

                        continueSelectingProjects = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }


            }


        }else if (u.UserType.equalsIgnoreCase("developer") || u instanceof Developer) {
            Developer dev = (Developer) u;

            List<String> projectsIDs = dev.getAssignedProjectsIDs();

            List<Project> projects = new ArrayList<Project>();

            for (String projectid : projectsIDs) {
                Project project = FilesStorage.fetchProjectData(projectid);
                projects.add(project);
            }

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
                input.nextLine(); // consume newline

                if (choice == projects.size()) {
                    System.out.println("Goodbye!");
                    continueSelectingProjects = false;
                    continue;
                }

                Project selectedproject = projects.get(choice);

                boolean continueSelectingBugs = true;

                while (continueSelectingBugs) {
                    List<BugReport> bugReports = new ArrayList<BugReport>();

                    for (String bugid : selectedproject.getBugsid()) {
                        BugReport bug = FilesStorage.fetchBugData(bugid);
                        bugReports.add(bug);
                    }

                    System.out.println("\n------Choose Bug to Alter------\n");

                    for (int i = 0; i < bugReports.toArray().length; i++) {
                        System.out.println(i + "\t" + bugReports.get(i).getTitle() + "\t"
                                + bugReports.get(i).getStatus() + "\t" + bugReports.get(i).getSeverity());
                    }
                    System.out.println(bugReports.size() + "\tBack to Projects");

                    System.out.print("choice: ");
                    choice = input.nextInt();
                    input.nextLine(); // consume newline

                    if (choice == bugReports.size()) {
                        continueSelectingBugs = false;
                        continue;
                    }

                    BugReport selectedbug = bugReports.get(choice);

                    boolean continueWorkingOnBug = true;

                    while (continueWorkingOnBug) {
                        // Refresh bug data
                        selectedbug = FilesStorage.fetchBugData(selectedbug.getID());

                        System.out.println("\n------" + selectedbug.getTitle() + "------");
                        System.out.println("Status: " + selectedbug.getStatus());
                        System.out.println("Severity: " + selectedbug.getSeverity());
                        System.out.println("Description: " + selectedbug.getDescription());

                        System.out.println("\n-Comments");

                        List<Comment> comments = selectedbug.getComments();
                        List<Attachment> attachments = selectedbug.getAttachments();

                        if (comments != null && !comments.isEmpty()) {
                            for (int i = 0; i < comments.toArray().length; i++) {
                                System.out.println(i + "\t" + comments.get(i).getText() + "\t\tAuthor:"
                                        + FilesStorage.fetchDeveloper(comments.get(i).getAuthor()).Name);
                            }
                        } else {
                            System.out.println("no Comments yet");
                        }

                        System.out.println("\n-Attachments");

                        if (attachments != null && !attachments.isEmpty()){
                            for (int i = 0; i < attachments.toArray().length; i++) {
                                System.out.println(i + "\t" + attachments.get(i).getFileName());
                            }
                        }else {
                            System.out.println("No attachments included\n");
                        }



                        // Action Menu
                        System.out.println("\n------Actions------");
                        System.out.println("1\tAdd Comment");
                        System.out.println("2\tChange Bug Status");
                        System.out.println("3\tSelect Another Bug");
                        System.out.println("4\tSelect Another Project");
                        System.out.println("5\tExit");

                        System.out.print("choice: ");
                        int actionChoice = input.nextInt();
                        input.nextLine(); // consume newline

                        switch (actionChoice) {
                            case 1: // Add Comment
                                System.out.print("Enter your comment: ");
                                String commentText = input.nextLine();

                                String commentId = FilesStorage.generateCommentId();
                                Comment newComment = new Comment(commentId, commentText, dev.getID());

                                // Save comment to file
                                List<String> commentData = new ArrayList<>();
                                commentData.add(commentId);
                                commentData.add(commentText);
                                commentData.add(java.time.LocalDateTime.now()
                                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                commentData.add(dev.getID());
                                FilesStorage.writefile("comments", commentData, commentId);

                                // Add comment to bug report
                                selectedbug.addComment(newComment);

                                System.out.println("Comment added successfully!");
                                break;

                            case 2: // Change Bug Status

                                if (selectedbug.getStatus() != Status.FIXED && selectedbug.getStatus() != Status.CLOSED) {
                                    dev.updateBugStatus(selectedbug);
                                    System.out.println("------Status Updated to (" + selectedbug.getStatus().name() + ")------");
                                }else{
                                    System.out.println("YOU CANNOT UPDATE FURTHER");
                                }

                                break;

                            case 3: // Select Another Bug
                                continueWorkingOnBug = false;
                                break;

                            case 4: // Select Another Project
                                continueWorkingOnBug = false;
                                continueSelectingBugs = false;
                                break;

                            case 5: // Exit
                                continueWorkingOnBug = false;
                                continueSelectingBugs = false;
                                continueSelectingProjects = false;
                                System.out.println("Goodbye!");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }
                }
            }
        }

    }

    private static void printProgressBar(int iteration, int total) {
        int barLength = 50; // Length of the progress bar in characters
        // Calculate the percentage completed
        double percent = ((double) iteration / total) * 100;
        // Calculate the number of filled characters
        int filledLength = (int) (barLength * iteration / total);

        // Build the bar string
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█"); // Use block character
            } else {
                bar.append("-");
            }
        }

        // Print the progress bar on the same line using carriage return (\r)
        String progress = String.format("\rProgress: |%s| %.1f%% (%d/%d)",
                bar.toString(), percent, iteration, total);
        System.out.print(progress);

        // Ensure the output is flushed immediately
        System.out.flush();
    } // can be removed safely
}