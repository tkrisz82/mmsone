package tkrisz82.rentacar.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import tkrisz82.rentacar.model.Rent;
import tkrisz82.rentacar.model.User;
import tkrisz82.rentacar.services.DateHandler;

@Controller
public class AppController {

	@PostMapping("/rent")
	public String rentACar(Model model, @RequestParam(name = "start") Date start, @RequestParam(name = "end") Date end,
			@RequestParam(name = "userid") int userId, @RequestParam(name = "carid") int carId,
			@RequestParam(name = "comment") String comment) {

		String html = "";

		UserDB udb = new UserDB();
		User user = udb.getUserById(userId);

		if (user.getLogedin() == 1) { // A user belépett-e?

			CarDB cdb = new CarDB();
			Car car = cdb.getCarById(carId);

			if (car.getBroken() == 0) { // Az autó törött-e?

				RentDB rdb = new RentDB();

				List<Rent> rentList = rdb.getRentByCarId(carId);

				boolean rentable = true;

				if (!rentList.isEmpty()) { // Foglalat-e az autó az adott időpontban?

					for (int i = 0; i < rentList.size(); i++) {
						Rent rent = rentList.get(i);
						if ((start.compareTo(rent.getStartDate()) >= 0 && start.compareTo(rent.getFinishDate()) <= 0)
								|| (end.compareTo(rent.getStartDate()) >= 0
										&& end.compareTo(rent.getFinishDate()) <= 0)) {
							rentable = false;
							break;
						}
					}
				}
				// Ha az autó nem foglalt
				if (rentList.isEmpty() || rentable == true) {

					DateHandler dh = new DateHandler();

					Rent rent = new Rent();

					rent.setCarId(carId);
					rent.setId(0);
					rent.setStartDate(start);
					rent.setFinishDate(end);
					rent.setUserId(userId);
					rent.setComment(comment);

					rdb.saveRent(rent);

					long price = dh.getDateDiffPlusOne(start, end) * car.getPrice();

					model.addAttribute("succsess", "Sikeres foglalás!");
					model.addAttribute("price", price);
					model.addAttribute("carList", cdb.getAllNotBrokenCars());
					model.addAttribute("user", user);

					html = "index.html";
				} else {
					model.addAttribute("succsess", "Az autó az adott időintervallumban nem elérhető.");
					model.addAttribute("user", user);
					model.addAttribute("carList", cdb.getAllNotBrokenCars());
					html = "index.html";
				}

			} else {
				model.addAttribute("succsess", "Az autó jelenleg törött!");
				model.addAttribute("carList", cdb.getAllNotBrokenCars());
				model.addAttribute("user", user);

				html = "index.html";
			}

		} else {
			html = "welcome.html";
		}
		return html;
	}
	
	
	@PostMapping("/damage")
	public String damage(Model model, @RequestParam(name = "userid") int userId,
			@RequestParam(name = "plate") String plate) {
		
		
		UserDB udb = new UserDB();
		CarDB cdb = new CarDB();
		
		User user = udb.getUserById(userId);
		Car car = cdb.getCarByPLate(plate);
		
		if(car != null) {
			
			car.setBroken(1);
			
			cdb.updateCar(car);
			
			model.addAttribute("carList", cdb.getAllNotBrokenCars());
	
			model.addAttribute("user", user);
			
			model.addAttribute("price", 0);
			
			model.addAttribute("damage", "Köszönjük az értesítést, kollégánk hamarosan hívni fogja!");
		}
		else {
			model.addAttribute("carList", cdb.getAllNotBrokenCars());
			
			model.addAttribute("user", user);
			
			model.addAttribute("price", 0);
			
			model.addAttribute("damage", "Nem megfelelő a rendszám formátuma, vagy nincs ilyen rendszámú jármű a flottánkban.");
			
		}
		
		
		return "index.html";
	}
	
	@PostMapping("/repaired")
	public String repaired(Model model, @RequestParam(name = "userid") int userId,
			@RequestParam(name = "plate") String plate) {
		
		
		
		UserDB udb = new UserDB();
		CarDB cdb = new CarDB();
		
		User user = udb.getUserById(userId);
		Car car = cdb.getCarByPLate(plate);
		
		if(car != null) {
		
			car.setBroken(0);
			
			cdb.updateCar(car);
		
			model.addAttribute("carList", cdb.getAllNotBrokenCars());
	
			model.addAttribute("user", user);
			
			model.addAttribute("price", 0);
			
			model.addAttribute("repaired", "Köszönjük az értesítést, kollégánk hamarosan hívni fogja!");
		}
		else {
			model.addAttribute("carList", cdb.getAllNotBrokenCars());
			
			model.addAttribute("user", user);
			
			model.addAttribute("price", 0);
			
			model.addAttribute("repaired", "Nem megfelelő a rendszám formátuma, vagy nincs ilyen rendszámú jármű a flottánkban.");
		}
		
		
		return "index.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
