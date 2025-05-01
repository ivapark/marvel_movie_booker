package ui;

import model.Movie;
import model.User;
import model.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerBookTicketWindow extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private Movie selectedMovie;

    private JTextField seatsField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField priceField;

    public CustomerBookTicketWindow(List<Movie> movieList, List<User> userList, Movie selectedMovie) {
        this.movieList = movieList;
        this.userList = userList;
        this.selectedMovie = selectedMovie;

        // Default price if not set
        if (selectedMovie.getTicketPrice() == 0) {
            selectedMovie.setTicketPrice(10);
        }

        setTitle("Browse Movies - Book a Ticket");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Booking: " + selectedMovie.getTitle() + ", " + selectedMovie.getShowtime(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel seatsLabel = new JLabel("Number of Seats:");
        seatsField = new JTextField();

        JLabel nameLabel = new JLabel("User's Name:");
        nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        priceField.setEditable(false);

        formPanel.add(seatsLabel);
        formPanel.add(seatsField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(priceLabel);
        formPanel.add(priceField);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton bookBtn = new JButton("Book");
        JButton exitBtn = new JButton("Exit");
        buttonPanel.add(bookBtn);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Price auto-update
        seatsField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
        });

        // Book button logic
        bookBtn.addActionListener(e -> {
            try {
                int seats = Integer.parseInt(seatsField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (seats <= 0 || name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields correctly.");
                    return;
                }

                if (selectedMovie.getSeatsAvailable() < seats) {
                    JOptionPane.showMessageDialog(this, "Not enough seats available.");
                    return;
                }

                User currentUser = userList.stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(email))
                        .findFirst()
                        .orElseGet(() -> {
                            User newUser = new User(name, email);
                            userList.add(newUser);
                            return newUser;
                        });

                selectedMovie.setSeatsAvailable(selectedMovie.getSeatsAvailable() - seats);
                currentUser.addBooking(new Booking(selectedMovie, seats));

                JOptionPane.showMessageDialog(this, "Ticket booked successfully!");
                this.dispose();
                new CustomerMenu(movieList, userList).setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number of seats.");
            }
        });

        // Exit button logic
        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });
    }

    private void updatePrice() {
        try {
            int seats = Integer.parseInt(seatsField.getText().trim());
            priceField.setText("$" + (seats * selectedMovie.getTicketPrice()));
        } catch (NumberFormatException ex) {
            priceField.setText("$0");
        }
    }
}
