import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    private Admin admin;

    public AdminDashboardFrame(Admin admin) {
        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Welcome Admin: " + admin.Name,
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton manageUsersBtn = new JButton("Manage Users");
        JButton manageProjectsBtn = new JButton("Manage Projects");
        JButton logoutBtn = new JButton("Logout");

        JPanel btnPanel = new JPanel();
        btnPanel.add(manageUsersBtn);
        btnPanel.add(manageProjectsBtn);
        btnPanel.add(logoutBtn);

        add(welcomeLabel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}

