import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminMenu extends JFrame {
    public AdminMenu(List<Movie> movieList, List<User> userList) {
        setTitle("Admin Menu");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton addMovieBtn = new JButton("Add Movie");
        JButton modifyMovieBtn = new JButton("Modify Movie");
        JButton viewUsersBtn = new JButton("View Registered Users");
        JButton exitBtn = new JButton("Exit");

        addMovieBtn.addActionListener(e -> new AddMovieWindow(movieList).setVisible(true));
        modifyMovieBtn.addActionListener(e -> new ModifyMovieWindow(movieList).setVisible(true));
        viewUsersBtn.addActionListener(e -> new ViewUsersWindow(userList).setVisible(true));
        exitBtn.addActionListener(e -> dispose());

        add(addMovieBtn);
        add(modifyMovieBtn);
        add(viewUsersBtn);
        add(exitBtn);
    }
}
