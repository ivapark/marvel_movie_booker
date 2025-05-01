package ui;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerMenu extends JFrame {
    private List<Movie> movieList;
    private List<User> userList;

    public CustomerMenu(List<Movie> movieList, List<User> userList) {
        this.movieList = movieList;
        this.userList = userList;

        setTitle("Customer Menu");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton browseBtn = new JButton("Browse Movies");
        JButton viewTicketBtn = new JButton("View My Ticket");
        JButton manageProfileBtn = new JButton("Manage Profile");
        JButton exitBtn = new JButton("Exit");

        browseBtn.addActionListener(e -> {
            this.dispose();  
            new CustomerBrowseMoviesWindow(movieList, userList).setVisible(true);
        });

        viewTicketBtn.addActionListener(e -> {
            String email = JOptionPane.showInputDialog(this, "Enter your email to view ticket:");
            if (email != null) {
                User currentUser = userList.stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(email))
                        .findFirst()
                        .orElse(null);
        
                if (currentUser != null) {
                    this.dispose();  
                    CustomerViewTicket.openTicket(movieList, userList, currentUser);
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            }
        });
        

        manageProfileBtn.addActionListener(e -> {
            String email = JOptionPane.showInputDialog(this, "Enter your email to manage profile:");
            if (email != null) {
                User currentUser = userList.stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(email))
                        .findFirst()
                        .orElse(null);
        
                if (currentUser != null) {
                    this.dispose();
                    new CustomerManageProfileWindow(movieList, userList, currentUser).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.");
                }
            }
        });
        
        exitBtn.addActionListener(e -> {
            this.dispose();
            new MainMenu(movieList, userList).setVisible(true); 
        });
    
        exitBtn.addActionListener(e -> dispose());

        add(browseBtn);
        add(viewTicketBtn);
        add(manageProfileBtn);
        add(exitBtn);
    }
}
