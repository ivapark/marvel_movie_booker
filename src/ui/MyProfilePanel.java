package ui;

import javax.swing.*;
import java.awt.*;

public class MyProfilePanel extends JPanel {
    private MainFrame mainFrame;

    public MyProfilePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        add(new JLabel("Name:"), gbc);
        gbc.gridy++;
        add(new JLabel("Email:"), gbc);

        gbc.gridy++;
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        add(updateButton, gbc);
        gbc.gridx++;
        add(deleteButton, gbc);

        updateButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Profile updated successfully!");
        });

        deleteButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Profile deleted successfully!");
        });
    }
}
