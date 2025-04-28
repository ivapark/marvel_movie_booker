package ui;

import javax.swing.*;
import java.awt.*;
import services.MovieTicketSystem;
import models.Movie;

public class AdminAddMoviePanel extends JPanel {
    private MainFrame mainFrame;
    private MovieTicketSystem system;

    private JTextField titleField;
    private JTextField showtimeField;
    private JTextField seatsField;
    private JTextField priceField;

    public AdminAddMoviePanel(MainFrame mainFrame, MovieTicketSystem system) {
        this.mainFrame = mainFrame;
        this.system = system;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        titleField = new JTextField(15);
        showtimeField = new JTextField(15);
        seatsField = new JTextField(5);
        priceField = new JTextField(5);

        JButton saveButton = new JButton("Save");
        JButton resetButton = new JButton("Reset");
        JButton cancelButton = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Showtime:"), gbc);
        gbc.gridx = 1;
        add(showtimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Available Seats:"), gbc);
        gbc.gridx = 1;
        add(seatsField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(saveButton, gbc);
        gbc.gridx = 1;
        add(resetButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(cancelButton, gbc);

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String showtime = showtimeField.getText();
            int seats = Integer.parseInt(seatsField.getText());
            double price = Double.parseDouble(priceField.getText());

            system.addMovie(new Movie(title, showtime, seats, price));

            JOptionPane.showMessageDialog(this, "Movie added successfully!");
            clearForm();
        });

        resetButton.addActionListener(e -> clearForm());

        cancelButton.addActionListener(e -> {
            clearForm();
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminPanel");
        });
    }

    private void clearForm() {
        titleField.setText("");
        showtimeField.setText("");
        seatsField.setText("");
        priceField.setText("");
    }
}
