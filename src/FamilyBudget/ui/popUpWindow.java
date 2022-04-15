package FamilyBudget.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class popUpWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnYes = new JButton("Yes");
	JButton btnNo = new JButton("No");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					popUpWindow frame = new popUpWindow();
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
	public popUpWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res\\logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 115);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        btnYes.setFont(new Font("Georgia", Font.PLAIN, 14));
        btnYes.setBackground(new Color(123, 104, 238));
        btnYes.setForeground(Color.WHITE);
		
        btnYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnYes(e);
            }
        });
        btnNo.setForeground(new Color(255, 255, 255));
        btnNo.setBackground(new Color(123, 104, 238));
        btnNo.setFont(new Font("Georgia", Font.PLAIN, 14));
        
      
        btnNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNo(e);
            }
        });
        contentPane.setLayout(null);
        
        btnNo.setBounds(170, 32, 101, 33);
        contentPane.add(btnNo);
        btnYes.setBounds(37, 32, 101, 33);
        contentPane.add(btnYes);
        
        JLabel lblNewLabel = new JLabel("Are you sure you want to exit the application?");
        lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 13));
        lblNewLabel.setBounds(10, 0, 298, 23);
        contentPane.add(lblNewLabel);
	}
	
	private void btnNo(ActionEvent e) {
        this.dispose();    
    }


    private void btnYes(ActionEvent e) {
         System.exit(0);   
    }
}
