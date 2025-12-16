import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TesterReportBugsFrame extends JFrame {

    private Tester tester;
    private Project project;
    private TesterProjectBugsFrame previousFrame;

    public TesterReportBugsFrame(Tester tester , Project project , TesterProjectBugsFrame caller) {
        this.tester = tester;
        this.project = project;
        this.previousFrame = caller;

        int horizontalMargin = 20;
        int verticalMargin = 10;

        Border emptyBorder = BorderFactory.createEmptyBorder(
                verticalMargin, horizontalMargin, verticalMargin, horizontalMargin
        );

        setTitle("Tester Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel welcomeLabel1 = new JLabel(
                "Selected Project: " + project.getName(),
                SwingConstants.CENTER
        );
        welcomeLabel1.setFont(new Font("Arial", Font.BOLD, 18));




        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel bugnametitle = new JLabel("Bug Name:");
        JTextField bugname = new JTextField();
        form.add(bugnametitle);
        form.add(bugname);

        JLabel bugdescriptiontitle = new JLabel("Bug description:");
        JTextField bugdescription = new JTextField();
        form.add(bugdescriptiontitle);
        form.add(bugdescription);

        JLabel bugseveritytitle = new JLabel("Bug severity:");
        JTextField bugseverity = new JTextField();
        form.add(bugseveritytitle);
        form.add(bugseverity);


        JButton submitbtn = new JButton("Submit");
        form.add(submitbtn);

        JLabel Addedbuglabel = new JLabel("Added bug report successfully");
        form.add(Addedbuglabel);
        Addedbuglabel.setVisible(false);




        JPanel formWrapper = new JPanel(new BorderLayout());
        formWrapper.setBorder(emptyBorder);
        formWrapper.add(form, BorderLayout.CENTER);



        JButton logoutBtn = new JButton("Logout");
        JButton backbtn = new JButton("Back");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // 🔹 ADDED
        bottomPanel.add(logoutBtn);
        bottomPanel.add(backbtn);




        add(welcomeLabel1, BorderLayout.NORTH);
        add(formWrapper, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);



        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        backbtn.addActionListener(e -> {
            previousFrame.setVisible(true);
            dispose();
        });

        submitbtn.addActionListener(e ->{
            String name = bugname.getText();
            String description = bugdescription.getText();
            String severity = bugseverity.getText();
            tester.reportBug(project , name , description ,Severity.valueOf(severity.toUpperCase()) ,null , null);
            Addedbuglabel.setVisible(true);

            form.revalidate();
            form.repaint();
        });
    }
}
