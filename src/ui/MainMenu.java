package ui;

import model.Movie;
import model.User;
import auth.Admin;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class MainMenu extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;

    public MainMenu(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Marvel Ticket Booking System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton customerBtn = new JButton("Login as Customer");
        JButton adminBtn = new JButton("Login as Admin");
        JButton exitBtn = new JButton("Exit");

        customerBtn.addActionListener(e -> {
            this.dispose();  
            new CustomerMenu(movieList, userList).setVisible(true);
        });

        adminBtn.addActionListener(e -> {
            String password = JOptionPane.showInputDialog(this, "Enter Admin Password:");
            if (auth.Admin.authenticate(password)) {
                this.dispose();  
                new AdminMenu(movieList, userList).setVisible(true);  
            } else {
                JOptionPane.showMessageDialog(this, "Wrong password!");
            }
        });
        
        exitBtn.addActionListener(e -> System.exit(0));

        add(customerBtn);
        add(adminBtn);
        add(exitBtn);
    }

    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        seedMovies(movieList);
        SwingUtilities.invokeLater(() -> new MainMenu(movieList, userList).setVisible(true));
    }

    private static void seedMovies(List<Movie> movieList) {
        movieList.add(new Movie("Iron Man", "12:00 PM", 50));
        movieList.add(new Movie("Black Panther", "1:30 PM", 40));
        movieList.add(new Movie("Doctor Strange", "3:00 PM", 30));
        movieList.add(new Movie("Spider-Man: Homecoming", "4:15 PM", 35));
        movieList.add(new Movie("Thor: Ragnarok", "5:45 PM", 25));
        movieList.add(new Movie("Captain Marvel", "6:30 PM", 20));
        movieList.add(new Movie("Avengers: Infinity War", "7:15 PM", 45));
        movieList.add(new Movie("Guardians of the Galaxy", "8:00 PM", 32));
        movieList.add(new Movie("Ant-Man and the Wasp", "9:00 PM", 28));
        movieList.add(new Movie("Avengers: Endgame", "10:00 PM", 60));
    }
    
}
