package ui;

import javax.swing.*;
import java.awt.*;
import models.Movie;
import models.User;
import models.Booking;
import services.MovieTicketSystem;

public class BookTicketPanel extends JPanel {
    private MainFrame mainFrame;
    private MovieTicketSystem system;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField seatsField;
    private JTextField amountField;
    private JComboBox<String> movieDropdown;

    public BookTicketPanel(MainFrame mainFrame, MovieTicketSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        nameField = new JTextField(15);
        emailField = new JTextField(15);
        seatsField = new JTextField(5);
        amountField = new JTextField(10);
        amountField.setEditable(false);

        movieDropdown = new JComboBox<>();

        JButton bookButton = new JButton("Book");
        JButton cancelButton = new JButton("Cancel");

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Movie:"), gbc);
        gbc.gridx = 1;
        add(movieDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Seats:"), gbc);
        gbc.gridx = 1;
        add(seatsField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(bookButton, gbc);
        gbc.gridx = 1;
        add(cancelButton, gbc);

        seatsField.addActionListener(e -> updateAmount());

        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            int seats = Integer.parseInt(seatsField.getText());
            Movie movie = system.getMovies().get(movieDropdown.getSelectedIndex());
            double amount = movie.getPrice() * seats;

            User user = new User(name, email);
            Booking booking = new Booking(movie, user, seats, amount);

            system.addUser(user);
            system.addBooking(booking);

            JOptionPane.showMessageDialog(this, "Ticket booked successfully!");
            clearForm();
        });

        cancelButton.addActionListener(e -> {
            clearForm();
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "UserPanel");
        });
    }

    public void refreshMovies() {
        movieDropdown.removeAllItems();
        for (Movie movie : system.getMovies()) {
            movieDropdown.addItem(movie.getTitle() + " (" + movie.getShowtime() + ")");
        }
    }

    private void updateAmount() {
        try {
            int seats = Integer.parseInt(seatsField.getText());
            Movie movie = system.getMovies().get(movieDropdown.getSelectedIndex());
            double amount = movie.getPrice() * seats;
            amountField.setText(String.format("%.2f", amount));
        } catch (Exception e) {
            amountField.setText("");
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        seatsField.setText("");
        amountField.setText("");
    }
}
