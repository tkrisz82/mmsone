package tkrisz82.rentacar.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rent")
public class Rent {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "startdate")
	private Date startDate;
	
	@Column(name = "finishdate")
	private Date finishDate;
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "carid")
	private int carId;
	
	@Column(name = "comment")
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Rent [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + ", userId=" + userId
				+ ", carId=" + carId + ", comment=" + comment + "]";
	}
	
	
}
