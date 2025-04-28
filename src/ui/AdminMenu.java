package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminMenu extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;

    public AdminMenu(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Admin Menu");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton manageMoviesBtn = new JButton("Manage Movie");
        JButton viewRevenueBtn = new JButton("View Revenue");

        manageMoviesBtn.addActionListener(e -> {
            this.dispose();  // Close Admin Menu window
            new ManageMovieWindow(movieList, userList).setVisible(true);
        });

        viewRevenueBtn.addActionListener(e -> {
            this.dispose();  // Close Admin Menu window
            new ViewRevenueWindow(movieList).setVisible(true);
        });

        add(manageMoviesBtn);
        add(viewRevenueBtn);
    }
}
