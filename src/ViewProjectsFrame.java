import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class ViewProjectsFrame extends JFrame {

    private Admin admin;
    private JTable table;
    private DefaultTableModel model;

    public ViewProjectsFrame(Admin admin) {
        this.admin = admin;

        setTitle("View Projects");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Table columns
        model = new DefaultTableModel();
        model.addColumn("Project ID");
        model.addColumn("Name");
        model.addColumn("Description");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteBtn = new JButton("Delete Project");

        add(scrollPane, BorderLayout.CENTER);
        add(deleteBtn, BorderLayout.SOUTH);

        loadProjects();

        deleteBtn.addActionListener(e -> deleteSelectedProject());
    }

    private void loadProjects() {
        model.setRowCount(0);

        File folder = new File(FilesStorage.FilePath + "projects");
        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            String projectId = file.getName().replace(".txt", "");
            String name = FilesStorage.readline("projects/" + file.getName(), 1);
            String desc = FilesStorage.readline("projects/" + file.getName(), 2);

            model.addRow(new Object[]{projectId, name, desc});
        }
    }

    private void deleteSelectedProject() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a project first");
            return;
        }

        String projectId = model.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this project?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            admin.deletproject(projectId);
            model.removeRow(row);
        }
    }
}
