import ui.MainMenu;
import model.Movie;
import model.User;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Entry point for the Marvel Ticket Booking System (pure OOP, no files).
 */
public class Main {
    public static void main(String[] args) {
        // Freshly create new lists every time the program runs
        List<Movie> movieList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        // Seed some movies
        seedMovies(movieList);

        // Start the GUI
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu(movieList, userList);
            mainMenu.setVisible(true);
        });
    }

    /**
     * Seeds sample Marvel movies into the movie list.
     */
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
