import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewUsersWindow extends JFrame {
    public ViewUsersWindow(List<User> userList) {
        setTitle("Registered Users");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columns = {"Name", "Email", "Booked Movies"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        for (User user : userList) {
            StringBuilder bookings = new StringBuilder();
            for (Booking booking : user.getBookings()) {
                bookings.append(booking.getMovie().getTitle()).append(", ");
            }
            model.addRow(new Object[]{
                user.getName(),
                user.getEmail(),
                bookings.length() > 0 ? bookings.substring(0, bookings.length() - 2) : "None"
            });
        }

        add(new JScrollPane(table));
    }
}
