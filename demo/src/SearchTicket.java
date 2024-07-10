import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchTicket extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTicket frame = new SearchTicket();
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
	public SearchTicket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicketId = new JLabel("Ticket ID");
		lblTicketId.setBounds(103, 52, 88, 22);
		lblTicketId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblTicketId);
		
		JLabel lblTicketNumber = new JLabel("Ticket Number");
		lblTicketNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTicketNumber.setBounds(103, 103, 141, 25);
		contentPane.add(lblTicketNumber);
		
		JLabel lblStartDestination = new JLabel("Start Destination");
		lblStartDestination.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStartDestination.setBounds(103, 154, 141, 25);
		contentPane.add(lblStartDestination);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(254, 57, 233, 25);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(254, 109, 233, 25);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(254, 160, 233, 25);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Search");
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(138, 321, 116, 42);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the values from the text fields
		        String ticketId = textField.getText();
		        String ticketNumber = textField_1.getText();
		        String startDestination = textField_2.getText();
		        String endDestination = textField_3.getText();
		        String userName = textField_4.getText();

		        // Connect to the database
		        Connection connection = null;
		        try {
		            String url = "jdbc:mysql://localhost:3306/train";
		            String dbUsername = "root";
		            String dbPassword = "1201Tuong@";

		            // Register the JDBC driver
		            Class.forName("com.mysql.cj.jdbc.Driver");

		            // Establish the connection
		            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

		            // Prepare the SQL statement to search for the ticket
		            String sql = "SELECT * FROM Ticket WHERE ticketID = ? AND ticket_number = ? AND start_Destination = ? AND end_destination = ? AND userName = ?";
		            PreparedStatement statement = connection.prepareStatement(sql);
		            statement.setString(1, ticketId);
		            statement.setString(2, ticketNumber);
		            statement.setString(3, startDestination);
		            statement.setString(4, endDestination);
		            statement.setString(5, userName);

		            // Execute the query
		            ResultSet resultSet = statement.executeQuery();

		            // Check if the ticket exists
		            if (resultSet.next()) {
		                // Ticket found, perform necessary actions
		                // ...

		                // Display success notification
		                JOptionPane.showMessageDialog(null, "Ticket found!");
		            } else {
		                // Ticket not found, display error notification
		                JOptionPane.showMessageDialog(null, "Wrong information. Please fill in the correct details.");
		            }

		            // Close the result set and statement
		            resultSet.close();
		            statement.close();
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        } finally {
		            // Close the connection
		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    }
		});
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the values from the text fields
		        String ticketId = textField.getText();
		        String ticketNumber = textField_1.getText();
		        String startDestination = textField_2.getText();
		        String endDestination = textField_3.getText();
		        String userName = textField_4.getText();

		        // Connect to the database
		        Connection connection = null;
		        try {
		            String url = "jdbc:mysql://localhost:3306/train";
		            String dbUsername = "root";
		            String dbPassword = "1201Tuong@";

		            // Register the JDBC driver
		            Class.forName("com.mysql.cj.jdbc.Driver");

		            // Establish the connection
		            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

		            // Prepare the SQL statement to check the ticket number
		            String checkSql = "SELECT ticket_number FROM Ticket WHERE ticketId = ?";
		            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
		            checkStatement.setString(1, ticketId);

		            // Execute the query
		            ResultSet checkResultSet = checkStatement.executeQuery();

		            // Check if the ticket exists
		            if (checkResultSet.next()) {
		                int currentTicketNumber = checkResultSet.getInt("ticket_number");

		                // Calculate the new ticket number
		                int newTicketNumber = currentTicketNumber - Integer.parseInt(ticketNumber);

		                // Check if the input ticket number is valid
		                if (newTicketNumber >= 0) {
		                    // Prepare the SQL statement to update the ticket number
		                    String updateSql = "UPDATE Ticket SET ticket_number = ? WHERE ticketId = ?";
		                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
		                    updateStatement.setInt(1, newTicketNumber);
		                    updateStatement.setString(2, ticketId);

		                    // Execute the update query
		                    int rowsAffected = updateStatement.executeUpdate();

		                    // Check if the ticket number was successfully updated
		                    if (rowsAffected > 0) {
		                        // Ticket number updated successfully
		                        // ...

		                        // Display success notification
		                        JOptionPane.showMessageDialog(null, "Ticket number updated successfully!");
		                    } else {
		                        // Error updating ticket number, display error notification
		                        JOptionPane.showMessageDialog(null, "Failed to update ticket number.");
		                    }

		                    // Close the update statement
		                    updateStatement.close();
		                } else {
		                    // Ticket number is larger than in the database, display error notification
		                    JOptionPane.showMessageDialog(null, "Can't delete this ticket.");
		                }
		            } else {
		                // Ticket not found, display error notification
		                JOptionPane.showMessageDialog(null, "Wrong information. Please fill in the correct details.");
		            }

		            // Close the result set and check statement
		            checkResultSet.close();
		            checkStatement.close();
		        } catch (NumberFormatException ex) {
		            // Handle invalid ticket number format
		            JOptionPane.showMessageDialog(null, "Invalid ticket number format. Please enter a valid number.");
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		        } finally {
		            // Close the connection
		            if (connection != null) {
		                try {
		                    connection.close();
		                } catch (SQLException ex) {
		                    ex.printStackTrace();
		                }
		            }
		        }
		    }
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(415, 321, 116, 42);
		contentPane.add(btnDelete);
		
		JLabel lblEndDestination = new JLabel("End Destination");
		lblEndDestination.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEndDestination.setBounds(103, 205, 141, 25);
		contentPane.add(lblEndDestination);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setBounds(103, 253, 141, 25);
		contentPane.add(lblUserName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(254, 211, 233, 25);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(254, 259, 233, 25);
		contentPane.add(textField_4);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket ticket = new Ticket();
				ticket.setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(534, 10, 116, 42);
		contentPane.add(btnExit);
	}

}
