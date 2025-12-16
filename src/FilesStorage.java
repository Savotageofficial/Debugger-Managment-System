import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FilesStorage {
    static String FilePath = "FileStorage/";

    public static void append(String input) {
        try (FileWriter writer = new FileWriter(FilePath, true)) {
            writer.append((input + "\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readline(String target, int index) {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(FilesStorage.FilePath + target))) {

            lines = reader.readAllLines();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(index);
    }

    static List<String> readlines(String targetpath) {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(FilesStorage.FilePath + targetpath))) {

            lines = reader.readAllLines();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void SaveUserData(User user) {


    }

    public static Project fetchProjectData(String projectid) {
        List<String> projectlist = FilesStorage.readlines("projects/" + projectid + ".txt");

        if (!(projectlist.get(5).equalsIgnoreCase("null"))) {

            return new Project(projectlist.get(0), projectlist.get(1), projectlist.get(2), projectlist.get(3),
                    new ArrayList<>(List.of(projectlist.get(4).split(","))),
                    new ArrayList<>(List.of(projectlist.get(5).split(","))));
        } else {
            return new Project(projectlist.get(0), projectlist.get(1), projectlist.get(2), projectlist.get(3),
                    new ArrayList<>(List.of(projectlist.get(4).split(","))), null);
        }
    }

    public static BugReport fetchBugData(String bugid) {
        List<String> buglist = FilesStorage.readlines("bugreports/" + bugid + ".txt");
        List<String> testerlist = FilesStorage
                .readlines("tester/" + FilesStorage.readline("bugreports/" + bugid + ".txt", 5) + ".txt");
        List<String> projectlist = FilesStorage
                .readlines("projects/" + FilesStorage.readline("bugreports/" + bugid + ".txt", 7) + ".txt");

        Tester tester = new Tester(testerlist.get(0), testerlist.get(1), testerlist.get(2), testerlist.get(3));

        Project project = new Project(projectlist.get(0), projectlist.get(1), projectlist.get(2), projectlist.get(3),
                new ArrayList<>(List.of(projectlist.get(4).split(","))),
                new ArrayList<>(List.of(projectlist.get(5).split(","))));

        String devid = FilesStorage.readline("bugreports/" + bugid + ".txt", 6);

        Developer developer = null;

        if (!(devid.equalsIgnoreCase("null"))) {
            List<String> devlist = FilesStorage.readlines("developer/" + devid + ".txt");
            developer = new Developer(projectlist.get(0), projectlist.get(1), projectlist.get(2), projectlist.get(3),
                    new ArrayList<>(List.of(projectlist.get(4).split(","))),
                    new ArrayList<>(List.of(projectlist.get(5).split(","))));
        }

        List<String> commentids = new ArrayList<>(List.of(buglist.get(10).split(",")));

        List<Comment> comments = new ArrayList<Comment>();

        if (commentids.get(0).equalsIgnoreCase("null")) {
            comments = null;
        } else {
            for (String commentid : commentids) {
                comments.add(fetchComment(commentid));
            }
        }

        List<String> attachmentsids = new ArrayList<>(List.of(buglist.get(11).split(",")));
        List<Attachment> attachments = new ArrayList<Attachment>();

        if (attachmentsids.get(0).equalsIgnoreCase("null")) {
            attachments = null;
        } else {
            for (String attachmentid : attachmentsids) {
                attachments.add(fetchAttachment(attachmentid));

            }
        }

        return new BugReport(buglist.get(0), buglist.get(1), buglist.get(2), Status.valueOf(buglist.get(3)),
                Severity.valueOf(buglist.get(4)), tester, developer, project, comments, attachments);
    }

    public static Developer fetchDeveloper(String devid) {

        List<String> devdata = FilesStorage.readlines("developer/" + devid + ".txt");

        Developer dev = new Developer(
                devdata.get(0),
                devdata.get(1),
                devdata.get(2),
                devdata.get(3),
                new ArrayList<>(List.of(devdata.get(5).split(","))),
                new ArrayList<>(List.of(devdata.get(6).split(",")))

        );

        return dev;

    }

    public static Tester fetchTester(String tesid) {

        List<String> tesdata = FilesStorage.readlines("tester/" + tesid + ".txt");

        Tester tes = new Tester(
                tesdata.get(0),
                tesdata.get(1),
                tesdata.get(2),
                tesdata.get(3)

        );

        return tes;

    }

    public static Admin fetchAdmin(String admid) {

        List<String> admdata = FilesStorage.readlines("admin/" + admid + ".txt");

        Admin adm = new Admin(
                admdata.get(0),
                admdata.get(1),
                admdata.get(2),
                admdata.get(3));

        return adm;

    }

    public static Comment fetchComment(String comid) {
        List<String> commentdetails = FilesStorage.readlines("comments/" + comid + ".txt");

        Comment comment = new Comment(commentdetails.get(0), commentdetails.get(1), commentdetails.get(2),
                commentdetails.get(3));

        return comment;
    }

    public static Attachment fetchAttachment(String attid) {
        List<String> attachmentdetails = FilesStorage.readlines("attachment/" + attid + ".txt");

        Attachment attachment = new Attachment(attachmentdetails.get(0), attachmentdetails.get(1),
                attachmentdetails.get(2), Long.parseLong(attachmentdetails.get(3)), attachmentdetails.get(4));

        return attachment;
    }

    public static void writeline(String target, int index, String text) {
        ArrayList<String> lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(FilePath + target))) {

            lines = new ArrayList<>(reader.readAllLines());
            lines.set(index, text);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(FilePath + target)) {
            for (int i = 0; i < lines.size(); i++) {
                writer.append(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deletefile(String target) {
        File file = new File(FilesStorage.FilePath + target);

        file.delete();

    }

    public static void writefile(String Collection, List<String> lines, String name) {

        try (FileWriter writer = new FileWriter(FilePath + Collection + "/" + name + ".txt")) {
            for (int i = 0; i < lines.size(); i++) {
                writer.append(lines.get(i) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String generateCommentId() {
        File directory = new File(FilePath + "comments");
        File[] files = directory.listFiles();
        int count = (files != null) ? files.length : 0;
        return "com" + (count + 1);
    }

    public static void createCommentFile(Comment comment) {
        List<String> lines = new ArrayList<String>();
        lines.add(comment.getId());
        lines.add(comment.getText());
        lines.add(comment.getDateCreated().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        lines.add(comment.getAuthor());
        FilesStorage.writefile("comments", lines, comment.getId());
    }

    public static void updateBugStatus(String bugid, String status) {
        writeline("bugreports/" + bugid + ".txt", 3, status);
    }

    public static void createBugFile(BugReport bug) {
        List<String> lines = new ArrayList<String>();

        lines.add(bug.getID());
        lines.add(bug.getTitle());
        lines.add(bug.getDescription());
        lines.add(bug.getStatus().name());
        lines.add(bug.getSeverity().name());
        lines.add(bug.getReporter().getID());
        if (bug.getAssignee() != null) {
            lines.add(bug.getAssignee().getID());
        } else {
            lines.add("null");
        }
        lines.add(bug.getAssignedProject().getID());
        lines.add(bug.getDateCreated().format(BugReport.format));
        lines.add(bug.getDateUpdated().format(BugReport.format));
        lines.add("null");
        lines.add("null");

        FilesStorage.writefile("bugreports", lines, bug.getID());
    }

}
