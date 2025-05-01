package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerBrowseMoviesWindow extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private JTable movieTable;
    private DefaultTableModel tableModel;

    public CustomerBrowseMoviesWindow(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Browse Movies");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Setup
        String[] columns = { "Title", "Showtime", "Available Seats" };
        tableModel = new DefaultTableModel(columns, 0);
        movieTable = new JTable(tableModel);
        populateTable();

        add(new JScrollPane(movieTable), BorderLayout.CENTER);

        // Bottom Panel for Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewAlphaBtn = new JButton("View Alphabetical Order");
        JButton viewByTimeBtn = new JButton("View by Time");
        JButton bookTicketBtn = new JButton("Book a Ticket");
        JButton exitBtn = new JButton("Exit");

        viewAlphaBtn.addActionListener(e -> {
            sortMoviesByTitle();
            populateTable();
        });

        viewByTimeBtn.addActionListener(e -> {
            sortMoviesByTime();
            populateTable();
        });

        bookTicketBtn.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a movie from the table.");
                return;
            }

            String selectedTitle = (String) tableModel.getValueAt(selectedRow, 0);
            Movie selectedMovie = movieList.stream()
                    .filter(m -> m.getTitle().equalsIgnoreCase(selectedTitle))
                    .findFirst()
                    .orElse(null);

            if (selectedMovie != null) {
                this.dispose();
                new CustomerBookTicketWindow(movieList, userList, selectedMovie).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selected movie not found.");
            }
        });

        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });

        bottomPanel.add(viewAlphaBtn);
        bottomPanel.add(viewByTimeBtn);
        bottomPanel.add(bookTicketBtn);
        bottomPanel.add(exitBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        for (Movie m : movieList) {
            tableModel.addRow(new Object[] { m.getTitle(), m.getShowtime(), m.getSeatsAvailable() });
        }
    }

    private void sortMoviesByTitle() {
        Collections.sort(movieList, Comparator.comparing(m -> m.getTitle().toLowerCase()));
    }

    private void sortMoviesByTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        Collections.sort(movieList, (m1, m2) -> {
            try {
                String t1 = normalizeTime(m1.getShowtime());
                String t2 = normalizeTime(m2.getShowtime());
                LocalTime time1 = LocalTime.parse(t1, formatter);
                LocalTime time2 = LocalTime.parse(t2, formatter);
                return time1.compareTo(time2);
            } catch (DateTimeParseException e) {
                return 0;
            }
        });
    }

    private String normalizeTime(String time) {
        time = time.trim().toUpperCase();
        if (!time.contains("AM") && !time.contains("PM")) {
            return time;
        }
        if (!time.contains(" ")) {
            time = time.replace("AM", " AM").replace("PM", " PM");
        }
        return time;
    }
}
