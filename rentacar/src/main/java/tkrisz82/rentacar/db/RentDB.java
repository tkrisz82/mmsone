package tkrisz82.rentacar.db;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tkrisz82.rentacar.model.Car;
import tkrisz82.rentacar.model.Rent;

public class RentDB {

	private SessionFactory sessionFactory;

	public RentDB() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	
	
	
	public List<Rent> getRentByCarId(int carId) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT r FROM Rent r WHERE r.carId = :carid");
		query.setParameter("carid", carId);

		List<Rent> rentList = query.getResultList();

		ta.commit();
		session.close();

		return rentList;

	}
	
	public List<Rent> getAllRentInAnIntervallByCarId(Date start, Date end, int carId){
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

	
		
		Query query = session.createQuery("SELECT r FROM Rent r WHERE "
				+ "((r.startDate >= :start AND r.startDate <= :end) OR (r.finishDate >= :start AND r.finishDate <= :end)) "
				+ " AND r.carId = :carId");
		
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("carId", carId);
		
		List<Rent> rentList = query.getResultList();
		
		
		ta.commit();
		session.close();
		
		
		return rentList;
	}
	
	public List<Rent> getAllRentInAnIntervallOrderedById(Date start, Date end){
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

	
		
		Query query = session.createQuery("SELECT r FROM Rent r WHERE "
				+ "(r.startDate >= :start AND r.startDate <= :end) OR (r.finishDate >= :start AND r.finishDate <= :end) "
				+ "ORDER BY r.carId");
		
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		List<Rent> rentList = query.getResultList();
		
		
		ta.commit();
		session.close();
		
		
		return rentList;
		
		
	}
	
	public List<Rent> getAllRent(){
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

	
		
		Query query = session.createQuery("SELECT r FROM Rent r ORDER BY r.carId");
		
		List<Rent> rentList = query.getResultList();
		
		
		ta.commit();
		session.close();
		
		
		return rentList;
	}

	public void saveRent(Rent rent) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.save(rent);

		ta.commit();
		session.close();

	}

	public void updateRent(Rent rent) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.update(rent);

		ta.commit();
		session.close();

	}
	
	public void deleteRentByCarId(int carId) {
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM Rent r WHERE r.carId = :carid");
		query.setParameter("carid", carId);
		query.executeUpdate();

		ta.commit();
		session.close();
	}
	
	public void deleteRentByrentId(int rentId) {
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM Rent r WHERE r.id = :rentid");
		query.setParameter("rentid", rentId);
		query.executeUpdate();

		ta.commit();
		session.close();
	}
	
	

	public void close() {
		sessionFactory.close();
	}
}
