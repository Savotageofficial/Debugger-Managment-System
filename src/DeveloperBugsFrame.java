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

        if (project.getBugsid() != null) {

            for (String bugId : project.getBugsid()) {
                BugReport bug = FilesStorage.fetchBugData(bugId);
                if (bug != null)
                    model.addElement(bug);
            }
        }

        JList<BugReport> bugList = new JList<>(model);

        JButton updateBtn = new JButton("Update Status");
        JButton addCommentBtn = new JButton("Add Comment");
        JButton viewCommentsBtn = new JButton("View Comments");
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

        addCommentBtn.addActionListener(e -> {
            BugReport selected = bugList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a bug first");
                return;
            }
            String commentText = JOptionPane.showInputDialog(this, "Enter your comment:");
            if (commentText != null && !commentText.trim().isEmpty()) {
                String commentId = FilesStorage.generateCommentId();
                Comment comment = new Comment(commentId, commentText, dev.getID());
                FilesStorage.createCommentFile(comment);
                selected.addComment(comment);
                JOptionPane.showMessageDialog(this, "Comment added successfully!");
            }
        });

        viewCommentsBtn.addActionListener(e -> {
            BugReport selected = bugList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a bug first");
                return;
            }
            List<Comment> comments = selected.getComments();
            if (comments == null || comments.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No comments for this bug.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Comment c : comments) {
                String authorid = c.getAuthor();
                String authorname = FilesStorage.fetchDeveloper(authorid).Name;
                sb.append("Author: ").append(authorname).append("\n");
                sb.append("Date: ").append(c.getDateCreated()).append("\n");
                sb.append("Comment: ").append(c.getText()).append("\n\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Comments for: " + selected.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> {
            new DeveloperProjectsFrame(dev).setVisible(true);
            dispose();
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(updateBtn);
        btnPanel.add(addCommentBtn);
        btnPanel.add(viewCommentsBtn);
        btnPanel.add(backBtn);

        add(new JScrollPane(bugList), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
