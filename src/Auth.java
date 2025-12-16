import java.util.ArrayList;
import java.util.List;

public abstract class Auth {


    public static User Login(String email, String password) {

        User u = null;
        Admin adm = new Admin();
        Developer dev = new Developer();
        Tester tes = new Tester();
        List<User> devs = dev.getUsers();
        List<User> adms = adm.getUsers();
        List<User> tess = tes.getUsers();

        if (u == null) {
            for (User user : devs) {
                if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)) {
                    u = user;
                    break;
                }
            }
        }

        if (u == null){
            for(User user : adms){
                if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                    u = user;
                    break;
                }
            }
        }
        if (u == null){
            for(User user : tess){
                if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                    u = user;
                    break;
                }
            }
        }




        return u;

    }

    public static String generateID(String Type){
        if (Type.equalsIgnoreCase("developer")){
            User dev = new Developer();
            List<User> userlist = dev.getUsers();
            List<String> userIds = new ArrayList<>();
            if (userlist.isEmpty()){
                return "dev1";
            }
            for (User user : userlist){
                userIds.add(user.getID());
            }
            sortByLastNumber(userIds);


            return "dev" + (extractNumber(userIds.getLast()) + 1);
        }
        if (Type.equalsIgnoreCase("admin")){
            User adm = new Admin();
            List<User> userlist = adm.getUsers();
            List<String> userIds = new ArrayList<>();
            if (userlist.isEmpty()){
                return "adm1";
            }
            for (User user : userlist){
                userIds.add(user.getID());
            }
            sortByLastNumber(userIds);


            return "adm" + (extractNumber(userIds.getLast()) + 1);
        }
        if (Type.equalsIgnoreCase("tester")){
            User tes = new Tester();
            List<User> userlist = tes.getUsers();
            List<String> userIds = new ArrayList<>();
            if (userlist.isEmpty()){
                return "tes1";
            }
            for (User user : userlist){
                userIds.add(user.getID());
            }
            sortByLastNumber(userIds);


            return "tes" + (extractNumber(userIds.getLast()) + 1);
        }
        if (Type.equalsIgnoreCase("bug")){
            BugReport bug = new BugReport();
            List<BugReport> bugReportList = bug.getBugReports();
            List<String> bugIds = new ArrayList<>();
            if (bugReportList.isEmpty()){
                return "bug1";
            }
            for (BugReport bugReport : bugReportList){
                bugIds.add(bugReport.getID());
            }
            sortByLastNumber(bugIds);


            return "bug" + (extractNumber(bugIds.getLast()) + 1);
        }
        if (Type.equalsIgnoreCase("project")){
            Project project = new Project();
            List<Project> projectList = project.getProjects();
            List<String> projectIds = new ArrayList<>();
            if (projectList.isEmpty()){
                return "pro1";
            }
            for (Project pro : projectList){
                projectIds.add(pro.getID());
            }
            sortByLastNumber(projectIds);


            return "pro" + (extractNumber(projectIds.getLast()) + 1);
        }
        return null;
    }


    private static void sortByLastNumber(List<String> list) {
        list.sort((a, b) -> {
            int numA = extractNumber(a);
            int numB = extractNumber(b);
            return Integer.compare(numA, numB);
        });
    }

    private static int extractNumber(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isDigit(s.charAt(i))) {
            i--;
        }
        return Integer.parseInt(s.substring(i + 1));
    }

}
