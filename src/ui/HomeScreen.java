package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel {
    private MainFrame mainFrame;

    public HomeScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton userButton = new JButton("User");
        JButton adminButton = new JButton("Admin");
        JButton exitButton = new JButton("Exit");

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userButton, gbc);

        gbc.gridy = 1;
        add(adminButton, gbc);

        gbc.gridy = 2;
        add(exitButton, gbc);

        userButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "UserPanel");
        });

        adminButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "AdminPanel");
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}
