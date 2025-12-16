import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AssignBugToDeveloperFrame extends JFrame {

    private JComboBox<String> developerBox;
    private JComboBox<String> bugBox;
    private Map<String, String> developerNameToId = new HashMap<>();
    private Map<String, String> bugTitleToId = new HashMap<>();
    private Map<String, Developer> developerMap = new HashMap<>();

    public AssignBugToDeveloperFrame() {
        setTitle("Assign Bug To Developer");
        setSize(450, 300);
        setLocationRelativeTo(null);

        JLabel developerLbl = new JLabel("Select Developer:");
        JLabel bugLbl = new JLabel("Select Bug:");

        developerBox = new JComboBox<>();
        bugBox = new JComboBox<>();

        loadDevelopers();

        JButton assignBtn = new JButton("Assign Bug");

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(developerLbl);
        panel.add(developerBox);
        panel.add(bugLbl);
        panel.add(bugBox);
        panel.add(assignBtn);

        add(panel);


        developerBox.addActionListener(e -> loadBugsForSelectedDeveloper());

        assignBtn.addActionListener(e -> assignBug());
    }

    private void loadDevelopers() {
        Developer dev = new Developer();
        List<User> developers = dev.getUsers();

        if (developers == null)
            return;

        for (User developer : developers) {
            developerBox.addItem(developer.Name);
            developerNameToId.put(developer.Name, developer.getID());
            developerMap.put(developer.Name, (Developer) developer);
        }

        if (developerBox.getItemCount() > 0) {
            loadBugsForSelectedDeveloper();
        }
    }

    private void loadBugsForSelectedDeveloper() {
        bugBox.removeAllItems();
        bugTitleToId.clear();

        String developerName = (String) developerBox.getSelectedItem();
        if (developerName == null)
            return;

        String developerId = developerNameToId.get(developerName);
        if (developerId == null)
            return;

        String projectsLine = FilesStorage.readline("developer/" + developerId + ".txt", 5);
        if (projectsLine == null || projectsLine.equalsIgnoreCase("null") || projectsLine.trim().isEmpty()) {
            bugBox.addItem("No projects assigned");
            return;
        }

        List<String> projectIds = Arrays.asList(projectsLine.split(","));
        Set<String> addedBugs = new HashSet<>();

        for (String projectId : projectIds) {
            if (projectId == null || projectId.equalsIgnoreCase("null") || projectId.trim().isEmpty()) {
                continue;
            }

            try {
                Project project = FilesStorage.fetchProjectData(projectId);
                if (project == null)
                    continue;

                List<String> bugIds = project.getBugsid();
                if (bugIds == null)
                    continue;

                for (String bugId : bugIds) {
                    if (bugId == null || bugId.equalsIgnoreCase("null") || bugId.trim().isEmpty()) {
                        continue;
                    }
                    if (addedBugs.contains(bugId))
                        continue;

                    BugReport bug = FilesStorage.fetchBugData(bugId);
                    if (bug != null) {
                        String displayName = bug.getTitle() + " (" + projectId + ")";
                        bugBox.addItem(displayName);
                        bugTitleToId.put(displayName, bugId);
                        addedBugs.add(bugId);
                    }
                }
            } catch (Exception ex) {

            }
        }

        if (bugBox.getItemCount() == 0) {
            bugBox.addItem("No bugs available");
        }
    }

    private void assignBug() {
        String developerName = (String) developerBox.getSelectedItem();
        String bugDisplay = (String) bugBox.getSelectedItem();

        if (developerName == null || bugDisplay == null) {
            JOptionPane.showMessageDialog(this, "Please select developer and bug");
            return;
        }

        if (bugDisplay.equals("No projects assigned") || bugDisplay.equals("No bugs available")) {
            JOptionPane.showMessageDialog(this, "No valid bug to assign");
            return;
        }

        String developerId = developerNameToId.get(developerName);
        String bugId = bugTitleToId.get(bugDisplay);

        if (developerId == null || bugId == null) {
            JOptionPane.showMessageDialog(this, "Error: Could not find selected items");
            return;
        }


        String bugsLine = FilesStorage.readline("developer/" + developerId + ".txt", 6);

        if (bugsLine == null || bugsLine.trim().isEmpty() || bugsLine.equalsIgnoreCase("null")) {
            bugsLine = bugId;
        } else {
            if (Arrays.asList(bugsLine.split(",")).contains(bugId)) {
                JOptionPane.showMessageDialog(this, "Bug Already Assigned to this Developer!");
                return;
            }
            bugsLine += "," + bugId;
        }
        FilesStorage.writeline("developer/" + developerId + ".txt", 6, bugsLine);


        FilesStorage.writeline("bugreports/" + bugId + ".txt", 6, developerId);

        JOptionPane.showMessageDialog(this, "Bug Assigned Successfully!");
        dispose();
    }
}
