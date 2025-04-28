package ui;

import model.Movie;       // For movies
import model.User;        // If it uses users
import model.Booking;     // If needed

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // If using tables
import java.awt.*;
import java.util.List;



public class BrowseMoviesWindow extends JFrame {
    public BrowseMoviesWindow(List<Movie> movieList) {
        setTitle("Browse Movies");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columns = {"Title", "Showtime", "Available Seats"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        for (Movie m : movieList) {
            model.addRow(new Object[]{m.getTitle(), m.getShowtime(), m.getSeatsAvailable()});
        }

        add(new JScrollPane(table));
    }
}
