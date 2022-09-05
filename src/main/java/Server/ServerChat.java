package Server;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.BasicConfigurator;

import data.Person;

public class ServerChat {

	// thiết lập môi trường cho JJNDI
	Properties settings = new Properties();
	Session session;
	Destination destination;
	javax.jms.Connection con;
	// tạo producer
	MessageProducer producer;
	Context ctx;
	Object obj;
	ConnectionFactory factory;
	public static ServerChat instance;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

	public ServerChat() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		BasicConfigurator.configure();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		// tạo context
		ctx = new InitialContext(settings);
		// lookup JMS connection factory
		obj = ctx.lookup("TopicConnectionFactory");
		factory = (ConnectionFactory) obj;
		// tạo connection
		con = factory.createConnection("admin", "admin");
		// nối đến MOM
		con.start();
		// tạo session
		session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
	}

	public static void main(String[] args) throws Exception {
		// thiết lập môi trường cho JMS logging
		
		try {
			instance = new ServerChat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect() throws Exception {

	}

	public void sendMessage(String mess, Person p) throws Exception {

		destination = (Destination) ctx.lookup("dynamicTopics/" + p.getHoten());
		// tạo producer
		producer = session.createProducer(destination);
		// Tạo 1 message
		Message msg = session.createTextMessage(p.getHoten() + " : " + mess);
		// gửi
		producer.send(msg);
		// shutdown connection

	}

	public void closeMess() throws JMSException {
		session.close();
		con.close();
		System.out.println("Finished...");
	}

}
