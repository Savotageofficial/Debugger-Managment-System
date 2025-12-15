import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperBugsFrame extends JFrame {

    private Developer dev;
    private Project project;

    public DeveloperBugsFrame(Developer dev, Project project) {
        this.dev = dev;
        this.project = project;

        setTitle("Bugs - " + project.getName());
        setSize(500, 300);
        setLocationRelativeTo(null);

        DefaultListModel<BugReport> model = new DefaultListModel<>();

        for (String bugId : project.getBugsid()) {
            BugReport bug = FilesStorage.fetchBugData(bugId);
            if (bug != null) model.addElement(bug);
        }

        JList<BugReport> bugList = new JList<>(model);

        JButton updateBtn = new JButton("Update Status");
        JButton backBtn = new JButton("Back");

        updateBtn.addActionListener(e -> {
            BugReport selected = bugList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a bug first");
                return;
            }
            new UpdateBugStatusFrame(dev, selected).setVisible(true);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new DeveloperProjectsFrame(dev).setVisible(true);
            dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(updateBtn);
        btnPanel.add(backBtn);

        add(new JScrollPane(bugList), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}

