package ui;

import model.Movie;       // For movies
import model.User;        // If it uses users
import model.Booking;     // If needed

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // If using tables
import java.awt.*;
import java.util.List;



public class ModifyMovieWindow extends JFrame {
    public ModifyMovieWindow(List<Movie> movieList) {
        setTitle("Modify Movie");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Movie to Modify:");
        JTextField titleField = new JTextField();

        JLabel newTimeLabel = new JLabel("New Showtime:");
        JTextField newTimeField = new JTextField();

        JLabel newSeatsLabel = new JLabel("New Seats:");
        JTextField newSeatsField = new JTextField();

        JButton modifyBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        modifyBtn.addActionListener(e -> {
            String title = titleField.getText();
            String newTime = newTimeField.getText();
            try {
                int newSeats = Integer.parseInt(newSeatsField.getText());
                boolean updated = false;

                for (Movie m : movieList) {
                    if (m.getTitle().equalsIgnoreCase(title)) {
                        m.setShowtime(newTime);
                        m.setSeatsAvailable(newSeats);
                        updated = true;
                        break;
                    }
                }

                if (updated) {
                    JOptionPane.showMessageDialog(this, "Movie updated!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Movie not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        add(titleLabel); add(titleField);
        add(newTimeLabel); add(newTimeField);
        add(newSeatsLabel); add(newSeatsField);
        add(modifyBtn); add(cancelBtn);
    }
}
