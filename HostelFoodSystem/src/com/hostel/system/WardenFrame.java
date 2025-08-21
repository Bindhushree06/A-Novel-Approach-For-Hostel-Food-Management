
package com.hostel.system;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class WardenFrame extends JPanel {
    private JTextField studentIdField;
    private JPasswordField passwordField;

    public WardenFrame() {
        setLayout(new GridLayout(3, 2));

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());

        add(studentIdLabel);
        add(studentIdField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(addButton);
    }

    private void addStudent() {
        String studentId = studentIdField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (studentId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID and Password cannot be empty.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users (user_id, password, role) VALUES (?, ?, 'student')";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, password);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add student.");
        }
    }
}
