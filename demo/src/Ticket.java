import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JTextField;

public class Ticket extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ticket frame = new Ticket();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	

	// ...

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
	private void retrieveTicketData() {
	    try {
	        // Establish the database connection
	        Connection connection = getConnection();

	        // Prepare the SQL statement to retrieve all ticket data
	        String sql = "SELECT * FROM Ticket";
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
	        table.setModel(tableModel);

	        // Close the result set, statement, and connection
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public Ticket() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(380, 10, 1, 1);
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(65, 105, 225));
		contentPane.add(panel_2_1);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(76, 34, 87, 25);
		panel_2_1.add(lblUser);
		
		JButton btnSearchUser = new JButton("Search user");
		btnSearchUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearchUser.setBounds(26, 172, 145, 33);
		panel_2_1.add(btnSearchUser);
		
		JButton btnShowUser = new JButton("Show user");
		btnShowUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowUser.setBounds(26, 117, 145, 33);
		panel_2_1.add(btnShowUser);
		
		JButton btnBack = new JButton("Back ");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(26, 439, 145, 33);
		panel_2_1.add(btnBack);
		
		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteUser.setBounds(26, 226, 145, 33);
		panel_2_1.add(btnDeleteUser);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBackground(new Color(65, 105, 225));
		panel_2_1_1.setBounds(0, 0, 200, 616);
		contentPane.add(panel_2_1_1);
		
		JLabel lblTicket = new JLabel("Ticket");
		lblTicket.setForeground(Color.WHITE);
		lblTicket.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTicket.setBounds(67, 45, 87, 25);
		panel_2_1_1.add(lblTicket);
		
		JButton ShowTicket = new JButton("Show ticket");
		ShowTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retrieveTicketData();
			}
		});
		ShowTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ShowTicket.setBounds(26, 117, 145, 33);
		panel_2_1_1.add(ShowTicket);
		
		JButton BackAdminPage = new JButton("Back ");
		BackAdminPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				frame.dispose();
			}
		});
		BackAdminPage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BackAdminPage.setBounds(26, 531, 145, 33);
		panel_2_1_1.add(BackAdminPage);
		
		JButton DeleteTicket = new JButton("Delete ticket");
		DeleteTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchTicket searchTicket = new SearchTicket();
				searchTicket.setVisible(true);
			}
		});
		DeleteTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		DeleteTicket.setBounds(26, 222, 145, 33);
		panel_2_1_1.add(DeleteTicket);
		
		JButton btnAddTicket = new JButton("Add ticket");
		btnAddTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchTicket searchTicket = new SearchTicket();
				searchTicket.setVisible(true);
				
			}
		});
		btnAddTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddTicket.setBounds(26, 166, 145, 33);
		panel_2_1_1.add(btnAddTicket);
		
		JButton SearchTicket = new JButton("Search ticket");
		SearchTicket.setBounds(26, 276, 145, 31);
		panel_2_1_1.add(SearchTicket);
		SearchTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchTicket searchTicket = new SearchTicket();
				searchTicket.setVisible(true);
			}
		});
		SearchTicket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		table = new JTable();
		table.setBounds(210, 128, 874, 463);
		contentPane.add(table);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(255, 255, 128));
		panel.setBounds(202, 0, 914, 79);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ticket ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(227, 98, 71, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_6 = new JLabel("Ticket ID");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(351, 148, 71, 20);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(364, 98, 71, 20);
		contentPane.add(lblPrice);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumber.setBounds(501, 98, 71, 20);
		contentPane.add(lblNumber);
		
		JLabel lblStart = new JLabel("From");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStart.setBounds(650, 98, 71, 20);
		contentPane.add(lblStart);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTo.setBounds(818, 98, 71, 20);
		contentPane.add(lblTo);
		
		JLabel lblUser_1 = new JLabel("User");
		lblUser_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser_1.setBounds(959, 98, 71, 20);
		contentPane.add(lblUser_1);
		
		
	}
}
