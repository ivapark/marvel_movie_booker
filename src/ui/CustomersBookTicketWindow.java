package ui;

import model.Movie;
import model.User;
import model.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomersBookTicketWindow extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private Movie selectedMovie;

    private JTextField seatsField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField priceField;

    public CustomersBookTicketWindow(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        // Select Movie First
        selectedMovie = selectMovieDialog();
        if (selectedMovie == null) {
            new CustomerBrowseMoviesWindow(movieList, userList).setVisible(true);
            dispose();
            return;
        }

        // Set default price if not set
        if (selectedMovie.getTicketPrice() == 0) {
            selectedMovie.setTicketPrice(10);
        }

        setTitle("Browse Movies - Book a Ticket");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top Label
        JLabel titleLabel = new JLabel("Booking: " + selectedMovie.getTitle() + ", " + selectedMovie.getShowtime(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Center Form
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

        // Bottom Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton bookBtn = new JButton("Book");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(bookBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Auto-update price when seats are entered
        seatsField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updatePrice(); }
        });

        // Book Button Action
        bookBtn.addActionListener(e -> {
            try {
                int seats = Integer.parseInt(seatsField.getText().trim());
                String username = nameField.getText().trim();
                String email = emailField.getText().trim();

                if (seats <= 0 || username.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill out all fields correctly.");
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
                            User newUser = new User(username, email);
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again.");
            }
        });

        // Exit Button Action
        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerBrowseMoviesWindow(movieList, userList).setVisible(true);
        });
    }

    private void updatePrice() {
        try {
            int seats = Integer.parseInt(seatsField.getText().trim());
            if (seats >= 0) {
                priceField.setText("$" + (seats * selectedMovie.getTicketPrice()));
            } else {
                priceField.setText("");
            }
        } catch (NumberFormatException ex) {
            priceField.setText("");
        }
    }

    private Movie selectMovieDialog() {
        String[] movieTitles = movieList.stream()
                .map(Movie::getTitle)
                .toArray(String[]::new);

        String selectedTitle = (String) JOptionPane.showInputDialog(
                null,
                "Select a movie:",
                "Movie Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                movieTitles,
                movieTitles.length > 0 ? movieTitles[0] : null
        );

        if (selectedTitle == null) {
            return null;
        }

        return movieList.stream()
                .filter(m -> m.getTitle().equalsIgnoreCase(selectedTitle))
                .findFirst()
                .orElse(null);
    }
}
