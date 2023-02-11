package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	public SessionFactory sessionFactory;
	public Session session;
	
	public HibernateUtil() {
		initHibernate();
	}
	
	private void initHibernate() {
		System.out.println("- Iniciando sesion hibernate");
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public Session reiniciarSession() {
		System.out.println("- Reiniciando sesion hibernate");
		session.flush();
		session.close();
		
		session = sessionFactory.openSession();
		
		return session;
	}
	
	public Session getSession() {
		if (session == null)
			session = sessionFactory.openSession();
		
		return session;
	}
	
	public void cerrarSession() {
		session.close();
	}
}
