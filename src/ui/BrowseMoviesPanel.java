package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import services.MovieTicketSystem;
import models.Movie;

public class BrowseMoviesPanel extends JPanel {
    private MainFrame mainFrame;
    private JTable movieTable;
    private MovieTicketSystem system;

    public BrowseMoviesPanel(MainFrame mainFrame, MovieTicketSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout());

        String[] columnNames = {"Title", "Showtime", "Available Seats", "Price"};
        movieTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton bookButton = new JButton("Book a Ticket");
        add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "BookTicketPanel");
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
