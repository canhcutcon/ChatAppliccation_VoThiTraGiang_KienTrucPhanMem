package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class MessageRoom extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	private ServerChat server;
	private JTextPane messageTextPane;
	String nameUser = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					MessageRoom frame = new MessageRoom();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MessageRoom() {

		setFocusCycleRoot(true);
		setFocusable(true);
		setFocusCycleRoot(true);
		getContentPane().setEnabled(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		messageTextPane = new JTextPane();
		messageTextPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		messageTextPane.setText("aa");
		messageTextPane.setContentType("text/html");
		messageTextPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
		getContentPane().add(messageTextPane, BorderLayout.CENTER);
		connectToServer();
		try {
			receivedMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connectToServer() {
		try {
			server = new ServerChat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receivedMessage() throws Exception {
		MessageConsumer receiver = server.getSession().createConsumer(server.getDestination());
		receiver.setMessageListener(new MessageListener() {
			public void onMessage(Message msg) {
				try {
					if (msg instanceof TextMessage) {
						TextMessage tm = (TextMessage) msg;
						String txt = tm.getText();
						System.out.println(txt);
						msg.acknowledge();
						appendToPane(messageTextPane, txt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void receivedMessage(String name) throws Exception {
		MessageConsumer receiver = server.getSession().createConsumer(server.getDestination());
		receiver.setMessageListener(new MessageListener() {
			public void onMessage(Message msg) {
				try {
					if (msg instanceof TextMessage) {
						TextMessage tm = (TextMessage) msg;
						String txt = nameUser + " : " + tm.getText();
						System.out.println(txt);
						msg.acknowledge();
						appendToPane(messageTextPane, txt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

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
