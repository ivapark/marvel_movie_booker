import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookTicketWindow extends JFrame {
    public BookTicketWindow(List<Movie> movieList, List<User> userList) {
        setTitle("Book a Ticket");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel userLabel = new JLabel("Your Email:");
        JTextField emailField = new JTextField();

        JLabel titleLabel = new JLabel("Movie Title:");
        JTextField titleField = new JTextField();

        JLabel seatsLabel = new JLabel("Seats:");
        JTextField seatsField = new JTextField();

        JButton bookBtn = new JButton("Book");
        JButton cancelBtn = new JButton("Cancel");

        bookBtn.addActionListener(e -> {
            String email = emailField.getText();
            String title = titleField.getText();
            int seats;

            try {
                seats = Integer.parseInt(seatsField.getText());
                Movie selected = null;

                for (Movie m : movieList) {
                    if (m.getTitle().equalsIgnoreCase(title)) {
                        selected = m;
                        break;
                    }
                }

                if (selected == null) {
                    JOptionPane.showMessageDialog(this, "Movie not found.");
                    return;
                }

                if (selected.getSeatsAvailable() < seats) {
                    JOptionPane.showMessageDialog(this, "Not enough seats.");
                    return;
                }

                User currentUser = userList.stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(email))
                        .findFirst()
                        .orElseGet(() -> {
                            User newUser = new User("Guest", email);
                            userList.add(newUser);
                            return newUser;
                        });

                selected.setSeatsAvailable(selected.getSeatsAvailable() - seats);
                currentUser.addBooking(new Booking(selected, seats));

                JOptionPane.showMessageDialog(this, "Ticket booked!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });

        cancelBtn.addActionListener(e -> dispose());

        add(userLabel); add(emailField);
        add(titleLabel); add(titleField);
        add(seatsLabel); add(seatsField);
        add(bookBtn); add(cancelBtn);
    }
}
