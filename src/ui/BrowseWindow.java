package ui;

import model.Movie;       // For movies
import model.User;        // If it uses users
import model.Booking;     // If needed

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // If using tables
import java.awt.*;
import java.util.List;


public class BrowseWindow extends JFrame {
    private DefaultTableModel tableModel;

    public BrowseWindow(List<Movie> movieList) {
        setTitle("Browse Movies");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Title", "Showtime", "Seats"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        refreshTable(movieList);

        JButton deleteBtn = new JButton("Delete");
        JButton closeBtn = new JButton("Close");

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String title = (String) tableModel.getValueAt(selectedRow, 0);
                movieList.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
                refreshTable(movieList);
            }
        });

        closeBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.add(deleteBtn);
        btnPanel.add(closeBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private void refreshTable(List<Movie> movieList) {
        tableModel.setRowCount(0);
        for (Movie m : movieList) {
            tableModel.addRow(new Object[]{m.getTitle(), m.getShowtime(), m.getSeatsAvailable()});
        }
    }
}
