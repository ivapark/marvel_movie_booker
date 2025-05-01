package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerManageProfileWindow extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;
    private User currentUser;

    private JTextField nameField;
    private JTextField emailField;

    public CustomerManageProfileWindow(List<Movie> movieList, List<User> userList, User currentUser) {
        this.movieList = movieList;
        this.userList = userList;
        this.currentUser = currentUser;

        setTitle("Manage Profile");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("My Profile", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);

        // Center Form
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField(currentUser.getName());
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField(currentUser.getEmail());
        formPanel.add(emailField);

        add(formPanel, BorderLayout.CENTER);

        // Bottom Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- Button Behaviors ---
        updateBtn.addActionListener(e -> {
            String newName = nameField.getText().trim();
            String newEmail = emailField.getText().trim();

            if (!newName.isEmpty() && !newEmail.isEmpty()) {
                currentUser.setName(newName);
                currentUser.setEmail(newEmail);
                JOptionPane.showMessageDialog(this, "Profile updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and email.");
            }
        });

        deleteBtn.addActionListener(e -> {
            userList.remove(currentUser);
            JOptionPane.showMessageDialog(this, "Profile deleted successfully.");
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });

        exitBtn.addActionListener(e -> {
            this.dispose();
            new CustomerMenu(movieList, userList).setVisible(true);
        });
    }
}
