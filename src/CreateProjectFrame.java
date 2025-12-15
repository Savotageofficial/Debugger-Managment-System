import javax.swing.*;
import java.awt.*;

public class CreateProjectFrame extends JFrame {

    private Admin admin;

    public CreateProjectFrame(Admin admin) {
        this.admin = admin;

        setTitle("Create Project");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Project ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Project Name:");
        JTextField nameField = new JTextField();

        JLabel descLabel = new JLabel("Description:");
        JTextArea descArea = new JTextArea(4, 20);

        JButton createBtn = new JButton("Create Project");

        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descLabel);
        panel.add(new JScrollPane(descArea));
        panel.add(createBtn);

        add(panel);

        createBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String desc = descArea.getText();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all required fields");
                return;
            }

            admin.createProject(id, name, desc);
            JOptionPane.showMessageDialog(this,
                    "Project Created Successfully!");

            dispose();
        });
    }
}
