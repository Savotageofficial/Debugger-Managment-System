import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;

public class ViewProjectsFrame extends JFrame {

    private Admin admin;
    private JTable table;
    private DefaultTableModel model;

    public ViewProjectsFrame(Admin admin) {
        this.admin = admin;

        setTitle("View Projects");
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Project ID");
        model.addColumn("Name");
        model.addColumn("Description");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteBtn = new JButton("Delete Project");
        JButton viewCommentsBtn = new JButton("View Bug Comments");

        JPanel btnPanel = new JPanel();
        btnPanel.add(deleteBtn);
        btnPanel.add(viewCommentsBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadProjects();

        deleteBtn.addActionListener(e -> deleteSelectedProject());

        viewCommentsBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project first");
                return;
            }
            String projectId = model.getValueAt(row, 0).toString();
            Project project = FilesStorage.fetchProjectData(projectId);
            List<String> bugIds = project.getBugsid();
            if (bugIds == null || bugIds.isEmpty() || bugIds.get(0).equalsIgnoreCase("null")) {
                JOptionPane.showMessageDialog(this, "No bugs in this project.");
                return;
            }
            String[] bugTitles = new String[bugIds.size()];
            BugReport[] bugs = new BugReport[bugIds.size()];
            for (int i = 0; i < bugIds.size(); i++) {
                bugs[i] = FilesStorage.fetchBugData(bugIds.get(i));
                bugTitles[i] = bugs[i] != null ? bugs[i].getTitle() : bugIds.get(i);
            }
            String selectedTitle = (String) JOptionPane.showInputDialog(this, "Select a bug:", "View Comments",
                    JOptionPane.QUESTION_MESSAGE, null, bugTitles, bugTitles[0]);
            if (selectedTitle != null) {
                BugReport selectedBug = null;
                for (BugReport bug : bugs) {
                    if (bug != null && bug.getTitle().equals(selectedTitle)) {
                        selectedBug = bug;
                        break;
                    }
                }
                if (selectedBug != null) {
                    List<Comment> comments = selectedBug.getComments();
                    if (comments == null || comments.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No comments for this bug.");
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (Comment c : comments) {
                        sb.append("Author: ").append(c.getAuthor()).append("\n");
                        sb.append("Date: ").append(c.getDateCreated()).append("\n");
                        sb.append("Comment: ").append(c.getText()).append("\n\n");
                    }
                    JTextArea textArea = new JTextArea(sb.toString());
                    textArea.setEditable(false);
                    JScrollPane sp = new JScrollPane(textArea);
                    sp.setPreferredSize(new Dimension(400, 300));
                    JOptionPane.showMessageDialog(this, sp, "Comments for: " + selectedBug.getTitle(),
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void loadProjects() {
        model.setRowCount(0);

        File folder = new File(FilesStorage.FilePath + "projects");
        File[] files = folder.listFiles();

        if (files == null)
            return;

        for (File file : files) {
            String projectId = file.getName().replace(".txt", "");
            String name = FilesStorage.readline("projects/" + file.getName(), 1);
            String desc = FilesStorage.readline("projects/" + file.getName(), 2);

            model.addRow(new Object[] { projectId, name, desc });
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
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            admin.deletproject(projectId);
            model.removeRow(row);
        }
    }
}
