import javax.swing.*;
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
        setLayout(new BorderLayout());

        JLabel welcomeLabel1 = new JLabel(
                "Selected Project: " + project.getName(),
                SwingConstants.CENTER
        );
        welcomeLabel1.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel bugnames = new JPanel();
        bugnames.setLayout(new BoxLayout(bugnames, BoxLayout.Y_AXIS));

        JPanel bugnamesWrapper = new JPanel(new BorderLayout());
        bugnamesWrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        bugnamesWrapper.add(bugnames, BorderLayout.NORTH);

        JLabel bugListTitle = new JLabel("FIXED BUG REPORTS : ");
        bugListTitle.setFont(new Font("Verdana", Font.BOLD, 18));
        bugListTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        List<BugReport> bugReports = new ArrayList<>();

        for (String bugid : project.getBugsid()) {
            BugReport bug = FilesStorage.fetchBugData(bugid);
            if (bug.getStatus() == Status.FIXED) {
                bugReports.add(bug);
            }
        }

        if (bugReports.isEmpty()) {
            bugListTitle.setText("THERE ARE NO FIXED BUGS FOR THIS PROJECT");
            bugListTitle.setFont(new Font("Verdana", Font.BOLD, 12));
            bugListTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        }

        bugnames.add(bugListTitle);
        bugnames.add(Box.createVerticalStrut(10));

        for (BugReport bug : bugReports) {
            JPanel bugcontainer = new JPanel(new BorderLayout());
            bugcontainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            bugcontainer.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel bugtitle = new JLabel(bug.getTitle());
            bugtitle.setFont(new Font("Verdana", Font.PLAIN, 15));

            JButton verifybtn = new JButton("verify bug fix");

            verifybtn.addActionListener(e ->{
                tester.verifyBug(bug , Status.CLOSED);
            });

            bugcontainer.add(bugtitle, BorderLayout.WEST);
            bugcontainer.add(verifybtn, BorderLayout.EAST);

            bugnames.add(bugcontainer);
            bugnames.add(Box.createVerticalStrut(5));
        }

        JButton logoutBtn = new JButton("Logout");
        JButton backbtn = new JButton("Back");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.add(logoutBtn);
        bottomPanel.add(backbtn);

        add(welcomeLabel1, BorderLayout.NORTH);
        add(bugnamesWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

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
