import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class SearchUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUser frame = new SearchUser();
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
	public SearchUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(232, 90, 233, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get the value from the text field
		        String username = textField.getText();

		        // Check if the username is empty
		        if (username.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in the username");
		        } else {
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

		                // Prepare the SQL statement to delete the user
		                String sql = "DELETE FROM user WHERE username = ?";
		                PreparedStatement statement = connection.prepareStatement(sql);
		                statement.setString(1, username);

		                // Execute the query
		                int rowsAffected = statement.executeUpdate();

		                // Check if the user was successfully deleted
		                if (rowsAffected > 0) {
		                    // User deleted successfully
		                    // ...

		                    // Display success notification
		                    JOptionPane.showMessageDialog(null, "User deleted successfully!");
		                } else {
		                    // User not found, display error notification
		                    JOptionPane.showMessageDialog(null, "User not found. Please enter a correct username.");
		                }

		                // Close the statement
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
		    }
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(384, 171, 116, 42);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(106, 87, 116, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
				btnNewButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        // Get the value from the text field
				        String username = textField.getText();

				        // Check if the username is empty
				        if (username.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Please fill in the username");
				        } else {
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

				                // Prepare the SQL statement to search for the username
				                String sql = "SELECT * FROM user WHERE userName = ?";
				                PreparedStatement statement = connection.prepareStatement(sql);
				                statement.setString(1, username);

				                // Execute the query
				                ResultSet resultSet = statement.executeQuery();

				                // Check if the username exists
				                if (resultSet.next()) {
				                    // Username found, perform necessary actions
				                    // ...

				                    // Display success notification
				                    JOptionPane.showMessageDialog(null, "Username found!");
				                } else {
				                    // Username not found, display error notification
				                    JOptionPane.showMessageDialog(null, "Username not found. Please enter a correct username.");
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
				    }
				});
			
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(148, 171, 116, 42);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(522, 10, 116, 42);
		contentPane.add(btnExit);
	}

}
