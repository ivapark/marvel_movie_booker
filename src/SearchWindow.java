import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchWindow extends JFrame {
    public SearchWindow(List<Movie> movieList) {
        setTitle("Search Movie");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextField searchField = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        JButton searchBtn = new JButton("Search");
        JButton resetBtn = new JButton("Reset");
        JButton cancelBtn = new JButton("Cancel");

        topPanel.add(searchBtn, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(resetBtn);
        btnPanel.add(cancelBtn);

        searchBtn.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();
            resultArea.setText("");
            boolean found = false;
            for (Movie m : movieList) {
                if (m.getTitle().toLowerCase().contains(query)) {
                    resultArea.append(m + "\n");
                    found = true;
                }
            }
            if (!found) resultArea.setText("No movie found.");
        });

        resetBtn.addActionListener(e -> {
            searchField.setText("");
            resultArea.setText("");
        });

        cancelBtn.addActionListener(e -> dispose());

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
