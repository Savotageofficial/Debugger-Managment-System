import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TesterProjectFixedBugsFrame extends JFrame {

    private Tester tester;
    private Project project;
    private TesterProjectBugsFrame previousFrame;

    public TesterProjectFixedBugsFrame(Tester tester , Project project , TesterProjectBugsFrame caller) {
        this.tester = tester;
        this.project = project;
        this.previousFrame = caller;

        setTitle("Tester Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel1 = new JLabel(
                "Selected Project: " + project.getName(),
                SwingConstants.CENTER
        );
        welcomeLabel1.setFont(new Font("Arial", Font.BOLD, 18));



//        JButton manageUsersBtn = new JButton("Manage Users");
//        JButton manageProjectsBtn = new JButton("Manage Projects");

        JPanel btnPanel = new JPanel();

        JPanel bugnames = new JPanel();
        bugnames.setLayout(new BoxLayout(bugnames , BoxLayout.Y_AXIS));

        JPanel logoutpanel = new JPanel();
        logoutpanel.setLayout(new BoxLayout(logoutpanel , BoxLayout.X_AXIS));

        JPanel allbtnPanel = new JPanel();


        allbtnPanel.setLayout(new BoxLayout(allbtnPanel , BoxLayout.Y_AXIS));
        List<BugReport> bugReports = new ArrayList<BugReport>();




//        Border emptyBorder = BorderFactory.createEmptyBorder(30, 20, 30, 20);

        JLabel bugListTitle = new JLabel("FIXED BUG REPORTS : ");
        bugListTitle.setFont(new Font("Verdana", Font.BOLD, 18));

        for (String bugid : project.getBugsid()) {
            BugReport bug = FilesStorage.fetchBugData(bugid);
            if(bug.getStatus() == Status.FIXED) {
                bugReports.add(bug);
            }
        }
        if (bugReports.toArray().length == 0){
            bugListTitle.setText("THERE ARE NO FIXED BUGS FOR THIS PROJECT");
            bugListTitle.setFont(new Font("Verdana", Font.BOLD, 12));
            bugListTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        }



        bugnames.add(bugListTitle);
        bugnames.add(Box.createVerticalStrut(10));
        for (BugReport bug : bugReports){

            JLabel bugtitle = new JLabel(bug.getTitle());
            bugtitle.setFont(new Font("Verdana", Font.PLAIN, 15));
            bugnames.add(bugtitle);

        }
        JButton logoutBtn = new JButton("Logout");
        JButton backbtn = new JButton("Back");

        logoutpanel.add(logoutBtn);
        logoutpanel.add(backbtn);

//        btnPanel.add(manageUsersBtn);
//        btnPanel.add(manageProjectsBtn);



        add(welcomeLabel1, BorderLayout.NORTH);
        add(bugnames , BorderLayout.WEST);
        allbtnPanel.add(btnPanel);
        allbtnPanel.add(logoutpanel , BorderLayout.SOUTH);
        add(allbtnPanel);
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        backbtn.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });
    }
}

