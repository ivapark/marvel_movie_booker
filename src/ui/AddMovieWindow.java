package ui;

import model.Movie;       // For movies
import model.User;        // If it uses users
import model.Booking;     // If needed

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // If using tables
import java.awt.*;
import java.util.List;


public class AddMovieWindow extends JFrame {
    public AddMovieWindow(List<Movie> movieList) {
        setTitle("Add New Movie");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel timeLabel = new JLabel("Showtime:");
        JTextField timeField = new JTextField();
        JLabel seatsLabel = new JLabel("Seats:");
        JTextField seatsField = new JTextField();

        JButton saveBtn = new JButton("Save");
        JButton resetBtn = new JButton("Reset");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> {
            String title = titleField.getText();
            String showtime = timeField.getText();
            try {
                int seats = Integer.parseInt(seatsField.getText());
                movieList.add(new Movie(title, showtime, seats));
                JOptionPane.showMessageDialog(this, "Movie added successfully!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for seats.");
            }
        });

        resetBtn.addActionListener(e -> {
            titleField.setText("");
            timeField.setText("");
            seatsField.setText("");
        });

        cancelBtn.addActionListener(e -> dispose());

        add(titleLabel);
        add(titleField);
        add(timeLabel);
        add(timeField);
        add(seatsLabel);
        add(seatsField);
        add(saveBtn);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(resetBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel);
    }
}
