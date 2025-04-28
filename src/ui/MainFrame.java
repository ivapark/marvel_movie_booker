package ui;

import javax.swing.*;
import java.awt.*;
import services.MovieTicketSystem;

public class MainFrame extends JFrame {
    private MovieTicketSystem system;

    public MainFrame() {
        setTitle("Marvel Movie Ticket Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // 1. Initialize your backend system
        system = new MovieTicketSystem();

        // 2. Add all panels to the frame
        getContentPane().add(new HomeScreen(this), "HomeScreen");
        getContentPane().add(new UserPanel(this), "UserPanel");
        getContentPane().add(new BrowseMoviesPanel(this, system), "BrowseMoviesPanel");
        getContentPane().add(new BookTicketPanel(this, system), "BookTicketPanel");
        getContentPane().add(new MyTicketPanel(this), "MyTicketPanel");
        getContentPane().add(new MyProfilePanel(this), "MyProfilePanel");
        getContentPane().add(new AdminPanel(this), "AdminPanel");
        getContentPane().add(new AdminAddMoviePanel(this, system), "AdminAddMoviePanel");
        getContentPane().add(new AdminModifyMoviePanel(this, system), "AdminModifyMoviePanel");
        getContentPane().add(new AdminViewRevenuePanel(this, system), "AdminViewRevenuePanel");

        // 3. Show the HomeScreen first
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "HomeScreen");
    }
}
