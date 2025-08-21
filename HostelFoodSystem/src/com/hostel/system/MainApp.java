package com.hostel.system;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainApp extends JFrame {
    private JPanel currentPanel;

    public MainApp(String role, String username) {
        setTitle("Hostel Food System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        switchPanel(role, username);
    }

    private void switchPanel(String role, String username) {
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (role.toLowerCase()) {
            case "student":
                currentPanel = new StudentFrame(username);
                break;
            case "manager":
                currentPanel = new ManagerFrame();
                break;
            case "warden":
                currentPanel = new WardenFrame();
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown role.");
                return;
        }

        add(currentPanel);
        revalidate();
        repaint();
    }
}