package ui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    private MainFrame mainFrame;

    public AdminPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addMovieButton = new JButton("Add New Movie");
        JButton modifyMovieButton = new JButton("Modify Movie");
        JButton viewRevenueButton = new JButton("View Revenue");
        JButton backButton = new JButton("Back");

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addMovieButton, gbc);

        gbc.gridy++;
        add(modifyMovieButton, gbc);

        gbc.gridy++;
        add(viewRevenueButton, gbc);

        gbc.gridy++;
        add(backButton, gbc);

        addMovieButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminAddMoviePanel");
        });

        modifyMovieButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminModifyMoviePanel");
        });

        viewRevenueButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminViewRevenuePanel");
        });

        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "HomeScreen");
        });
    }
}
