import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ManageProfileWindow extends JFrame {
    public ManageProfileWindow(List<User> userList) {
        setTitle("Manage Profile");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton createBtn = new JButton("Create");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton cancelBtn = new JButton("Cancel");

        createBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();

            if (userList.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email))) {
                JOptionPane.showMessageDialog(this, "User already exists.");
            } else {
                userList.add(new User(name, email));
                JOptionPane.showMessageDialog(this, "Profile created.");
                dispose();
            }
        });

        updateBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();

            for (User u : userList) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    userList.remove(u);
                    userList.add(new User(name, email));
                    JOptionPane.showMessageDialog(this, "Profile updated.");
                    dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "User not found.");
        });

        deleteBtn.addActionListener(e -> {
            String email = emailField.getText();
            boolean removed = userList.removeIf(u -> u.getEmail().equalsIgnoreCase(email));
            JOptionPane.showMessageDialog(this, removed ? "Profile deleted." : "User not found.");
            if (removed) dispose();
        });

        cancelBtn.addActionListener(e -> dispose());

        add(nameLabel); add(nameField);
        add(emailLabel); add(emailField);
        add(createBtn); add(updateBtn);
        add(deleteBtn); add(cancelBtn);
    }
}
