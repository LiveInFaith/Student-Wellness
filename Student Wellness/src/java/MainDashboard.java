package view;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Wellness Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Appointments", new AppointmentPanel());
        tabbedPane.addTab("Counselors", new CounselorPanel());
        tabbedPane.addTab("Feedback", new FeedbackPanel());

        // Add Tabs to Frame
        add(tabbedPane, BorderLayout.CENTER);

        // Styling
        UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Optional: Set Look & Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(MainDashboard::new);
    }
}