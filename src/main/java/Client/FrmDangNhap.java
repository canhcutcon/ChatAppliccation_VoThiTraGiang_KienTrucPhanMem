package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmDangNhap extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField taiKhoanText;
	private JButton cancelButton, okButton;
	private JPasswordField passwordText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
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
	public FrmDangNhap() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginUser();
				}
			}
		});
		setFocusable(true);
		initFrameDangNhap();
		setUIFrmDangNhap();

	}

	private void initFrameDangNhap() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 303);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setUndecorated(true);
	}

	private void setUIFrmDangNhap() {
//		LEFT PANEL 
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(Color.WHITE);
		leftPanel.add(logoPanel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("HinhAnh\\Logo\\41973c0e30db4c36924ab8e4a4a9cd33 (1).png"));
		logoPanel.add(lblNewLabel);

		JPanel companyPanel = new JPanel();
		companyPanel.setBackground(new Color(255, 153, 153));
		leftPanel.add(companyPanel);

		JLabel lblNewLabel_1 = new JLabel("GSB Company");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
		companyPanel.add(lblNewLabel_1);

		Component verticalStrut = Box.createVerticalStrut(10);
		verticalStrut.setBackground(Color.WHITE);
		leftPanel.add(verticalStrut);

		JLabel fogotPasswordLabel = new JLabel("Fotgot password?");
		fogotPasswordLabel.setForeground(Color.RED);
		fogotPasswordLabel.setBackground(Color.WHITE);
		fogotPasswordLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		leftPanel.add(fogotPasswordLabel);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		verticalStrut_1.setBackground(Color.WHITE);
		leftPanel.add(verticalStrut_1);

		// ============== Right ===========
		JPanel RightPanel = new JPanel();
		RightPanel.setBackground(Color.WHITE);
		contentPane.add(RightPanel, BorderLayout.CENTER);
		RightPanel.setLayout(new BorderLayout(0, 0));

		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setOpaque(true);
		loginLabel.setBackground(Color.PINK);
		loginLabel.setPreferredSize(new Dimension(31, 80));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 30));
		RightPanel.add(loginLabel, BorderLayout.NORTH);

		JPanel infoLoginPanel = new JPanel();
		infoLoginPanel.setBackground(Color.WHITE);
		RightPanel.add(infoLoginPanel, BorderLayout.CENTER);
		infoLoginPanel.setLayout(new BoxLayout(infoLoginPanel, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(40);
		infoLoginPanel.add(verticalStrut_2);

		JPanel userPanel = new JPanel();
		userPanel.setBackground(Color.WHITE);
		infoLoginPanel.add(userPanel);

		JLabel taiKhoanLabel = new JLabel("Username");
		taiKhoanLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		userPanel.add(taiKhoanLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		userPanel.add(horizontalStrut);

		taiKhoanText = new JTextField("admin");
		taiKhoanText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userPanel.add(taiKhoanText);
		taiKhoanText.setColumns(15);

		JPanel passPanel = new JPanel();
		passPanel.setBackground(Color.WHITE);
		infoLoginPanel.add(passPanel);

		JLabel mkLabel = new JLabel("Password");
		mkLabel.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		mkLabel.setPreferredSize(taiKhoanLabel.getPreferredSize());
		passPanel.add(mkLabel);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		passPanel.add(horizontalStrut_1);

		passwordText = new JPasswordField("admin");;
		passwordText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passPanel.add(passwordText);
		passwordText.setColumns(15);
		
		Component verticalStrut_3 = Box.createVerticalStrut(40);
		infoLoginPanel.add(verticalStrut_3);

		JPanel functionPanel = new JPanel();
		functionPanel.setPreferredSize(new Dimension(10, 50));
		functionPanel.setBackground(Color.WHITE);
		RightPanel.add(functionPanel, BorderLayout.SOUTH);

		cancelButton = new JButton("Cancel");
		cancelButton.setBackground(Color.PINK);
		cancelButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		functionPanel.add(cancelButton);

		okButton = new JButton("OK");
		okButton.setBackground(Color.PINK);
		okButton.setPreferredSize(cancelButton.getPreferredSize());
		okButton.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		functionPanel.add(okButton);

		cancelButton.addActionListener(this);
		okButton.addActionListener(this);
	
	}

	public void close() {
		WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
	}

	

	private void loginUser() {
		String userName = taiKhoanText.getText();
		String password = passwordText.getText();

		if (checkLoginUser(userName,password)) {
			this.setVisible(false);
			ChatClient window;
			try {
				window = new ChatClient(userName);
				window.jfr.setVisible(true);
				window.jfr.setLocationRelativeTo(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private boolean checkLoginUser(String userName, String password) {
		// TODO Auto-generated method stub
		if ( password.equals("admin")) {
			return true;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == okButton)
		{
			loginUser();
		}
	}

}
