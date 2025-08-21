package com.hostel.system;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentFrame extends JPanel {
    private String studentId;

    public StudentFrame(String studentId) {
        this.studentId = studentId;
        setLayout(new GridLayout(3, 1));

        JButton breakfastButton = new JButton("Book Breakfast");
        breakfastButton.addActionListener(e -> bookMeal("breakfast"));

        JButton lunchButton = new JButton("Book Lunch");
        lunchButton.addActionListener(e -> bookMeal("lunch"));

        JButton dinnerButton = new JButton("Book Dinner");
        dinnerButton.addActionListener(e -> bookMeal("dinner"));

        add(breakfastButton);
        add(lunchButton);
        add(dinnerButton);
    }

    private void bookMeal(String mealType) {
        try (Connection conn = DBConnection.getConnection()) {
            LocalTime now = LocalTime.now();
            if ((mealType.equals("breakfast") && now.isAfter(LocalTime.of(21, 0)) && now.isBefore(LocalTime.of(22, 0)))
                || (mealType.equals("lunch") && now.isAfter(LocalTime.of(6, 0)) && now.isBefore(LocalTime.of(9, 0)))
                || (mealType.equals("dinner") && now.isAfter(LocalTime.of(12, 0)) && now.isBefore(LocalTime.of(15, 0)))) {
                String query = "INSERT INTO meal_bookings (student_id, meal_type) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, studentId);
                stmt.setString(2, mealType);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, mealType + " booked successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Booking for " + mealType + " is not allowed at this time.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to book meal.");
        }
    }
}