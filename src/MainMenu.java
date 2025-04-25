import javax.swing.*;                  // For JFrame, JButton, etc.
import java.awt.*;                     // For GridLayout
import java.util.List;                 // For List
import java.util.ArrayList;            // For ArrayList

public class MainMenu extends JFrame {
    public MainMenu(List<Movie> movieList) {
        setTitle("Marvel Ticket Booking System");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton addNewBtn = new JButton("Add New");
        JButton searchBtn = new JButton("Search");
        JButton browseBtn = new JButton("Browse");

        addNewBtn.addActionListener(e -> new AddMovieWindow(movieList).setVisible(true));
        searchBtn.addActionListener(e -> new SearchWindow(movieList).setVisible(true));
        browseBtn.addActionListener(e -> new BrowseWindow(movieList).setVisible(true));

        add(addNewBtn);
        add(searchBtn);
        add(browseBtn);
    }

    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Iron Man", "12:00PM", 10));
        movieList.add(new Movie("Black Panther", "3:00PM", 8));
        movieList.add(new Movie("Endgame", "6:00PM", 5));

        SwingUtilities.invokeLater(() -> new MainMenu(movieList).setVisible(true));
    }
}
