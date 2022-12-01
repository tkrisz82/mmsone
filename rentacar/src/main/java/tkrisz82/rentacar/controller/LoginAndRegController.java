package tkrisz82.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tkrisz82.rentacar.db.CarDB;
import tkrisz82.rentacar.db.UserDB;

import tkrisz82.rentacar.model.User;
import tkrisz82.rentacar.services.PwdHandler;

@Controller
public class LoginAndRegController {
	
	
	// KEZDÉS
	@GetMapping("/rentacar")
	public String welcomePage() {

		return "welcome.html";
	}

	// REGISZTRÁCIÓRA UGRÁS
	@GetMapping("/registration")
	public String goToRegistration() {

		return "registration.html";
	}

	// BEJELENTKEZÉSRE UGRÁS
	@GetMapping("/login")
	public String goToLogin() {

		return "login.html";
	}

	
	// REGISZTRÁCIÓ
	@PostMapping("/reg")
	public String registration(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email, @RequestParam(name = "pwd") String pwd) {
		
		String html = "";
		
		PwdHandler ph = new PwdHandler();
		
		if(!ph.validate(email)) {
			model.addAttribute("feedback", "Rossz email formátum!");
			
			html = "registration.html";
		}
		else {
		
			String hashedpwd = ph.hashPassword(pwd);
			
			User user = new User();
	
			user.setId(0);
			user.setName(name);
			user.setEmail(email);
			user.setPwd(hashedpwd);
			user.setLogedin(0);
			user.setType("user");
	
			UserDB db = new UserDB();
	
			db.saveUser(user);
			
			html = "login.html";

		}
		
		return html;
	}
	
	
	// BEJELENTKEZÉS
	@PostMapping("/login")
	public String login(Model model, @RequestParam(name = "email") String email, @RequestParam(name = "pwd") String pwd

	) {
		
		PwdHandler ph = new PwdHandler();
		
		String html = "";

		UserDB udb = new UserDB();
		
		CarDB cdb = new CarDB();
		
		String hashedPwd = ph.hashPassword(pwd);

		User user = udb.getUserByEmailAndPwd(email, hashedPwd);

		if (user == null) { // Stimmel-e az email és a jelszó. Itt nem.

			html = "login.html";

			model.addAttribute("feedback", "Rossz email, vagy jelszó!");
		} else { // Stimmel-e az email és a jelszó. Itt igen.

			if (user.getType().equals("user")) {  // user estén

				user.setLogedin(1);

				udb.updateUser(user);
				
				model.addAttribute("carList", cdb.getAllNotBrokenCars());

				model.addAttribute("user", user);
				
				model.addAttribute("price", 0);
				model.addAttribute("eur", 0);

				html = "index.html";

			} else {  // admin esetén

				
				
				user.setLogedin(1);

				udb.updateUser(user);

				model.addAttribute("user", user);
				
				model.addAttribute("allCars", cdb.getAllCars());
				
				model.addAttribute("brokenCars", cdb.getAllBrokenCars());

				html = "admin.html";

			}
		}
		return html;
	}

	
	//KIJELENTKEZÉS
	@PostMapping("logout")
	public String logout(Model model, @RequestParam(name = "userid") int userId) {

		UserDB db = new UserDB();

		User user = db.getUserById(userId);
		user.setLogedin(0);

		db.updateUser(user);

		return "welcome.html";
	}

}
