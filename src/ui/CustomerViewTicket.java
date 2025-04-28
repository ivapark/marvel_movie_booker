package ui;

import model.Movie;
import model.User;
import model.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerViewTicket extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private User currentUser;

    private JLabel titleLabel;
    private JLabel showtimeLabel;
    private JLabel seatsLabel;
    private JLabel priceLabel;

    private Booking booking;
    private Movie movie;

    // --- Static method to open ticket safely ---
    public static void openTicket(List<Movie> movieList, List<User> userList, User currentUser) {
        if (currentUser.getBookings().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No bookings found.");
            new CustomerMenu(movieList, userList).setVisible(true);
        } else {
            new CustomerViewTicket(movieList, userList, currentUser).setVisible(true);
        }
    }

    private CustomerViewTicket(List<Movie> movieList, List<User> userList, User currentUser) {
        this.movieList = movieList;
        this.userList = userList;
        this.currentUser = currentUser;

        setTitle("View My Ticket");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("My Ticket: Movie Title, Showtime", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);

        // Center Panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        infoPanel.add(new JLabel("Title:"));
        titleLabel = new JLabel();
        infoPanel.add(titleLabel);

        infoPanel.add(new JLabel("Showtime:"));
        showtimeLabel = new JLabel();
        infoPanel.add(showtimeLabel);

        infoPanel.add(new JLabel("Number of Seats:"));
        seatsLabel = new JLabel();
        infoPanel.add(seatsLabel);

        infoPanel.add(new JLabel("Price:"));
        priceLabel = new JLabel();
        infoPanel.add(priceLabel);

        add(infoPanel, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton cancelBtn = new JButton("Cancel Reservation");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(cancelBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        loadTicketInfo();

        // --- Button Behavior ---
        cancelBtn.addActionListener(e -> cancelBooking());
        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });
    }

    private void loadTicketInfo() {
        booking = currentUser.getBookings().get(0);
        movie = booking.getMovie();

        titleLabel.setText(movie.getTitle());
        showtimeLabel.setText(movie.getShowtime());
        seatsLabel.setText(String.valueOf(booking.getSeats()));
        priceLabel.setText("$" + (booking.getSeats() * movie.getTicketPrice()));
    }

    private void cancelBooking() {
        if (!currentUser.getBookings().isEmpty()) {
            Booking removedBooking = currentUser.getBookings().remove(0);
            removedBooking.getMovie().setSeatsAvailable(
                removedBooking.getMovie().getSeatsAvailable() + removedBooking.getSeats()
            );

            JOptionPane.showMessageDialog(this, "Reservation cancelled.");
        } else {
            JOptionPane.showMessageDialog(this, "No booking to cancel.");
        }
        this.dispose();
        new CustomerMenu(movieList, userList).setVisible(true);
    }
}
