package ui;

import model.User;
import model.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUsersWindow extends JFrame {
    private List<User> userList;
    private DefaultTableModel tableModel;

    public ViewUsersWindow(List<User> userList) {
        this.userList = userList;

        setTitle("Registered Users");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table Setup
        String[] columns = {"Name", "Email", "Booked Movies"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        refreshTable();

        // Buttons
        JButton removeBtn = new JButton("Remove User");
        JButton exitBtn = new JButton("Exit");

        removeBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String email = (String) tableModel.getValueAt(selectedRow, 1);
                userList.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
                refreshTable();
            }
        });

        exitBtn.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(removeBtn);
        buttonPanel.add(exitBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (User user : userList) {
            StringBuilder bookings = new StringBuilder();
            for (Booking booking : user.getBookings()) {
                bookings.append(booking.getMovie().getTitle()).append(", ");
            }
            if (bookings.length() > 0) {
                bookings.setLength(bookings.length() - 2); // Remove last comma
            }
            tableModel.addRow(new Object[]{user.getName(), user.getEmail(), bookings.toString()});
        }
    }
}
