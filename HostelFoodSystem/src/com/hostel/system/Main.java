package com.hostel.system;
public class Main {
    public static void main(String[] args) {
        // Launch the login frame as the entry point for the application
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}