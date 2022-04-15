package FamilyBudget.ui;
import FamilyBudget.bussines.ReadPropertyFile;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class mainWindow {

	private static JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo.png"));
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 237, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(123, 104, 238));
		panel.setBounds(0, 0, 221, 96);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Family Budget");
		lblNewLabel.setBounds(26, 11, 168, 33);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		
		JLabel familyName = new JLabel("");
		familyName.setHorizontalAlignment(SwingConstants.CENTER);
		familyName.setForeground(Color.WHITE);
		familyName.setFont(new Font("Georgia", Font.PLAIN, 18));
		familyName.setBounds(26, 55, 168, 30);
		panel.add(familyName);
		familyName.setText("Family: " + (ReadPropertyFile.getProp("familyName")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(123, 104, 238), 4, true));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 52, 221, 273);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		// button for config
		JButton btnConf = new JButton("Configuration");
		btnConf.setBounds(40, 58, 140, 47);
		panel_1.add(btnConf);
		btnConf.setFont(new Font("Georgia", Font.BOLD, 14));
		btnConf.setForeground(new Color(255, 255, 255));
		btnConf.setBackground(new Color(123, 104, 238));
		
		
		// button for operation
		JButton btnOp = new JButton("Operational");
		btnOp.setBounds(40, 133, 140, 47);
		panel_1.add(btnOp);
		btnOp.setFont(new Font("Georgia", Font.BOLD, 14));
		btnOp.setForeground(new Color(255, 255, 255));
		btnOp.setBackground(new Color(123, 104, 238));
		
		// button for exit 
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(40, 210, 140, 47);
		panel_1.add(btnExit);
		btnExit.setFont(new Font("Georgia", Font.BOLD, 14));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(123, 104, 238));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit(e);
			}
		});
		btnOp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				operationWindowOpen();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configurationWindowOpen();
				
			}
		});
		
		
	}
	
	private void btnExit(ActionEvent e) {
        new popUpWindow().setVisible(true);

    }
	
	private void operationWindowOpen()
	{
		new operationWindow().setVisible(true);
		frame.dispose();
	}
	
	
	private void configurationWindowOpen()
	{
		new configWindow().setVisible(true);
		frame.dispose();
	}

	public static void setVisible() {
		frame.setVisible(true);
	}
	
	public static void functie() {
		
	}
}
