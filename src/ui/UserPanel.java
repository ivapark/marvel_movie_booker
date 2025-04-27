package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private MainFrame mainFrame;

    public UserPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton browseButton = new JButton("Browse Movies");
        JButton ticketButton = new JButton("View My Ticket");
        JButton profileButton = new JButton("My Profile");

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(browseButton, gbc);

        gbc.gridy = 1;
        add(ticketButton, gbc);

        gbc.gridy = 2;
        add(profileButton, gbc);

        browseButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "BrowseMoviesPanel");
        });

        ticketButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "MyTicketPanel");
        });

        profileButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "MyProfilePanel");
        });
    }
}
