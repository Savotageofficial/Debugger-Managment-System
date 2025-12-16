import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignDeveloperToProjectFrame extends JFrame {

    private JComboBox<String> projectBox;
    private JComboBox<String> developerBox;
    private Map<String, String> projectNameToId = new HashMap<>();
    private Map<String, String> developerNameToId = new HashMap<>();

    public AssignDeveloperToProjectFrame() {

        setTitle("Assign Developer To Project");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JLabel projectLbl = new JLabel("Select Project:");
        JLabel developerLbl = new JLabel("Select Developer:");

        projectBox = new JComboBox<>();
        developerBox = new JComboBox<>();

        loadProjects();
        loadDevelopers();

        JButton assignBtn = new JButton("Assign Developer");

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(projectLbl);
        panel.add(projectBox);
        panel.add(developerLbl);
        panel.add(developerBox);
        panel.add(assignBtn);

        add(panel);

        assignBtn.addActionListener(e -> assignDeveloper());
    }

    private void loadProjects() {
        Project pro = new Project();
        List<Project> projects = pro.getProjects();

        if (projects == null)
            return;

        for (Project project : projects) {
            projectBox.addItem(project.getName());
            projectNameToId.put(project.getName(), project.getID());
        }
    }

    private void loadDevelopers() {
        Developer dev = new Developer();
        List<User> developers = dev.getUsers();

        if (developers == null)
            return;

        for (User developer : developers) {
            developerBox.addItem(developer.Name);
            developerNameToId.put(developer.Name, developer.getID());
        }
    }

    private void assignDeveloper() {
        String projectName = (String) projectBox.getSelectedItem();
        String developerName = (String) developerBox.getSelectedItem();

        if (projectName == null || developerName == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select project and developer");
            return;
        }

        String projectId = projectNameToId.get(projectName);
        String developerId = developerNameToId.get(developerName);

        if (projectId == null || developerId == null) {
            JOptionPane.showMessageDialog(this, "Error: Could not find selected items");
            return;
        }


        String developersLine = FilesStorage.readline("projects/" + projectId + ".txt", 4);

        if (developersLine == null || developersLine.trim().isEmpty() || developersLine.equalsIgnoreCase("null")) {
            developersLine = developerId;
            FilesStorage.writeline("projects/" + projectId + ".txt", 4, developersLine);
        } else {
            if (Arrays.asList(developersLine.split(",")).contains(developerId)) {
                JOptionPane.showMessageDialog(this, "Developer Already Assigned!");
                dispose();
                return;
            }
            developersLine += "," + developerId;
            FilesStorage.writeline("projects/" + projectId + ".txt", 4, developersLine);
        }


        String projectsLine = FilesStorage.readline("developer/" + developerId + ".txt", 5);

        if (projectsLine == null || projectsLine.trim().isEmpty() || projectsLine.equalsIgnoreCase("null")) {
            projectsLine = projectId;
        } else {
            if (!Arrays.asList(projectsLine.split(",")).contains(projectId)) {
                projectsLine += "," + projectId;
            }
        }
        FilesStorage.writeline("developer/" + developerId + ".txt", 5, projectsLine);

        JOptionPane.showMessageDialog(this, "Developer Assigned Successfully!");
        dispose();
    }
}
