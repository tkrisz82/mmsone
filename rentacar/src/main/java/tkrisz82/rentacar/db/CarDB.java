package tkrisz82.rentacar.db;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tkrisz82.rentacar.model.Car;

public class CarDB {

	private SessionFactory sessionFactory;

	public CarDB() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public Car getCarById(int carId) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Car car = session.get(Car.class, carId);

		ta.commit();
		session.close();

		return car;
	}
	
	public Car getCarByPLate(String plate) {
		
		Car car = null;
		
		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT c FROM Car c WHERE c.plate = :plate");
		query.setParameter("plate", plate);
		
		List<Car> carList = query.getResultList();

		if(!carList.isEmpty()) {
			car = carList.get(0);
		}
		
		ta.commit();
		session.close();

		return car;
		
		
	}

	public List<Car> getAllCars() {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT c FROM Car c");
		List<Car> carList = query.getResultList();

		ta.commit();
		session.close();

		return carList;
	}

	public List<Car> getAllNotBrokenCars() {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT c FROM Car c WHERE c.broken = :broken");
		query.setParameter("broken", 0);
		List<Car> carList = query.getResultList();

		ta.commit();
		session.close();

		return carList;
	}
	
	public List<Car> getAllBrokenCars() {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("SELECT c FROM Car c WHERE c.broken = :broken");
		query.setParameter("broken", 1);
		List<Car> carList = query.getResultList();

		ta.commit();
		session.close();

		return carList;
	}

	public void saveCar(Car car) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.save(car);

		ta.commit();
		session.close();

	}

	public void updateCar(Car car) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.update(car);

		ta.commit();
		session.close();

	}
	
	public void deleteCar(Car car) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		session.delete(car);

		ta.commit();
		session.close();

	}
	
	public void deleteCarByPlate(String plate) {

		Session session = sessionFactory.openSession();
		Transaction ta = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM Car c WHERE c.plate = :plate");
		query.setParameter("plate", plate);
		query.executeUpdate();

		ta.commit();
		session.close();

	}
	
	
	

	public void close() {
		sessionFactory.close();
	}
}
