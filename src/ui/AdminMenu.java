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
        setSize(400, 300); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10)); 

        JButton manageMoviesBtn = new JButton("Manage Movie");
        JButton viewRevenueBtn = new JButton("View Revenue");
        JButton exitBtn = new JButton("Exit");

        manageMoviesBtn.addActionListener(e -> {
            this.dispose();
            new AdminManageMovieWindow(movieList, userList).setVisible(true);
        });

        viewRevenueBtn.addActionListener(e -> {
            this.dispose();
            new AdminViewRevenueWindow(movieList, userList).setVisible(true); 
        });

        exitBtn.addActionListener(e -> {
            this.dispose();
            new MainMenu(movieList, userList).setVisible(true);
        });

        add(manageMoviesBtn);
        add(viewRevenueBtn);
        add(exitBtn);
    }
}
