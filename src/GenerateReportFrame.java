import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GenerateReportFrame extends JFrame {

    public GenerateReportFrame() {

        setTitle("System Report");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("System Report", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        int projectsCount = countFiles("projects");
        int bugsCount = countFiles("bugreports");
        int developersCount = countFiles("developer");
        int testersCount = countFiles("tester");

        JLabel projectsLbl = new JLabel("Total Projects: " + projectsCount);
        JLabel bugsLbl = new JLabel("Total Bugs: " + bugsCount);
        JLabel devLbl = new JLabel("Total Developers: " + developersCount);
        JLabel testerLbl = new JLabel("Total Testers: " + testersCount);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(title);
        panel.add(projectsLbl);
        panel.add(bugsLbl);
        panel.add(devLbl);
        panel.add(testerLbl);

        add(panel);
    }

    private int countFiles(String folderName) {
        File folder = new File(FilesStorage.FilePath + folderName);
        File[] files = folder.listFiles();
        return files == null ? 0 : files.length;
    }
}
