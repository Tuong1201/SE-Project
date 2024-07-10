import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.ImageIcon;

public class Signup extends JFrame {

    private JPanel contentPane;
    private JTextField username;
    private JTextField password;
    private JTextField confirm_password;
    private JTextField phone_num;
    private JTextField email;

    // MySQL database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1201Tuong@";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Signup frame = new Signup();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Signup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 826, 580);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        email = new JTextField();
        email.setColumns(10);
        email.setBounds(328, 305, 275, 22);
        contentPane.add(email);

        phone_num = new JTextField();
        phone_num.setColumns(10);
        phone_num.setBounds(328, 256, 275, 22);
        contentPane.add(phone_num);

        JLabel lblNewLabel_1_3 = new JLabel("Email");
        lblNewLabel_1_3.setForeground(Color.WHITE);
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_3.setBounds(232, 305, 66, 22);
        contentPane.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_2 = new JLabel("Phone number");
        lblNewLabel_1_2.setForeground(Color.WHITE);
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_2.setBounds(146, 256, 153, 22);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Confirm Password");
        lblNewLabel_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1_1.setBounds(111, 422, 207, 22);
        contentPane.add(lblNewLabel_1_1_1);

        confirm_password = new JTextField();
        confirm_password.setColumns(10);
        confirm_password.setBounds(328, 422, 275, 22);
        contentPane.add(confirm_password);

        JLabel lblNewLabel_2 = new JLabel("TicketBox");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 89));
        lblNewLabel_2.setBounds(182, 75, 526, 95);
        contentPane.add(lblNewLabel_2);

        username = new JTextField();
        username.setColumns(10);
        username.setBounds(328, 205, 275, 22);
        contentPane.add(username);

        password = new JTextField();
        password.setColumns(10);
        password.setBounds(328, 368, 275, 22);
        contentPane.add(password);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(333, 469, 119, 39);
        btnSignUp.addActionListener(e -> {
            String uname = username.getText();
            String pass = password.getText();
            String confirmPass = confirm_password.getText();
            String phone = phone_num.getText();
            String userEmail = email.getText();

            if (pass.equals(confirmPass)) {
                if (validateSignup(uname, pass, phone, userEmail)) {
                    if (isUsernameAvailable(uname)) {
                        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                                PreparedStatement statement = conn.prepareStatement(
                                        "INSERT INTO user (userId, userName, userPassword, Phone, email) VALUES (?, ?, ?, ?, ?)")) {
                            String userId = UUID.randomUUID().toString(); // Generate random UUID as user ID
                            statement.setString(1, userId);
                            statement.setString(2, uname);
                            statement.setString(3, pass);
                            statement.setString(4, phone);
                            statement.setString(5, userEmail);
                            statement.executeUpdate();

                            // Clear the input fields after successful signup
                            username.setText("");
                            password.setText("");
                            confirm_password.setText("");
                            phone_num.setText("");
                            email.setText("");

                            // Optionally, display a success message to the user
                            JOptionPane.showMessageDialog(null, "Signup Successful!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Login login = new Login();
                            login.setVisible(true);
                            dispose();
                            login.setLocationRelativeTo(null);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            // Handle any database errors or display an error message to the user
                            JOptionPane.showMessageDialog(null, "Ooooopssss", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Display an error message to the user about a taken username
                        JOptionPane.showMessageDialog(null, "Username already exists!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // Display an error message to the user about password mismatch
                JOptionPane.showMessageDialog(null, "Password and Confirm Password do not match!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(btnSignUp);

        JLabel lblNewLabel_1 = new JLabel("User Name");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(182, 205, 111, 22);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(193, 363, 125, 22);
        contentPane.add(lblNewLabel_1_1);

        JLabel sigupimg = new JLabel("");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/z5475046974718_dd26476f6ad2c7a621281bf1a566fcde.jpg"));
        sigupimg.setIcon(img);
        sigupimg.setBounds(0, 0, 814, 540);
        contentPane.add(sigupimg);

        JLabel label = new JLabel("New label");
        label.setBounds(192, 243, 45, 13);
        contentPane.add(label);
    }

    private boolean validateSignup(String username, String password, String phone, String email) {
        if (username.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            // Display an error message to the user about empty fields
            JOptionPane.showMessageDialog(null, "Please fill in all the required fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // You can add additional validation checks here

        return true;
    }

    private boolean isUsernameAvailable(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE userName = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next(); // Return true if the result set is empty (username is available)
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any database errors or display an error message to the user
            JOptionPane.showMessageDialog(null, "Oops! An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}