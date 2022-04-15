package FamilyBudget.ui;

import java.awt.Color;

import FamilyBudget.connection.MySQLConnection;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;



public class configWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTable table = new JTable();
	private JLabel nrMembers = new JLabel("");
	private JTextField nametextField;
	private JTextField idTextField;
	/**
	 * Launch the application.
	 */
	private static configWindow frame = new configWindow();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
	   
	}
	
	public static void close() {
		frame.setVisible(false);
	}
	/**
	 * Create the frame.
	 */

	
	public configWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 345);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		scrollPane.setBounds(256, 11, 265, 221);
		scrollPane.getViewport().setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		table.setSelectionBackground(new Color(123, 104, 238));
		table.setGridColor(new Color(123, 104, 238));
		table.setFont(new Font("Georgia", Font.PLAIN, 12));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JButton btnView = new JButton("View family members");
		btnView.setBackground(new Color(123, 104, 238));
		btnView.setForeground(new Color(255, 255, 255));
		btnView.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewFamilyMembers(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnView.setBounds(256, 243, 265, 23);
		contentPane.add(btnView);
		
		JLabel lblNewLabel = new JLabel("Number of family members:");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNewLabel.setBounds(90, 274, 214, 14);
		contentPane.add(lblNewLabel);
		nrMembers.setFont(new Font("Georgia", Font.PLAIN, 15));
		
		
		nrMembers.setBounds(292, 268, 70, 23);
		contentPane.add(nrMembers);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel.setBounds(10, 11, 202, 105);
		contentPane.add(panel);
		panel.setLayout(null);
		
		nametextField = new JTextField();
		nametextField.setBounds(10, 29, 180, 20);
		panel.add(nametextField);
		nametextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(62, 11, 67, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add family member");
		btnNewButton.setBackground(new Color(123, 104, 238));
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertActionListener(e);
				try {
					viewFamilyMembers(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 60, 180, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setForeground(Color.WHITE);
		panel_1.setLayout(null);
		panel_1.setBounds(10, 127, 202, 105);
		contentPane.add(panel_1);
		
		idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setBounds(10, 29, 180, 20);
		panel_1.add(idTextField);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(75, 11, 42, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnRemoveFamilyMember = new JButton("Remove family member");
		btnRemoveFamilyMember.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnRemoveFamilyMember.setForeground(new Color(255, 255, 255));
		btnRemoveFamilyMember.setBackground(new Color(123, 104, 238));
		btnRemoveFamilyMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					deleteActionListener(e);
			        viewFamilyMembers(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemoveFamilyMember.setBounds(10, 60, 180, 23);
		panel_1.add(btnRemoveFamilyMember);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Georgia", Font.PLAIN, 13));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(123, 104, 238));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnBack.setBounds(10, 270, 70, 23);
		contentPane.add(btnBack);
	}
	
	
	private void viewFamilyMembers(ActionEvent e) throws Exception{
		try {
			Statement stmt = MySQLConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * FROM members");
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs = stmt.getResultSet();
			stmt.close();
			nrMembers.setText(String.valueOf(num()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void insertActionListener(ActionEvent e){
		String adduserSQL = "INSERT INTO members(`name`) VALUES (?)";
		String name = nametextField.getText();
			try {
				PreparedStatement psAdd = MySQLConnection.getConnection().prepareStatement(adduserSQL);
					psAdd.setString(1, name);
					//succesLabel.setText("Car inserted succesfully");
				    psAdd.executeUpdate();
				    psAdd.close();
				    nametextField.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
			} catch (Exception e1) {
						// TODO Auto-generated catch block
						//succesLabel.setText("Wrong Input");
						e1.printStackTrace();
					}
		}
	
	private void deleteActionListener (ActionEvent e) throws Exception
	{
		String deleteuserSQL = "delete from members where idmember = ?";
		String deleteIncomes = "delete from income where `Titular's ID` = ?";
		String deleteExpense = "delete from expense where `Titular's ID` = ?";
		String id = idTextField.getText();
			try {
				PreparedStatement psDelete = MySQLConnection.getConnection().prepareStatement(deleteuserSQL);
				psDelete.setString(1, id);
				psDelete.executeUpdate();
		        psDelete.close();
		        
		        PreparedStatement psDeleteIncome = MySQLConnection.getConnection().prepareStatement(deleteIncomes);
		        psDeleteIncome.setString(1, id);
		        psDeleteIncome.executeUpdate();
		        psDeleteIncome.close();
		        
		        PreparedStatement psDeleteExpense = MySQLConnection.getConnection().prepareStatement(deleteExpense);
		        psDeleteExpense.setString(1, id);
		        psDeleteExpense.executeUpdate();
		        psDeleteExpense.close();
		        
		        idTextField.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
			
			
	}
	
	public int num() throws Exception {

	     Statement stmt = MySQLConnection.getConnection().createStatement();
	     ResultSet rs = stmt.executeQuery("select count(*) from members");
	     
		 while (rs.next()) {
		 return rs.getInt(1);
		 }
		return 0;
    }
	
	private void closeWindow()
	{
		mainWindow.setVisible();
		this.dispose();
	}
	
	
}
