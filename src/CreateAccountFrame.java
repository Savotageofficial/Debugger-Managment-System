import javax.swing.*;
import java.awt.*;

public class CreateAccountFrame extends JFrame {

    private Admin admin;

    public CreateAccountFrame(Admin admin) {
        this.admin = admin;

        setTitle("Create Account");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JLabel typeLabel = new JLabel("Account Type:");
        String[] types = { "Developer", "Tester", "Admin" };
        JComboBox<String> typeBox = new JComboBox<>(types);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton createBtn = new JButton("Create Account");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(typeLabel);
        panel.add(typeBox);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(createBtn);

        add(panel);

        createBtn.addActionListener(e -> {
            String type = (String) typeBox.getSelectedItem();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }


            if (emailExists(email)) {
                JOptionPane.showMessageDialog(this, "An account with this email already exists!");
                return;
            }

            switch (type) {
                case "Developer":
                    admin.creatdeveloper(name, email, password);
                    break;
                case "Tester":
                    admin.creattester(name, email, password);
                    break;
                case "Admin":
                    admin.creatadmin(name, email, password);
                    break;
            }

            JOptionPane.showMessageDialog(this, type + " account created successfully!");
            dispose();
        });
    }

    private boolean emailExists(String email) {

        Developer dev = new Developer();
        for (User user : dev.getUsers()) {
            if (user.Email.equalsIgnoreCase(email)) {
                return true;
            }
        }


        Tester tes = new Tester();
        for (User user : tes.getUsers()) {
            if (user.Email.equalsIgnoreCase(email)) {
                return true;
            }
        }


        Admin adm = new Admin();
        for (User user : adm.getUsers()) {
            if (user.Email.equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }
}
