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

    private JComboBox<String> bookingSelector;
    private JLabel titleLabel;
    private JLabel showtimeLabel;
    private JLabel seatsLabel;
    private JLabel priceLabel;

    private Booking currentBooking;

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

        setTitle("View My Tickets");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("My Tickets", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);

        // Booking selection dropdown
        bookingSelector = new JComboBox<>();
        for (Booking b : currentUser.getBookings()) {
            bookingSelector.addItem(b.getMovie().getTitle() + " @ " + b.getMovie().getShowtime());
        }

        bookingSelector.addActionListener(e -> {
            int index = bookingSelector.getSelectedIndex();
            if (index >= 0) {
                currentBooking = currentUser.getBookings().get(index);
                updateTicketInfo();
            }
        });

        add(bookingSelector, BorderLayout.NORTH);

        // Ticket Info Display
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
        JPanel buttonPanel = new JPanel();

        JButton cancelBtn = new JButton("Cancel Reservation");
        JButton exitBtn = new JButton("Exit");

        cancelBtn.addActionListener(e -> cancelBooking());
        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });

        buttonPanel.add(cancelBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load initial selection
        bookingSelector.setSelectedIndex(0);
    }

    private void updateTicketInfo() {
        Movie movie = currentBooking.getMovie();
        titleLabel.setText(movie.getTitle());
        showtimeLabel.setText(movie.getShowtime());
        seatsLabel.setText(String.valueOf(currentBooking.getSeats()));
        priceLabel.setText("$" + (currentBooking.getSeats() * movie.getTicketPrice()));
    }

    private void cancelBooking() {
        if (currentBooking != null) {
            currentBooking.getMovie().setSeatsAvailable(
                currentBooking.getMovie().getSeatsAvailable() + currentBooking.getSeats()
            );
            currentUser.getBookings().remove(currentBooking);

            JOptionPane.showMessageDialog(this, "Reservation cancelled.");

            if (currentUser.getBookings().isEmpty()) {
                this.dispose();
                new CustomerMenu(movieList, userList).setVisible(true);
            } else {
                bookingSelector.removeItemAt(bookingSelector.getSelectedIndex());
                if (bookingSelector.getItemCount() > 0) {
                    bookingSelector.setSelectedIndex(0);
                }
            }
        }
    }
}
