import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

        private Admin admin;

        public AdminDashboardFrame(Admin admin) {
                this.admin = admin;

                setTitle("Admin Dashboard");
                setSize(500, 400);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                JLabel welcomeLabel = new JLabel(
                                "Welcome Admin: " + admin.Name,
                                SwingConstants.CENTER);
                welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

                JButton createProjectBtn = new JButton("Create Project");
                JButton viewProjectsBtn = new JButton("View Projects");
                JButton createAccountBtn = new JButton("Create Account");
                JButton reportBtn = new JButton("Generate Report");
                JButton assignBugBtn = new JButton("Assign Developer To Project");
                JButton assignBug2Btn = new JButton("Assign Bug To Developer");
                JButton logoutBtn = new JButton("Logout");

                JPanel btnPanel = new JPanel(new GridLayout(6, 1, 10, 10));
                btnPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

                btnPanel.add(createProjectBtn);
                btnPanel.add(viewProjectsBtn);
                btnPanel.add(createAccountBtn);
                btnPanel.add(reportBtn);
                btnPanel.add(assignBugBtn);
                btnPanel.add(assignBug2Btn);
                btnPanel.add(logoutBtn);

                add(welcomeLabel, BorderLayout.NORTH);
                add(btnPanel, BorderLayout.CENTER);


                createProjectBtn.addActionListener(e -> new CreateProjectFrame(admin).setVisible(true));

                viewProjectsBtn.addActionListener(e -> new ViewProjectsFrame(admin).setVisible(true));

                createAccountBtn.addActionListener(e -> new CreateAccountFrame(admin).setVisible(true));

                reportBtn.addActionListener(e -> new GenerateReportFrame().setVisible(true));

                assignBugBtn.addActionListener(e -> new AssignDeveloperToProjectFrame().setVisible(true));
                assignBug2Btn.addActionListener(e -> new AssignBugToDeveloperFrame().setVisible(true));
                logoutBtn.addActionListener(e -> {
                        dispose();
                        new LoginFrame().setVisible(true);
                });
        }
}
