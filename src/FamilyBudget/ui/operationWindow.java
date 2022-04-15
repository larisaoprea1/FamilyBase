package FamilyBudget.ui;

import java.awt.Color;

import FamilyBudget.connection.MySQLConnection;
import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class operationWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					operationWindow frame = new operationWindow();
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
	public operationWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo.png"));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					viewFamilyMembers();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 312, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		scrollPane.setBounds(20, 62, 261, 336);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		table.setFont(new Font("Georgia", Font.PLAIN, 11));
		table.setGridColor(new Color(123, 104, 238));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(123, 104, 238));
		table.setBackground(Color.WHITE);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBackground(new Color(123, 104, 238));
		btnSelect.setForeground(new Color(255, 255, 255));
		btnSelect.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectActionListener(e);
					getId();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSelect.setBounds(164, 409, 89, 23);
		contentPane.add(btnSelect);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(123, 104, 238));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnBack.setBounds(40, 409, 89, 23);
		contentPane.add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(123, 104, 238));
		panel.setBounds(0, 0, 306, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select row and press Select button");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 5, 282, 31);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
	}
	
	private void viewFamilyMembers() throws Exception{
		try {
			Statement stmt = MySQLConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * FROM members");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs = stmt.getResultSet();
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void selectActionListener(ActionEvent E) throws Exception
	{
		new selectedUserWindow().setVisible(true);
		this.dispose();
	}
	
	
	public static int getId()
	{
		int id = (int) table.getValueAt(table.getSelectedRow(), 0);
		return id;

	}
	
	public static String getTitularName() {
		String name = (String) table.getValueAt(table.getSelectedRow(), 1);
		return name;
	}
	
	private void closeWindow()
	{
		mainWindow.setVisible();
		this.dispose();
	}
}
