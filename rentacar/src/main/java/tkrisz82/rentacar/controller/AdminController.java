package tkrisz82.rentacar.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import tkrisz82.rentacar.db.CarDB;
import tkrisz82.rentacar.db.RentDB;
import tkrisz82.rentacar.db.UserDB;
import tkrisz82.rentacar.model.Car;
import tkrisz82.rentacar.model.User;

@Controller
public class AdminController {

	@PostMapping("/addnewcar")
	public String addNewCar(Model model, @RequestParam(name = "model") String mod,
											@RequestParam(name = "plate") String plate,
											@RequestParam(name = "price") int price,
											@RequestParam(name = "userid") int userId) {
		
		UserDB udb = new UserDB();
		
		User user = udb.getUserById(userId);
				
		CarDB cdb = new CarDB();
		
		Car car = new Car(mod, plate, price);
		
		cdb.saveCar(car);
		
		model.addAttribute("user", user);
		
		model.addAttribute("allCars", cdb.getAllCars());
		
		model.addAttribute("brokenCars", cdb.getAllBrokenCars());
		
		model.addAttribute("registration", "Sikeres regisztráció!");
		
		return "admin.html";
	}
	
	
	@PostMapping("/deletecar")
	public String deleteCar(Model model, @RequestParam(name = "plate") String plate,
											@RequestParam(name = "userid") int userId) {
		RentDB rdb = new RentDB();
		
		UserDB udb = new UserDB();
		
		User user = udb.getUserById(userId);
				
		CarDB cdb = new CarDB();
		
		Car car = cdb.getCarByPLate(plate);
		
		
		rdb.deleteRentByCarId(car.getId());
		
		cdb.deleteCarByPlate(plate);
		
		
		
		model.addAttribute("user", user);
		
		model.addAttribute("allCars", cdb.getAllCars());
		
		model.addAttribute("brokenCars", cdb.getAllBrokenCars());
		
		model.addAttribute("delete", "Sikeres törlés!");
		
		return "admin.html";
	}
	
	@PostMapping("/report")
	public String report(Model model, @RequestParam(name = "userid") int userId,
										@RequestParam(name = "start") Date start,
										@RequestParam(name = "end") Date end,
										@RequestParam(name = "carid") int carId) {
		
		UserDB udb = new UserDB();
		
		User user = udb.getUserById(userId);
		
		CarDB cdb = new CarDB();
		Car car = cdb.getCarById(carId);
		
		RentDB rdb = new RentDB();
		
		if(carId == 0) {
			
			model.addAttribute("user", user);
				
			model.addAttribute("allCars", cdb.getAllCars());
				
			model.addAttribute("brokenCars", cdb.getAllBrokenCars());
				
			model.addAttribute("reportCars", rdb.getAllRentInAnIntervallOrderedById(start, end));
				
		}
		else {
			
			model.addAttribute("user", user);
			
			model.addAttribute("allCars", cdb.getAllCars());
			
			model.addAttribute("brokenCars", cdb.getAllBrokenCars());
			
			model.addAttribute("reportCars", rdb.getAllRentInAnIntervallByCarId(start, end, carId));
			
			model.addAttribute("reportedcar", car.getModel() + " " + car.getPlate());
			
		}
		
		
		
		return "admin.html";
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
	   
		return "ex.html";
		
	}
	
}
