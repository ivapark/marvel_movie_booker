package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class BrowseMoviesWindow extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public BrowseMoviesWindow(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Browse Movies");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Setup
        String[] columns = {"Title", "Showtime", "Available Seats"};
        tableModel = new DefaultTableModel(columns, 0);
        movieTable = new JTable(tableModel);

        populateTable();

        add(new JScrollPane(movieTable), BorderLayout.CENTER);

        // Bottom Panel for Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewChronoBtn = new JButton("View Chronological Order");
        JButton viewByTimeBtn = new JButton("View by Time");
        JButton bookTicketBtn = new JButton("Book a Ticket");
        JButton exitBtn = new JButton("Exit");

        viewChronoBtn.addActionListener(e -> {
            sortMoviesByTitle();
            populateTable();
        });

        viewByTimeBtn.addActionListener(e -> {
            sortMoviesByTime();
            populateTable();
        });

        bookTicketBtn.addActionListener(e -> {
            this.dispose();
            new BookTicketWindow(movieList, userList).setVisible(true);
        });

        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });

        bottomPanel.add(viewChronoBtn);
        bottomPanel.add(viewByTimeBtn);
        bottomPanel.add(bookTicketBtn);
        bottomPanel.add(exitBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    
    private void populateTable() {
        tableModel.setRowCount(0); // clear existing rows
        for (Movie m : movieList) {
            tableModel.addRow(new Object[]{m.getTitle(), m.getShowtime(), m.getSeatsAvailable()});
        }
    }

    private void sortMoviesByTitle() {
        Collections.sort(movieList, Comparator.comparing(Movie::getTitle));
    }

    private void sortMoviesByTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
    
        Collections.sort(movieList, (m1, m2) -> {
            try {
                LocalTime time1 = LocalTime.parse(m1.getShowtime(), formatter);
                LocalTime time2 = LocalTime.parse(m2.getShowtime(), formatter);
                return time1.compareTo(time2);
            } catch (DateTimeParseException e) {
                return 0; // if parsing fails, treat as equal
            }
        });
    }
}
