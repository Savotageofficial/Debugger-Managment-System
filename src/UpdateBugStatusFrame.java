import javax.swing.*;
import java.awt.*;

public class UpdateBugStatusFrame extends JFrame {

    public UpdateBugStatusFrame(Developer dev, BugReport bug) {

        setTitle("Update Bug Status");
        setSize(350, 200);
        setLocationRelativeTo(null);

        JLabel info = new JLabel(
                "Current Status: " + bug.getStatus(),
                SwingConstants.CENTER
        );

        JButton updateBtn = new JButton("Update Status");

        updateBtn.addActionListener(e -> {
            dev.updateBugStatus(bug);
            JOptionPane.showMessageDialog(this,
                    "New Status: " + bug.getStatus());
            dispose();
        });

        setLayout(new GridLayout(2, 1, 10, 10));
        add(info);
        add(updateBtn);
    }
}
