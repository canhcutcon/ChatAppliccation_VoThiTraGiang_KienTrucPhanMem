package Client2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.BasicConfigurator;

import Server.ServerChat;
import data.Person;
import helper.XMLConvert;

public class ChatClient {
	final JTextPane jtextFilDiscu = new JTextPane();
	final JTextPane jtextListUsers = new JTextPane();
	final JTextField jtextInputChat = new JTextField();
	final JFrame jfr = new JFrame("Chat");
	private String name;
	Properties settings;
	ServerChat sev = new ServerChat();
	Person p;

	public ChatClient(String name) throws Exception {
		this.name = name;
		
//		// thiết lập môi trư�?ng cho JMS
//		BasicConfigurator.configure();
//		// thiết lập môi trư�?ng cho JJNDI
//		settings = new Properties();
//		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
//		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
//		// =======
//		// tạo context
//		final Context ctx = new InitialContext(settings);
//		// lookup JMS connection factory
//		Object obj = ctx.lookup("TopicConnectionFactory");
//		ConnectionFactory factory = (ConnectionFactory) obj;
//		// tạo connection
//		Connection con = factory.createConnection("admin", "admin");
//		// nối đến MOM
//		con.start();
//		// tạo session
//		final Session session = con.createSession(/* transaction */false, /* ACK */Session.CLIENT_ACKNOWLEDGE);
//		// tạo consumer
		
		String fontfamily = "Arial, sans-serif";
		Font font = new Font(fontfamily, Font.PLAIN, 15);

		jfr.getContentPane().setLayout(null);
		jfr.setSize(700, 500);
		jfr.setResizable(false);
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Module du fil de discussion
		jtextFilDiscu.setBounds(25, 25, 490, 320);
		jtextFilDiscu.setFont(font);
		jtextFilDiscu.setMargin(new Insets(6, 6, 6, 6));
		jtextFilDiscu.setEditable(false);
		JScrollPane jtextFilDiscuSP = new JScrollPane(jtextFilDiscu);
		jtextFilDiscuSP.setBounds(25, 25, 490, 320);

		jtextFilDiscu.setContentType("text/html");
		jtextFilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

		// Module de la liste des utilisateurs
		jtextListUsers.setBounds(520, 25, 156, 320);
		jtextListUsers.setEditable(true);
		jtextListUsers.setFont(font);
		jtextListUsers.setMargin(new Insets(6, 6, 6, 6));
		jtextListUsers.setEditable(false);
		JScrollPane jsplistuser = new JScrollPane(jtextListUsers);
		jsplistuser.setBounds(520, 25, 156, 320);

		jtextListUsers.setContentType("text/html");
		jtextListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

		// Field message user input
		jtextInputChat.setBounds(0, 350, 400, 50);
		jtextInputChat.setFont(font);
		jtextInputChat.setMargin(new Insets(6, 6, 6, 6));
		final JScrollPane jtextInputChatSP = new JScrollPane(jtextInputChat);
		jtextInputChatSP.setBounds(25, 350, 650, 50);

		// button send
		final JButton jsbtn = new JButton("Send");
		
		final JButton buttonClient = new JButton("Clien");
		buttonClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ChatClient window;
					try {
						window = new ChatClient("abmin2");
						window.jfr.setVisible(true);
						window.jfr.setLocationRelativeTo(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		jsbtn.setFont(font);
		jsbtn.setBounds(575, 410, 100, 35);
		// Connection view
		final JTextField jtfName = new JTextField(name);
		final JTextField jtfport = new JTextField(Integer.toString(300));
		final JTextField jtfAddr = new JTextField("Hos");
		final JButton jcbtn = new JButton("Connect");
		// button Disconnect
		final JButton jsbtndeco = new JButton("Disconnect");
		jsbtndeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					sev.closeMess();
					jfr.add(jtfName);
					jfr.add(jtfport);
					jfr.add(jtfAddr);
					jfr.add(jcbtn);
					jfr.remove(jsbtn);
					jfr.remove(jtextInputChatSP);
					jfr.remove(jsbtndeco);
					jfr.remove(buttonClient);
					jfr.revalidate();
					jfr.repaint();
					jtextListUsers.setText(null);
					jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
					jtextListUsers.setBackground(Color.LIGHT_GRAY);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		jsbtndeco.setFont(font);
		jsbtndeco.setBounds(25, 410, 130, 35);

		// Click on send button
		jsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String message = jtextInputChat.getText().trim();
					if (message.equals("")) {
						return;
					}
					sev.sendMessage(jtextInputChat.getText(), p);
					jtextInputChat.requestFocus();
					jtextInputChat.setText(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		

		jcbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				p = new Person(1001, jtfName.getText(), new Date());
				try {
					String xml = new XMLConvert<Person>(p).object2XML(p);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// tạo consumer
				Destination destination;
				try {
					destination = (Destination) sev.getCtx().lookup("dynamicTopics/" + p.getHoten());
					try {
						MessageConsumer receiver = sev.getSession().createConsumer(destination);
						receiver.setMessageListener(new MessageListener() {

							// có message đến queue, phương thức này được thực thi
							public void onMessage(Message msg) {// msg là message nhận được
								try {
									if (msg instanceof TextMessage) {
										TextMessage tm = (TextMessage) msg;
										String txt = tm.getText();
										System.out.println("XML= " + txt);
										msg.acknowledge();// gửi tín hiệu ack
										appendToPane(jtextFilDiscu, p.getHoten() + txt);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

						jfr.remove(jtfName);
						jfr.remove(jtfport);
						jfr.remove(jtfAddr);
						jfr.remove(jcbtn);
						jfr.add(jsbtn);
						jfr.add(jtextInputChatSP);
						jfr.add(jsbtndeco);
						jfr.add(buttonClient);
						jfr.revalidate();
						jfr.repaint();
						jtextFilDiscu.setBackground(Color.WHITE);
						jtextListUsers.setBackground(Color.WHITE);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		jcbtn.setFont(font);
		jtfAddr.setBounds(25, 380, 135, 40);
		jtfName.setBounds(375, 380, 135, 40);
		jtfport.setBounds(200, 380, 135, 40);
		jcbtn.setBounds(575, 380, 100, 40);
		buttonClient.setBounds(575, 380, 100, 40);
		
		jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
		jtextListUsers.setBackground(Color.LIGHT_GRAY);

		jfr.add(jcbtn);
		jfr.add(jtextFilDiscuSP);
		jfr.add(jsplistuser);
		jfr.add(jtfName);
		jfr.add(jtfport);
		jfr.add(jtfAddr);
		jfr.setVisible(true);
	}

	// send html to pane
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
