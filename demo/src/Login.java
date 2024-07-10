
	import java.awt.EventQueue;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.awt.Canvas;
	import javax.swing.JTextArea;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
	import java.awt.Font;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
	
	public class Login extends JFrame {
	
		private JPanel contentPane;
		private JTextField username;
		private String enteredUsername;
		private String userId;
		
	
		// MySQL database connection details
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/train";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "1201Tuong@";
	    private JPasswordField password;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			String enteredUsername ="";
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Login frame = new Login();
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
		public Login() {
			this.userId =userId;
			setBackground(new Color(240, 240, 240));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 819, 571);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(64, 128, 128));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			password = new JPasswordField();
			password.setBounds(307, 251, 275, 19);
			contentPane.add(password);
			
			username = new JTextField();
			username.setBounds(307, 193, 275, 22);
	//		textField.setBackground( new Color(0,0,0,0));
			contentPane.add(username);
			username.setColumns(10);
			
			JButton login = new JButton("Log In");
			login.setBounds(307, 309, 119, 39);
			login.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String enteredUsername = username.getText();
	                String enteredPassword = password.getText();

	                if (validateLogin(enteredUsername, enteredPassword)) {
	                	Mainpage mainpage = new Mainpage(enteredUsername, userId);
	                	mainpage.setVisible(true);
				        dispose();
				        mainpage.setLocationRelativeTo(null);
	                } else {
	                	JOptionPane.showMessageDialog(null, "Password or Username is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });
			contentPane.add(login);
JLabel signup_option = new JLabel("Don't have an account? Sign up");
			signup_option.setForeground(new Color(255, 255, 255));
			signup_option.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Signup signup = new Signup();
			        signup.setVisible(true);
			        dispose();
			        signup.setLocationRelativeTo(null);
				}
			});
			
			signup_option.setFont(new Font("Tahoma", Font.BOLD, 14));
			signup_option.setLabelFor(this);
			signup_option.setBounds(566, 503, 233, 22);
			contentPane.add(signup_option);
			
			JLabel lblNewLabel_1 = new JLabel("User Name");
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_1.setBounds(172, 193, 111, 22);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_1 = new JLabel("Password");
			lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel_1_1.setBounds(172, 247, 125, 18);
			contentPane.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_2 = new JLabel("TicketBox");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 89));
			lblNewLabel_2.setBounds(175, 71, 526, 95);
			contentPane.add(lblNewLabel_2);
			
			JLabel LoginIMG = new JLabel("");
			LoginIMG.setBackground(new Color(240, 240, 240));
			LoginIMG.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\train-canada-landscape-mountains-wallpaper-preview (1).jpg"));
			LoginIMG.setBounds(-228, 194, 814, 540);
			contentPane.add(LoginIMG);
		}
		private boolean validateLogin(String username, String password) {
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        boolean isValidLogin = false;

	        try {
	            // Establish database connection
	            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	            // Prepare SQL statement
	            String sql = "SELECT * FROM user WHERE userName = ? AND userPassword = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            // Execute the query
	            rs = stmt.executeQuery();

	            // Check if the query returned any rows
	            if (rs.next()) {
	                isValidLogin = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the database resources
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
return isValidLogin;
	    }
	}