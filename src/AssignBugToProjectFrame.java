import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AssignBugToProjectFrame extends JFrame {

    private JComboBox<String> projectBox;
    private JComboBox<String> bugBox;

    public AssignBugToProjectFrame() {

        setTitle("Assign Bug To Project");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JLabel projectLbl = new JLabel("Select Project:");
        JLabel bugLbl = new JLabel("Select Bug:");

        projectBox = new JComboBox<>();
        bugBox = new JComboBox<>();

        loadProjects();
        loadBugs();

        JButton assignBtn = new JButton("Assign Bug");

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(projectLbl);
        panel.add(projectBox);
        panel.add(bugLbl);
        panel.add(bugBox);
        panel.add(assignBtn);

        add(panel);

        assignBtn.addActionListener(e -> assignBug());
    }

    private void loadProjects() {
        File folder = new File(FilesStorage.FilePath + "projects");
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            projectBox.addItem(file.getName().replace(".txt", ""));
        }
    }

    private void loadBugs() {
        File folder = new File(FilesStorage.FilePath + "bugreports");
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            bugBox.addItem(file.getName().replace(".txt", ""));
        }
    }

    private void assignBug() {
        String projectId = (String) projectBox.getSelectedItem();
        String bugId = (String) bugBox.getSelectedItem();

        if (projectId == null || bugId == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select project and bug");
            return;
        }

        String bugsLine = FilesStorage.readline("projects/" + projectId + ".txt", 4);

        if (bugsLine == null || bugsLine.trim().isEmpty()) {
            bugsLine = bugId;
        } else {
            bugsLine += "," + bugId;
        }

        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lines.add(FilesStorage.readline("projects/" + projectId + ".txt", i));
        }

        lines.set(4, bugsLine);

        FilesStorage.writefile("projects", lines, projectId);

        JOptionPane.showMessageDialog(this,
                "Bug Assigned Successfully!");

        dispose();
    }
}

