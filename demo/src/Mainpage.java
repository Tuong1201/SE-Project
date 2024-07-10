

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Mainpage extends JFrame {

    private JPanel contentPane;
    private JTextField from;
    private JTextField to;
    private JTable table;
    private String enteredUsername;
    private String userId;
    private double Total;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	String enteredUsername = "";
    	String userId ="";
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mainpage frame = new Mainpage(enteredUsername,userId);
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
   
    public Mainpage(String enteredUsername, String userId) {
    	this.userId = userId;
    	this.enteredUsername = enteredUsername;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1280, 762);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel logout = new JLabel("");
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
                login.setLocationRelativeTo(null);
            }
        });
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Seat");
        lblNewLabel_1_2_1.setForeground(Color.WHITE);
        lblNewLabel_1_2_1.setFont(new Font("Dialog", Font.BOLD, 17));
        lblNewLabel_1_2_1.setBounds(948, 256, 76, 25);
        contentPane.add(lblNewLabel_1_2_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("End");
        lblNewLabel_1_2.setForeground(Color.WHITE);
        lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 17));
        lblNewLabel_1_2.setBounds(625, 256, 150, 25);
        contentPane.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_1 = new JLabel("Start ");
        lblNewLabel_1_1.setForeground(Color.WHITE);
        lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 17));
        lblNewLabel_1_1.setBounds(470, 256, 142, 25);
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setForeground(Color.WHITE);
        lblPrice.setFont(new Font("Dialog", Font.BOLD, 17));
        lblPrice.setBackground(Color.WHITE);
        lblPrice.setBounds(785, 256, 88, 25);
        contentPane.add(lblPrice);
        
        JLabel lblNewLabel_1 = new JLabel("Date");
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 17));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(346, 256, 88, 25);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblPhone = new JLabel("TicketID");
        lblPhone.setForeground(Color.WHITE);
        lblPhone.setBackground(Color.WHITE);
        lblPhone.setFont(new Font("Dialog", Font.BOLD, 17));
        lblPhone.setBounds(200, 256, 76, 25);
        contentPane.add(lblPhone);
        
        JLabel lblUsername1 = new JLabel("Logged in as: " + enteredUsername);
        lblUsername1.setForeground(Color.WHITE);
        lblUsername1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername1.setBounds(20, 20, 200, 20);
        contentPane.add(lblUsername1);
        
        JLabel checkout = new JLabel("");
        checkout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkout out = new checkout(enteredUsername,userId);
                out.setVisible(true);
                dispose();
                out.setLocationRelativeTo(null);
            }
        });

        ImageIcon img = new ImageIcon(this.getClass().getResource("/checkout (1).png"));
        checkout.setBounds(1063, 0, 100, 100);
        checkout.setIcon(img);
        contentPane.add(checkout);
        ImageIcon img1 = new ImageIcon(this.getClass().getResource("/out (3).png"));
        logout.setIcon(img1);
        logout.setBounds(1179, 0, 64, 86);
        contentPane.add(logout);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(1249, 0, 17, 720);
        contentPane.add(scrollBar);

        JLabel lblNewLabel = new JLabel("TicketBox");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 89));
        lblNewLabel.setBounds(369, 20, 526, 95);
        contentPane.add(lblNewLabel);

        table = new JTable();
        table.setLayout(null);
        table.setBounds(183, 300, 855, 326);
        contentPane.add(table);

        JPanel panel = new JPanel();
        panel.setBackground(new java.awt.Color(0, 152, 255, 230));
        panel.setBounds(183, 140, 855, 63);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel From = new JLabel("     From");
        From.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        From.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                From.setForeground(Color.RED); // Change the text color to red
                From.setBackground(new Color(255, 192, 203)); // Change the background color to pink
                From.setOpaque(true); // Set the label as opaque to display the background color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                From.setForeground(Color.BLACK); // Reset the text color to black when the mouse exits
                From.setBackground(null); // Reset the background color to default when the mouse exits
                From.setOpaque(false); // Set the label as non-opaque to remove the background color
            }
        });
        From.setBounds(0, 0, 129, 60);
        panel.add(From);

        JLabel To = new JLabel("        To");
        To.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        To.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                To.setForeground(Color.RED); // Change the text color to red
                To.setBackground(new Color(255, 192, 203)); // Change the background color to pink
                To.setOpaque(true); // Set the label as opaque to display the background color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                To.setForeground(Color.BLACK); // Reset the text color to black when the mouse exits
                To.setBackground(null); // Reset the background color to default when the mouse exits
                To.setOpaque(false); // Set the label as non-opaque to remove the background color
            }
        });
        To.setBounds(341, 0, 129, 60);
        panel.add(To);

        from = new JTextField();
        from.setBounds(139, 0, 188, 60);
        panel.add(from);
        from.setColumns(10);

        to = new JTextField();
        to.setBounds(480, 0, 240, 60);
        panel.add(to);
        to.setColumns(10);

        JButton searchbutton = new JButton("SEARCH");
        searchbutton.setBounds(721, 0, 134, 63);
        panel.add(searchbutton);
        searchbutton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        searchbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startDestination = from.getText();
                String endDestination = to.getText();

                try {
                    DatabaseConnection dbConnection = new DatabaseConnection();
                    Connection connection = dbConnection.getConnection();

                    String query = "SELECT * FROM train WHERE start_destination = ? AND end_destination = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, startDestination);
                    statement.setString(2, endDestination);

                    ResultSet resultSet = statement.executeQuery();

                    // Create a table model with heading column
                    String[] columnNames = {"Train ID", "Date", "Start Destination", "End Destination", "Price", "Seat"};
                    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                    while (resultSet.next()) {
                        Object[] rowData = new Object[columnNames.length];
                        for (int i = 0; i < columnNames.length; i++) {
                            rowData[i] = resultSet.getObject(i + 1);
                        }
                        tableModel.addRow(rowData);
                    }

                    table.setModel(tableModel);

                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle the exception appropriately
                }
            }
        });

        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        purchaseButton.setBounds(183, 653, 162, 40);
        contentPane.add(purchaseButton);
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                purchaseTicket();
                updatePaymentTable();
            }
        });

        JLabel backgroundIMG = new JLabel("");
        backgroundIMG.setBackground(new Color(240, 240, 240));
        backgroundIMG.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ImageIcon img2 = new ImageIcon(this.getClass().getResource("/z5475047446885_e0a7e8e6f2987d18dc9fd7d5ca05d884.jpg"));
        backgroundIMG.setIcon(img2);

        backgroundIMG.setBounds(0, 0, 1256, 725);
        contentPane.add(backgroundIMG);
    }

    private void purchaseTicket() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
           
            double bill = Double.parseDouble(table.getValueAt(selectedRow, 4).toString());
String status = "Pending";
            
           

                // Perform any additional logic or display a success message here
                JOptionPane.showMessageDialog(null, "Ticket purchased successfully!");
                updateSeatCount(selectedRow);
               

        } else {
           
            JOptionPane.showMessageDialog(null, "Please select a train before purchasing a ticket.");
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
    private void updateSeatCount(int selectedRow) {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            Connection connection = dbConnection.getConnection();

            String trainID = table.getValueAt(selectedRow, 0).toString();
            String query = "UPDATE train SET seat = seat - 1 WHERE trainID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, trainID);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately
        }
    }
    private void updatePaymentTable() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Establish database connection
            DatabaseConnection dbConnection = new DatabaseConnection();
            conn = dbConnection.getConnection();

            // Prepare SQL statement
            String sql = "INSERT INTO payment (userName, userPhone, bill, status) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, enteredUsername);
            stmt.setString(2, "1234567890"); // Replace with the user's phone number
            stmt.setDouble(3, Total); // Replace with the total bill amount
            stmt.setString(4, "Pending"); // Replace with the desired status

            // Execute the update
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Payment successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Payment failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
