import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TesterProjectBugsFrame extends JFrame {

    private Tester tester;
    private Project project;
    private TesterDashboardFrame previousFrame;

    public TesterProjectBugsFrame(Tester tester , Project project , TesterDashboardFrame caller) {
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



        JPanel btnPanel = new JPanel();

        JPanel bugnames = new JPanel();
        bugnames.setLayout(new BoxLayout(bugnames , BoxLayout.Y_AXIS));

        JPanel logoutpanel = new JPanel();

        JPanel allbtnPanel = new JPanel();

        JPanel choices = new JPanel();
        choices.setLayout(new BoxLayout(choices , BoxLayout.Y_AXIS));

        allbtnPanel.setLayout(new BoxLayout(allbtnPanel , BoxLayout.Y_AXIS));
        List<BugReport> bugReports = new ArrayList<BugReport>();


        int horizontalMargin = 20;
        int verticalMargin = 10;

        Border emptyBorder = BorderFactory.createEmptyBorder(verticalMargin, horizontalMargin, verticalMargin, horizontalMargin);

        for (String bugid : project.getBugsid()) {
            BugReport bug = FilesStorage.fetchBugData(bugid);
            bugReports.add(bug);
        }

        JLabel bugListTitle = new JLabel("BUG REPORTS : ");
        bugListTitle.setFont(new Font("Verdana", Font.BOLD, 18));

        bugnames.add(bugListTitle);
        bugnames.add(Box.createVerticalStrut(10));

        for (BugReport bug : bugReports){
            JPanel bugcontainer = new JPanel(new BorderLayout(20, 0));
            bugcontainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            bugcontainer.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel bugtitle = new JLabel(bug.getTitle());
            bugtitle.setFont(new Font("Verdana", Font.PLAIN, 15));

            JLabel Status = new JLabel(bug.getStatus().name());

            bugcontainer.add(bugtitle, BorderLayout.WEST);

            bugcontainer.add(Box.createHorizontalStrut(20), BorderLayout.CENTER);

            bugcontainer.add(Status, BorderLayout.EAST);

            bugnames.add(bugcontainer);
            bugnames.add(Box.createVerticalStrut(5));

        }

        JButton logoutBtn = new JButton("Logout");
        JButton backbtn = new JButton("Back");

        JButton ReportBugbtn = new JButton("Report Bug");
        JButton VerifyBugFixbtn = new JButton("Verify Bug Fix");

        logoutpanel.add(logoutBtn);
        logoutpanel.add(backbtn);



        choices.setBorder(emptyBorder);
        choices.add(ReportBugbtn);
        choices.add(Box.createVerticalStrut(10));
        choices.add(VerifyBugFixbtn);

        setLayout(new BorderLayout());
        add(welcomeLabel1, BorderLayout.NORTH);
        add(bugnames , BorderLayout.WEST);



        add(logoutpanel, BorderLayout.SOUTH);

        add(choices , BorderLayout.EAST);

        bugnames.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        choices.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        backbtn.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });
        ReportBugbtn.addActionListener(e -> {
            setVisible(false);
            new TesterReportBugsFrame(tester , project , TesterProjectBugsFrame.this).setVisible(true);


        });
        VerifyBugFixbtn.addActionListener(e ->{
            setVisible(false);
            new TesterProjectFixedBugsFrame(tester , project , TesterProjectBugsFrame.this).setVisible(true);
        });
    }
}