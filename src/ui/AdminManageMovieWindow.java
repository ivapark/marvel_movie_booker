package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import ui.AdminViewUsersWindow; 

public class AdminManageMovieWindow extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Movie> movieList;
    private List<User> userList;

    public AdminManageMovieWindow(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Browse Movies");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"Title", "Showtime", "Available Seats"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTable();

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        JButton modifyBtn = new JButton("Modify Movie");
        JButton removeBtn = new JButton("Remove Movie");
        JButton addBtn = new JButton("Add New Movie");
        JButton viewUsersBtn = new JButton("View Users");
        JButton exitBtn = new JButton("Exit");

        modifyBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String selectedTitle = (String) tableModel.getValueAt(selectedRow, 0);  // get selected movie title
                new AdminModifyMovieWindow(movieList, this, selectedTitle).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a movie to modify.");
            }
        });
        

        removeBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String title = (String) tableModel.getValueAt(selectedRow, 0);
                movieList.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
                refreshTable();
            }
        });

        addBtn.addActionListener(e -> {
            new AdminAddMovieWindow(movieList, this).setVisible(true);
        });
        

        viewUsersBtn.addActionListener(e -> {
            new AdminViewUsersWindow(userList).setVisible(true);
        });

        exitBtn.addActionListener(e -> {
            this.dispose();
            new AdminMenu(movieList, userList).setVisible(true);  // Go back to Admin Menu
        });

        buttonPanel.add(modifyBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(viewUsersBtn);
        buttonPanel.add(exitBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Movie m : movieList) {
            tableModel.addRow(new Object[]{m.getTitle(), m.getShowtime(), m.getSeatsAvailable()});
        }
    }
}
