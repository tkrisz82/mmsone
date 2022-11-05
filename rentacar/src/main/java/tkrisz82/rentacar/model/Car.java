package tkrisz82.rentacar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "plate")
	private String plate;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "broken")
	private int broken;
	
	public Car() {
		
	}
	
	public Car(String model, String plate, int price) {
		this.id = 0;
		this.model = model;
		this.plate = plate;
		this.price = price;
		this.broken = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBroken() {
		return broken;
	}

	public void setBroken(int broken) {
		this.broken = broken;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", plate=" + plate + ", price=" + price + ", broken=" + broken
				+ "]";
	}
	
	
	
	
}
