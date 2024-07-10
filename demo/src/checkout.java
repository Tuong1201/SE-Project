import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class checkout extends JFrame {
    private JPanel contentPane;
    private JTable tablemodel;
    private String enteredUsername;
    private String userId;
    private double Total;
    private JLabel total;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String enteredUsername = "";
                    String userId = "";
                    checkout frame = new checkout(enteredUsername, userId);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public checkout(String enteredUsername, String userId) {
        this.enteredUsername = enteredUsername;
        this.userId = userId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 671, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mainpage mainpage = new Mainpage(enteredUsername, userId);
                mainpage.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.setBounds(83, 613, 161, 66);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton = new JButton("Checkout");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		QR qr = new QR();
        		qr.setVisible(true);
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(348, 613, 208, 66);
        contentPane.add(btnNewButton);

        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(0, 0, 0, 100));
        panel.setBounds(82, 36, 474, 521);
        contentPane.add(panel);
        panel.setLayout(null);
        
        total = new JLabel("");
        total.setForeground(Color.WHITE);
        total.setFont(new Font("Tahoma", Font.BOLD, 20));
        total.setBounds(158, 37, 151, 46);
        panel.add(total);

        JLabel checkoutLabel = new JLabel("");
        checkoutLabel.setBounds(358, 10, 106, 86);
        panel.add(checkoutLabel);
        ImageIcon img = new ImageIcon(this.getClass().getResource("/checkout (1).png"));
        checkoutLabel.setIcon(img);

        JLabel cartLabel = new JLabel("Cart");
        cartLabel.setForeground(SystemColor.textHighlightText);
        cartLabel.setFont(new Font("Tahoma", Font.BOLD, 49));
        cartLabel.setBounds(27, 16, 132, 80);
        panel.add(cartLabel);

        tablemodel = new JTable();
        tablemodel.setBounds(24, 124, 421, 374);
        
        panel.add(tablemodel);

        JLabel backgroundImg = new JLabel("");
        ImageIcon img2 = new ImageIcon(this.getClass().getResource("/d6125754ead4a5b9e0d383360b7c7ad5.jpg"));
        backgroundImg.setIcon(img2);
        backgroundImg.setBounds(0, 0, 1266, 713);
        contentPane.add(backgroundImg);
        
        fetchPaymentData(); // Fetch and display payment data in the table
    }

    private void fetchPaymentData() {
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT payment.paymentID, payment.userName, payment.userPhone, payment.bill, payment.status, User.userId, User.userPassword, User.Phone, User.email " +
                     "FROM payment JOIN User ON payment.userName = User.userName " +
                     "WHERE User.userName = ?")) {

            statement.setString(1, enteredUsername);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Payment ID");
            tableModel.addColumn("User Name");
            tableModel.addColumn("User Phone");
            tableModel.addColumn("Bill");
            tableModel.addColumn("Status");

            // Reset the total before calculating for each row
            Total = 0;

            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("paymentID");
                row[1] = resultSet.getString("userName");
                row[2] = resultSet.getString("userPhone");
                double bill = resultSet.getDouble("bill");
                row[3] = bill;
                row[4] = resultSet.getString("status");

                // Add the bill amount to the total
                Total += bill;

                tableModel.addRow(row);
            }

            tablemodel.setModel(tableModel);

            // Update the text of the total JLabel with the value of Total
            total.setText("Total: $" + Total);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class DatabaseConnection {
        private final String DB_URL = "jdbc:mysql://localhost:3306/train";
        private final String USERNAME = "root";
        private final String PASSWORD = "1201Tuong@";

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
    }
}