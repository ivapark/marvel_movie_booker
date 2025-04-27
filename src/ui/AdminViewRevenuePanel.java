package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import services.MovieTicketSystem;
import models.Movie;
import models.Booking;

public class AdminViewRevenuePanel extends JPanel {
    private MainFrame mainFrame;
    private MovieTicketSystem system;
    private JTable revenueTable;

    public AdminViewRevenuePanel(MainFrame mainFrame, MovieTicketSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new BorderLayout());

        String[] columnNames = {"Movie Title", "Total Revenue"};
        revenueTable = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane scrollPane = new JScrollPane(revenueTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminPanel");
        });
    }

    public void refreshRevenue() {
        DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();
        model.setRowCount(0);

        for (Movie movie : system.getMovies()) {
            double totalRevenue = 0;
            for (Booking booking : system.getBookings()) {
                if (booking.getMovie().getTitle().equals(movie.getTitle())) {
                    totalRevenue += booking.getAmountPaid();
                }
            }
            model.addRow(new Object[]{movie.getTitle(), totalRevenue});
        }
    }
}
