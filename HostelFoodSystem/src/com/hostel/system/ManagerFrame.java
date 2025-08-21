package com.hostel.system;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ManagerFrame extends JPanel {
    private JTextArea reportArea;

    public ManagerFrame() {
        setLayout(new BorderLayout());

        reportArea = new JTextArea();
        reportArea.setEditable(false);

        JButton generateReportButton = new JButton("Generate Daily Report");
        generateReportButton.addActionListener(e -> generateReport());

        add(new JScrollPane(reportArea), BorderLayout.CENTER);
        add(generateReportButton, BorderLayout.SOUTH);
    }

    private void generateReport() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = """
                SELECT meal_type, COUNT(*) AS total
                FROM meal_bookings
                WHERE DATE(booking_time) = CURDATE()
                GROUP BY meal_type
            """;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            StringBuilder report = new StringBuilder("Daily Meal Report:\n");
            while (rs.next()) {
                report.append(rs.getString("meal_type"))
                        .append(": ")
                        .append(rs.getInt("total"))
                        .append(" bookings\n");
            }

            reportArea.setText(report.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to generate report.");
        }
    }
}
