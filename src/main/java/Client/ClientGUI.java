package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class ClientGUI extends JFrame {

	private JPanel contentPane, footerPanel;
	private JTextField messTextField;
	private JButton connectButton, disconnectButton, senButton;
	private JTextPane messageTextPane;
	private JTextField roomTextField;
	private JLabel lblNameRoom;
	String nameUser;
	private ClientChat client;
	MessageRoom frm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
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
	public ClientGUI() {
		initUI();
		actionButton();
		
	}

	private void connectClient() {
		try {
			client = new ClientChat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		System.out.println("Start sending messages ... ");
		try {
			TextMessage msg = client.getSession().createTextMessage(messTextField.getText());
			client.getProducer().send(msg);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void actionButton() {
		// TODO Auto-generated method stub
		connectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enableConnectUIPanel();
				try {
					connectClient();
					nameUser = roomTextField.getText().trim();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		disconnectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				disableConnectUIPanel();

				try {

					client.closeConnecting();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		senButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sendMessage();
				messTextField.setText(null);
				messTextField.requestFocus();
			}

		});
	}

	private void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		footerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) footerPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(footerPanel, BorderLayout.SOUTH);

		roomTextField = new JTextField();
		roomTextField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		footerPanel.add(roomTextField);
		roomTextField.setColumns(10);
		lblNameRoom = new JLabel("Name of User");
		lblNameRoom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		footerPanel.add(lblNameRoom);

		connectButton = new JButton("Connect");
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		footerPanel.add(connectButton);

		disconnectButton = new JButton("Disconnect");
		disconnectButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		footerPanel.add(disconnectButton);

		messTextField = new JTextField();
		messTextField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		messTextField.setColumns(30);
		footerPanel.add(messTextField);

		senButton = new JButton("Send");
		senButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		footerPanel.add(senButton);
		frm = new MessageRoom();
		
		contentPane.add(frm, BorderLayout.CENTER);
		frm.setVisible(true);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		disableConnectUIPanel();

	}

	private void enableConnectUIPanel() {

		lblNameRoom.setVisible(false);
		connectButton.setVisible(false);
		roomTextField.setVisible(false);
		disconnectButton.setVisible(true);
		messTextField.setVisible(true);
		senButton.setVisible(true);
	}

	private void disableConnectUIPanel() {
		connectButton.setVisible(true);
		connectButton.setVisible(true);
		roomTextField.setVisible(true);
		disconnectButton.setVisible(false);
		messTextField.setVisible(false);
		senButton.setVisible(false);
	}

	private void appendToPane(JTextPane tp, String msg) {
		HTMLDocument doc = (HTMLDocument) tp.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
		try {
			editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
			tp.setCaretPosition(doc.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
