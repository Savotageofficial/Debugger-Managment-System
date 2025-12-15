import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Bug Tracking System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        panel.add(new JLabel()); // empty
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(e -> login());
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        User user = Auth.Login(email, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid email or password");
        } else {
            dispose();

            if (user instanceof Admin) {
                new AdminDashboardFrame((Admin) user).setVisible(true);
            } else if (user instanceof Tester) {
                new TesterDashboardFrame((Tester) user).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Welcome " + user.Name + " (" + user.UserType + ")");
            }

        }
    }
}
