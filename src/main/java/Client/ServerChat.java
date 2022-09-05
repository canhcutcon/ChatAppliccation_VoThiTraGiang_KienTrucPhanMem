package Client;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ServerChat {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination = null;
	private MessageConsumer consumer;

	public ServerChat() throws Exception {
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		System.out.println("Create a ConnectionFactory");
		Context ctx = new InitialContext(settings);
		Object obj = ctx.lookup("TopicConnectionFactory");
		connectionFactory = (ConnectionFactory) obj;

		System.out.println("Create a Connection");
		connection = connectionFactory.createConnection("admin", "admin");
		connection.start();

		System.out.println("Create a Session");
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		System.out.println("Create a Topic/ Queue based on the given parameter");

		destination = session.createQueue("gpcoder-jms-queue");
		destination = session.createTopic("gpcoder-jms-topic");

		System.out.println("Create a Consumer to receive messages from one Topic or Queue.");
		consumer = session.createConsumer(destination);
	}
	
	public void closeConnecting() throws Exception {
		System.out.println("Shutdown JMS connection and free resources");
		connection.close();
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

}
