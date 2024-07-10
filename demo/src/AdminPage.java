import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import java.awt.Panel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

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
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		this.setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(83, 206, 181, 0);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(95, 251, 155, 0);
		contentPane.add(table_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(65, 105, 225));
		panel_2_1.setBounds(-7, 0, 200, 639);
		contentPane.add(panel_2_1);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Page");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(50, 23, 106, 25);
		panel_2_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton User = new JButton("User");
		User.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User user = new User();
				user.setVisible(true);
				frame.dispose();
			}
		});
		User.setBounds(26, 119, 145, 33);
		panel_2_1.add(User);
		User.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton Ticket = new JButton("Ticket ");
		Ticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ticket ticket = new Ticket();
				ticket.setVisible(true);
				frame.dispose();
			}
		});
		Ticket.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Ticket.setBounds(26, 172, 145, 33);
		panel_2_1.add(Ticket);
		
		JPanel panel = new JPanel();
		panel.setBounds(191, 0, 950, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ticket Online System");
		lblNewLabel_1.setBounds(366, 22, 220, 25);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/hana109-07m27s.png"));
		lblNewLabel_2.setIcon(img);
		lblNewLabel_2.setBounds(105, 63, 1168, 576);
		
		
		contentPane.add(lblNewLabel_2);
	}
}
