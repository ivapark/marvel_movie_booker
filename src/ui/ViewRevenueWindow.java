package ui;

import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewRevenueWindow extends JFrame {
    private List<Movie> movieList;

    public ViewRevenueWindow(List<Movie> movieList) {
        this.movieList = movieList;

        setTitle("View Revenue");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Setup
        String[] columns = {"Title", "Number of Tickets", "Revenue"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        refreshTable(tableModel);

        // Exit Button
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> {
            this.dispose();
            // You could choose to go back to AdminMenu if you want
            // new AdminMenu(movieList, userList).setVisible(true);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(exitBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void refreshTable(DefaultTableModel tableModel) {
        for (Movie movie : movieList) {
            int totalSeats = getInitialSeats(movie.getTitle()); // assume each movie had fixed initial seats
            int soldSeats = totalSeats - movie.getSeatsAvailable();
            int revenue = soldSeats * getTicketPrice(movie.getTitle());
            tableModel.addRow(new Object[]{movie.getTitle(), soldSeats, "$" + revenue});
        }
    }

    // Assume starting seats were always 50, you can adjust this logic if needed
    private int getInitialSeats(String title) {
        switch (title) {
            case "Iron Man":
            case "Avengers: Endgame":
                return 60;
            case "Black Panther":
                return 40;
            case "Doctor Strange":
                return 30;
            case "Spider-Man: Homecoming":
                return 35;
            case "Thor: Ragnarok":
                return 25;
            case "Captain Marvel":
                return 20;
            case "Avengers: Infinity War":
                return 45;
            case "Guardians of the Galaxy":
                return 32;
            case "Ant-Man and the Wasp":
                return 28;
            default:
                return 50; // default
        }
    }

    // Assume ticket price fixed, or vary if you want
    private int getTicketPrice(String title) {
        return 10; // e.g., $10 per ticket
    }
}
