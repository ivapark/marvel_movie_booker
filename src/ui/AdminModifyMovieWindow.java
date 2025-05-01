package ui;

import model.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminModifyMovieWindow extends JFrame {
    private List<Movie> movieList;
    private AdminBrowseMovieWindow manageMovieWindow;
    private String movieTitle;

    public AdminModifyMovieWindow(List<Movie> movieList, AdminBrowseMovieWindow manageMovieWindow, String movieTitle) {
        this.movieList = movieList;
        this.manageMovieWindow = manageMovieWindow;
        this.movieTitle = movieTitle;

        setTitle("Modify Movie");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Labels and text fields
        JLabel titleLabel = new JLabel("New Title:");
        JTextField titleField = new JTextField();
        JLabel timeLabel = new JLabel("New Showtime:");
        JTextField timeField = new JTextField();
        JLabel seatsLabel = new JLabel("New Available Seats:");
        JTextField seatsField = new JTextField();

        // Pre-fill current movie data
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(movieTitle)) {
                titleField.setText(m.getTitle());
                timeField.setText(m.getShowtime());
                seatsField.setText(String.valueOf(m.getSeatsAvailable()));
                break;
            }
        }

        // Buttons
        JButton updateBtn = new JButton("Update");
        JButton cancelBtn = new JButton("Cancel");

        updateBtn.addActionListener(e -> {
            String newTitle = titleField.getText().trim();
            String newShowtime = timeField.getText().trim();
            try {
                int newSeats = Integer.parseInt(seatsField.getText().trim());

                for (Movie m : movieList) {
                    if (m.getTitle().equalsIgnoreCase(movieTitle)) {
                        m.setTitle(newTitle.isEmpty() ? m.getTitle() : newTitle);
                        m.setShowtime(newShowtime.isEmpty() ? m.getShowtime() : newShowtime);
                        m.setSeatsAvailable(newSeats);
                        break;
                    }
                }

                manageMovieWindow.refreshTable();  // Refresh the table after updating
                JOptionPane.showMessageDialog(this, "Movie updated!");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number for seats. Please enter a valid integer.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.");
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        add(titleLabel);
        add(titleField);
        add(timeLabel);
        add(timeField);
        add(seatsLabel);
        add(seatsField);
        add(updateBtn);
        add(cancelBtn);
    }
}
