import javax.swing.*;
import java.awt.*;

public class DeveloperProjectsFrame extends JFrame {

    private Developer dev;

    public DeveloperProjectsFrame(Developer dev) {
        this.dev = dev;

        setTitle("Assigned Projects");
        setSize(400, 300);
        setLocationRelativeTo(null);

        DefaultListModel<Project> model = new DefaultListModel<>();

        if (!(dev.getAssignedProjectsIDs().isEmpty())) {

            for (String id : dev.getAssignedProjectsIDs()) {
                if (id == null || id.equalsIgnoreCase("null") || id.trim().isEmpty()) {
                    continue;
                }
                Project p = FilesStorage.fetchProjectData(id);

                if (p != null) {
                    model.addElement(p);
                }
            }
        }

        JList<Project> projectList = new JList<>(model);
        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton openBtn = new JButton("Open Project");
        JButton backBtn = new JButton("Back");

        openBtn.addActionListener(e -> {
            Project selected = projectList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a project first");
                return;
            }
            new DeveloperBugsFrame(dev, selected).setVisible(true);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new DeveloperDashboardFrame(dev).setVisible(true);
            dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(openBtn);
        btnPanel.add(backBtn);

        add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
