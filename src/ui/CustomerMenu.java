package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class CustomerMenu extends JFrame {
    public CustomerMenu(List<Movie> movieList, List<User> userList) {
        setTitle("Customer Menu");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton browseBtn = new JButton("Browse Movies");
        JButton bookBtn = new JButton("Book a Ticket");
        JButton manageProfileBtn = new JButton("Manage Profile");
        JButton exitBtn = new JButton("Exit");

        browseBtn.addActionListener(e -> new BrowseMoviesWindow(movieList).setVisible(true));
        bookBtn.addActionListener(e -> new BookTicketWindow(movieList, userList).setVisible(true));
        manageProfileBtn.addActionListener(e -> new ManageProfileWindow(userList).setVisible(true));
        exitBtn.addActionListener(e -> dispose());

        add(browseBtn);
        add(bookBtn);
        add(manageProfileBtn);
        add(exitBtn);
    }
}
