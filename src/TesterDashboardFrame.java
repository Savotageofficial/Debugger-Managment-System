import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TesterDashboardFrame extends JFrame {

    private Tester tester;


    public TesterDashboardFrame(Tester tester) {
        this.tester = tester;

        setTitle("Tester Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Welcome Tester: " + tester.Name,
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

//        JButton manageUsersBtn = new JButton("Manage Users");
//        JButton manageProjectsBtn = new JButton("Manage Projects");

        Project pro = new Project();
        List<Project> projects = pro.getProjects();
        JPanel btnPanel = new JPanel();
        JPanel logoutpanel = new JPanel();

        for (Project project : projects){

            JButton btn = new JButton(project.getName());
            btnPanel.add(btn);

            btn.addActionListener(e ->{
                Project selectedproject = project;

                setVisible(false);

                new TesterProjectBugsFrame(tester ,selectedproject , TesterDashboardFrame.this).setVisible(true);
            });

        }
        JButton logoutBtn = new JButton("Logout");
        logoutpanel.add(logoutBtn);

//        btnPanel.add(manageUsersBtn);
//        btnPanel.add(manageProjectsBtn);


        add(welcomeLabel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);
        add(logoutpanel ,BorderLayout.SOUTH);

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}

