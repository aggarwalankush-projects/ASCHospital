package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddLogin {

	private static final String PERSISTENCE_UNIT_NAME = "ASCHospital";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Create new user
		em.getTransaction().begin();
		Login login = new Login();
		login.setUserName("admin");
		login.setPassword("pass4admin");
		em.persist(login);

		em.getTransaction().commit();

		em.close();
	}
}
