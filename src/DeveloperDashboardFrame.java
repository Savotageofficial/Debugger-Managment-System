import javax.swing.*;
import java.awt.*;

public class DeveloperDashboardFrame extends JFrame {

    private Developer dev;

    public DeveloperDashboardFrame(Developer dev) {
        this.dev = dev;

        setTitle("Developer Dashboard");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel(
                "Welcome Developer: " + dev.Name,
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton projectsBtn = new JButton("My Projects");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(welcomeLabel);
        panel.add(projectsBtn);
        panel.add(logoutBtn);

        add(panel);

        projectsBtn.addActionListener(e ->
                new DeveloperProjectsFrame(dev).setVisible(true)
        );

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}

