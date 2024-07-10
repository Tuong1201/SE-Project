import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JFrame frame;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
					frame.setVisible(true);
					Connection connection = frame.getConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Connection getConnection() throws SQLException {
	    // Update the following variables with your database connection details
	    String url = "jdbc:mysql://localhost:3306/train";
	    String username = "root";
	    String password = "1201Tuong@";

	    Connection connection = null;
	    try {
	        // Register the JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Establish the connection
	        connection = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    return connection;
	}
	public User() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(65, 105, 225));
		panel_2_1.setBounds(0, 0, 200, 692);
		contentPane.add(panel_2_1);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(76, 34, 87, 25);
		panel_2_1.add(lblUser);
		
		JButton ShowUser = new JButton("Show user");
		ShowUser.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Call the refreshTable method to display all users
		        retrieveTicketData();
		    }
		});
		ShowUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ShowUser.setBounds(26, 117, 145, 33);
		panel_2_1.add(ShowUser);
		
		JButton BackAdminPage = new JButton("Back ");
		BackAdminPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				frame.dispose();
			}
		});
		BackAdminPage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BackAdminPage.setBounds(26, 570, 145, 33);
		panel_2_1.add(BackAdminPage);
		
		JButton DeleteUser = new JButton("Delete user");
		DeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUser searchuser = new SearchUser();
				searchuser.setVisible(true);
			}
		});
		DeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DeleteUser.setBounds(26, 178, 145, 33);
		panel_2_1.add(DeleteUser);
		
		table = new JTable();
		table.setBounds(259, 188, 211, 0);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(227, 107, 886, 499);
		contentPane.add(table_1);
		
		JLabel lblUserid = new JLabel("userID");
		lblUserid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserid.setBounds(252, 77, 71, 20);
		contentPane.add(lblUserid);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserName.setBounds(408, 77, 106, 20);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(589, 77, 71, 20);
		contentPane.add(lblPassword);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(789, 77, 71, 20);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(967, 77, 71, 20);
		contentPane.add(lblEmail);

		
	}
	private void retrieveTicketData() {
	    try {
	        // Establish the database connection
	        Connection connection = getConnection();

	        // Prepare the SQL statement to retrieve all ticket data
	        String sql = "SELECT * FROM user";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        // Execute the query and retrieve the result set
	        ResultSet resultSet = statement.executeQuery();

	        // Create a table model to hold the data
	        DefaultTableModel tableModel = new DefaultTableModel();

	        // Retrieve the metadata (column names) from the result set
	        ResultSetMetaData metaData = resultSet.getMetaData();
	        int columnCount = metaData.getColumnCount();

	        // Add the column names to the table model
	        for (int i = 1; i <= columnCount; i++) {
	            tableModel.addColumn(metaData.getColumnName(i));
	        }

	        // Iterate through the result set and add each row of data to the table model
	        while (resultSet.next()) {
	            Vector<Object> rowData = new Vector<>();
	            for (int i = 1; i <= columnCount; i++) {
	                rowData.add(resultSet.getObject(i));
	            }
	            tableModel.addRow(rowData);
	        }

	        // Set the table model to the JTable
	        table_1.setModel(tableModel);

	        // Close the result set, statement, and connection
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
