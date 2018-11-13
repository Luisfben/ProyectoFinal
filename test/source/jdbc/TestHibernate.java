package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import entity.Niveles;

import entity.Niveles;

public class TestHibernate {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Niveles.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Niveles nivel = session.get(Niveles.class, 1);
			System.out.println(nivel.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tx.commit();
			factory.close();
		}

	}

}
