package FamilyBudget.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FamilyBudget.connection.MySQLConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class selectedUserWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTable table = new JTable();
	static JTable tableExpense = new JTable();
	private JPanel contentPane;
	JLabel nameLabel = new JLabel("New label");
	JLabel totalIncomeField; 
	JLabel totalExpenseField; 
	JLabel remainingbudget = new JLabel("");
	private JTextField incomeTextField;
	private int idTitular = operationWindow.getId();
	private JTextField expenseField;
	private JTextField detailsIncomeField;
	private JTextField detailsExpenseField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectedUserWindow frame = new selectedUserWindow();
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
	public selectedUserWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 749, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel.setForeground(Color.BLACK);
		panel.setBounds(10, 66, 265, 132);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Income");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel.setBounds(30, 11, 225, 23);
		panel.add(lblNewLabel);
		
		incomeTextField = new JTextField();
		incomeTextField.setBounds(66, 38, 169, 20);
		panel.add(incomeTextField);
		incomeTextField.setColumns(10);
		
		JButton addIncomebtn = new JButton("Add");
		addIncomebtn.setForeground(new Color(255, 255, 255));
		addIncomebtn.setFont(new Font("Georgia", Font.PLAIN, 12));
		addIncomebtn.setBackground(new Color(123, 104, 238));
		addIncomebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addIncome();
				generateTables();
			}
		});
		addIncomebtn.setBounds(154, 100, 81, 23);
		panel.add(addIncomebtn);
		
		detailsIncomeField = new JTextField();
		detailsIncomeField.setColumns(10);
		detailsIncomeField.setBounds(66, 69, 169, 20);
		panel.add(detailsIncomeField);
		
		JLabel lblNewLabel_2 = new JLabel("Value:");
		lblNewLabel_2.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 40, 52, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Details:");
		lblNewLabel_3.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 72, 52, 14);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 209, 713, 290);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Reporting");
		btnNewButton.setBackground(new Color(123, 104, 238));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(192, 11, 309, 33);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateTables();
				calculateBudget();
			}
		});
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Incomes");
		lblNewLabel_1.setBounds(64, 42, 130, 33);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		JScrollPane scrollPaneIncome = new JScrollPane();
		scrollPaneIncome.setBorder(new LineBorder(new Color(123, 104, 238), 2, true));
		scrollPaneIncome.setFont(new Font("Georgia", Font.PLAIN, 11));
		scrollPaneIncome.setBounds(32, 75, 205, 205);
		scrollPaneIncome.getViewport().setBackground(Color.WHITE);
		panel_1.add(scrollPaneIncome);
		table.setSelectionBackground(new Color(123, 104, 238));
		table.setFont(new Font("Georgia", Font.PLAIN, 11));
		table.setGridColor(new Color(123, 104, 238));
		table.setBackground(Color.WHITE);
		table.setDefaultEditor(Object.class, null);
		scrollPaneIncome.setViewportView(table);
		
		JScrollPane scrollPaneExpense = new JScrollPane();
		scrollPaneExpense.setBorder(new LineBorder(new Color(123, 104, 238), 2, true));
		scrollPaneExpense.setBounds(477, 75, 205, 205);
		scrollPaneExpense.getViewport().setBackground(Color.WHITE);
		panel_1.add(scrollPaneExpense);
		tableExpense.setFont(new Font("Georgia", Font.PLAIN, 11));
		tableExpense.setSelectionBackground(new Color(123, 104, 238));
		tableExpense.setGridColor(new Color(123, 104, 238));
		tableExpense.setBackground(Color.WHITE);
		tableExpense.setDefaultEditor(Object.class, null);
		scrollPaneExpense.setViewportView(tableExpense);
		
		JLabel lblNewLabel_1_1 = new JLabel("Expenses");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(515, 42, 130, 33);
		panel_1.add(lblNewLabel_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(123, 104, 238), 2));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(255, 75, 205, 205);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Aggregated statistics");
		lblNewLabel_5.setBounds(35, 5, 135, 19);
		panel_3.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("Remaining Budget");
		lblNewLabel_4.setBounds(10, 139, 178, 33);
		panel_3.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		remainingbudget.setBounds(50, 164, 100, 33);
		panel_3.add(remainingbudget);
		
		
		remainingbudget.setHorizontalAlignment(SwingConstants.CENTER);
		remainingbudget.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		totalIncomeField = new JLabel("");
		totalIncomeField.setHorizontalAlignment(SwingConstants.CENTER);
		totalIncomeField.setFont(new Font("Georgia", Font.PLAIN, 13));
		totalIncomeField.setBounds(0, 35, 205, 25);
		panel_3.add(totalIncomeField);
		
		totalExpenseField = new JLabel("");
		totalExpenseField.setHorizontalAlignment(SwingConstants.CENTER);
		totalExpenseField.setFont(new Font("Georgia", Font.PLAIN, 13));
		totalExpenseField.setBounds(0, 71, 205, 24);
		panel_3.add(totalExpenseField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel_2.setLayout(null);
		panel_2.setBounds(458, 66, 265, 132);
		contentPane.add(panel_2);
		
		JLabel lblAddExpense = new JLabel("Add Expense");
		lblAddExpense.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddExpense.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblAddExpense.setBounds(30, 11, 225, 23);
		panel_2.add(lblAddExpense);
		
		expenseField = new JTextField();
		expenseField.setColumns(10);
		expenseField.setBounds(66, 38, 169, 20);
		panel_2.add(expenseField);
		
		JButton addIncomebtn_1 = new JButton("Add");
		addIncomebtn_1.setBackground(new Color(123, 104, 238));
		addIncomebtn_1.setForeground(new Color(255, 255, 255));
		addIncomebtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addExpense();
				generateTables();
			}
		});
		addIncomebtn_1.setBounds(156, 98, 81, 23);
		panel_2.add(addIncomebtn_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Value:");
		lblNewLabel_2_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(10, 41, 52, 14);
		panel_2.add(lblNewLabel_2_1);
		
		detailsExpenseField = new JTextField();
		detailsExpenseField.setColumns(10);
		detailsExpenseField.setBounds(66, 69, 169, 20);
		panel_2.add(detailsExpenseField);
		
		JLabel lblNewLabel_3_1 = new JLabel("Details:");
		lblNewLabel_3_1.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblNewLabel_3_1.setBounds(10, 72, 52, 14);
		panel_2.add(lblNewLabel_3_1);
		
		JButton backBtn = new JButton("Back");
		backBtn.setBackground(new Color(123, 104, 238));
		backBtn.setForeground(new Color(255, 255, 255));
		backBtn.setFont(new Font("Georgia", Font.PLAIN, 11));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		backBtn.setBounds(10, 504, 89, 23);
		contentPane.add(backBtn);
		
		JButton btnNewButton_1 = new JButton("Reset monthly");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Georgia", Font.PLAIN, 12));
		btnNewButton_1.setBackground(new Color(123, 104, 238));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetMonthly();
			}
		});
		btnNewButton_1.setBounds(599, 504, 124, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(123, 104, 238));
		panel_4.setBounds(0, 0, 733, 48);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		nameLabel.setBounds(205, 11, 319, 24);
		panel_4.add(nameLabel);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setBackground(new Color(123, 104, 238));
		nameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
		runSelect();
	}
	
	private void runSelect() {
		String selectSQL = "SELECT `Name` FROM members WHERE idmember = ?";
		int id = operationWindow.getId();
		try {
			
			PreparedStatement psSelect = MySQLConnection.getConnection().prepareStatement(selectSQL);
			psSelect.setInt(1, id);
			ResultSet rs = psSelect.executeQuery();
			rs.next();
			String name =rs.getString("Name");
			nameLabel.setText(name + "'s expenses and incomes");
		
		} catch (SQLException e1) {
			this.dispose();
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addIncome() {
		String adduserSQL = "INSERT INTO income(`Income's titular`, `Titular's ID`, `Value`, `Details`) VALUES (?,?,?,?)";
		String incomeTitular = operationWindow.getTitularName();
		int titularid = operationWindow.getId();
		int income = Integer.parseInt(incomeTextField.getText());
		String details = detailsIncomeField.getText();
		
			try {
				PreparedStatement psAdd = MySQLConnection.getConnection().prepareStatement(adduserSQL);
					psAdd.setString(1, incomeTitular);
					psAdd.setInt(2, titularid);
					psAdd.setInt(3, income);
					psAdd.setString(4, details);
				    psAdd.executeUpdate();
				    psAdd.close();
				    JOptionPane.showMessageDialog(this,"Income Added");
				    incomeTextField.setText("");
				    detailsIncomeField.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
			} catch (Exception e1) {
						// TODO Auto-generated catch block
						//succesLabel.setText("Wrong Input");
						e1.printStackTrace();
					}
	}
	
	private void addExpense() {
		String adduserSQL = "INSERT INTO expense(`Expense's titular`, `Titular's ID`, `Value`, `Details`) VALUES (?,?,?,?)";
		String expenseTitular = operationWindow.getTitularName();
		int titularid = operationWindow.getId();
		int expense = Integer.parseInt(expenseField.getText());
		String details = detailsExpenseField.getText();
		
			try {
				PreparedStatement psAdd = MySQLConnection.getConnection().prepareStatement(adduserSQL);
					psAdd.setString(1, expenseTitular);
					psAdd.setInt(2, titularid);
					psAdd.setInt(3, expense);
					psAdd.setString(4, details);
				    psAdd.executeUpdate();
				    psAdd.close();
				    JOptionPane.showMessageDialog(this,"Expense Added");
				    expenseField.setText("");
				    detailsExpenseField.setText("");
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
			} catch (Exception e1) {
						// TODO Auto-generated catch block
						//succesLabel.setText("Wrong Input");
						e1.printStackTrace();
					}
	}
	private void generateTables() {
		try {
			PreparedStatement stmt = MySQLConnection.getConnection().prepareStatement("select `Value`, `Details` FROM income WHERE `Titular's ID` = ?");
			stmt.setInt(1, idTitular);
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.next();
		
			
			PreparedStatement stmt2 = MySQLConnection.getConnection().prepareStatement("select `Value`, `Details` FROM expense WHERE `Titular's ID` = ?");
			stmt2.setInt(1, idTitular);
			ResultSet rs2 = stmt2.executeQuery();
			tableExpense.setModel(DbUtils.resultSetToTableModel(rs2));
			rs.next();
	
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void calculateBudget() {
		String budgetQuery = "Select SUM(`Value`) as incomeSum from income where `Titular's ID` = ?";
		String expenseQuery = "Select SUM(`Value`) as expenseSum from expense where `Titular's ID` = ?";
		try {
			PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(budgetQuery);
			ps.setInt(1, idTitular);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int budget = rs.getInt("incomeSum");
			
			PreparedStatement ps2 = MySQLConnection.getConnection().prepareStatement(expenseQuery);
			ps2.setInt(1, idTitular);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			int expenses = rs2.getInt("expenseSum");
			
			totalIncomeField.setText("Total income:  " + budget);
			totalExpenseField.setText("Total expenses:  " + expenses);
			int remainingBudgetAux = budget - expenses;
			
			
			remainingbudget.setText(Integer.toString(remainingBudgetAux));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void resetMonthly()
	{
		String deleteIncome = "DELETE from income where `Titular's ID` = ?";
		String deleteExpenses = "DELETE from expense where `Titular's ID` = ?";
		
		try {
			PreparedStatement ps = MySQLConnection.getConnection().prepareStatement(deleteIncome);
			ps.setInt(1, idTitular);
			ps.executeUpdate();
			
			ps = MySQLConnection.getConnection().prepareStatement(deleteExpenses);
			ps.setInt(1, idTitular);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeWindow()
	{
		new operationWindow().setVisible(true);
		this.dispose();
	}
}
