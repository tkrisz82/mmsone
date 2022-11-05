package tkrisz82.rentacar.db;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tkrisz82.rentacar.model.Rent;
import tkrisz82.rentacar.model.User;

public class UserDB {

	private SessionFactory sessionFactory;

	public UserDB() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public User getUserById(int userId) {

		Session session = sessionFactory.openSession();

		Transaction ta = session.beginTransaction();

		User user = session.get(User.class, userId);

		ta.commit();
		session.close();

		return user;
	}

	public User getUserByEmailAndPwd(String email, String pwd) {

		User user = null;

		Session session = sessionFactory.openSession();

		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.pwd = :pwd");
		query.setParameter("email", email);
		query.setParameter("pwd", pwd);

		List<User> userList = query.getResultList();

		if (!userList.isEmpty()) {
			user = userList.get(0);
		}

		ta.commit();
		session.close();

		return user;
	}

	public void saveUser(User user) {

		Session session = sessionFactory.openSession();

		Transaction ta = session.beginTransaction();

		session.save(user);

		ta.commit();
		session.close();

	}

	public void updateUser(User user) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.update(user);

		ta.commit();
		session.close();

	}

	public void close() {
		sessionFactory.close();
	}

}
