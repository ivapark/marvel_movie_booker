package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import services.MovieTicketSystem;
import models.Movie;

public class AdminModifyMoviePanel extends JPanel {
    private MainFrame mainFrame;
    private MovieTicketSystem system;
    private JTable movieTable;

    public AdminModifyMoviePanel(MainFrame mainFrame, MovieTicketSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout());

        String[] columnNames = {"Title", "Showtime", "Available Seats", "Price"};
        movieTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton modifyButton = new JButton("Modify Selected Movie");
        add(modifyButton, BorderLayout.SOUTH);

        modifyButton.addActionListener(e -> {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow >= 0) {
                Movie movie = system.getMovies().get(selectedRow);
                String newTitle = JOptionPane.showInputDialog("New Title:", movie.getTitle());
                String newTime = JOptionPane.showInputDialog("New Showtime:", movie.getShowtime());
                int newSeats = Integer.parseInt(JOptionPane.showInputDialog("New Available Seats:", movie.getAvailableSeats()));
                double newPrice = Double.parseDouble(JOptionPane.showInputDialog("New Price:", movie.getPrice()));

                movie.setTitle(newTitle);
                movie.setShowtime(newTime);
                movie.setAvailableSeats(newSeats);
                movie.setPrice(newPrice);

                refreshMovies();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a movie first.");
            }
        });
    }

    public void refreshMovies() {
        DefaultTableModel model = (DefaultTableModel) movieTable.getModel();
        model.setRowCount(0);
        for (Movie movie : system.getMovies()) {
            model.addRow(new Object[]{
                movie.getTitle(),
                movie.getShowtime(),
                movie.getAvailableSeats(),
                movie.getPrice()
            });
        }
    }
}
