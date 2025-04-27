package ui;

import javax.swing.*;
import java.awt.*;

public class MyTicketPanel extends JPanel {
    private MainFrame mainFrame;

    public MyTicketPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Title:"), gbc);
        gbc.gridy++;
        add(new JLabel("Showtime:"), gbc);
        gbc.gridy++;
        add(new JLabel("Number of Seats:"), gbc);
        gbc.gridy++;
        add(new JLabel("Amount:"), gbc);

        gbc.gridy++;
        JButton modifyButton = new JButton("Modify");
        JButton deleteButton = new JButton("Delete");
        JButton exitButton = new JButton("Exit");

        add(modifyButton, gbc);
        gbc.gridx++;
        add(deleteButton, gbc);
        gbc.gridx++;
        add(exitButton, gbc);

        exitButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();
            cl.show(mainFrame.getContentPane(), "UserPanel");
        });
    }
}
