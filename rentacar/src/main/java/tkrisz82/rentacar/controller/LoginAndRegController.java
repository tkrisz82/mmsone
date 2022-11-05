package tkrisz82.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tkrisz82.rentacar.db.CarDB;
import tkrisz82.rentacar.db.UserDB;

import tkrisz82.rentacar.model.User;

@Controller
public class LoginAndRegController {

	@GetMapping("/rentacar")
	public String welcomePage() {

		return "welcome.html";
	}

	@GetMapping("/registration")
	public String goToRegistration() {

		return "registration.html";
	}

	@GetMapping("/login")
	public String goToLogin() {

		return "login.html";
	}

	@PostMapping("/reg")
	public String registration(Model model, @RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email, @RequestParam(name = "pwd") String pwd) {

		User user = new User();

		user.setId(0);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		user.setLogedin(0);
		user.setType("user");

		UserDB db = new UserDB();

		db.saveUser(user);

		return "login.html";
	}

	@PostMapping("/login")
	public String login(Model model, @RequestParam(name = "email") String email, @RequestParam(name = "pwd") String pwd

	) {

		String html = "";

		UserDB udb = new UserDB();
		
		CarDB cdb = new CarDB();

		User user = udb.getUserByEmailAndPwd(email, pwd);

		if (user == null) {

			html = "login.html";

			model.addAttribute("feedback", "Rossz email, vagy jelszó!");
		} else {

			if (user.getType().equals("user")) {

				user.setLogedin(1);

				udb.updateUser(user);
				
				model.addAttribute("carList", cdb.getAllNotBrokenCars());

				model.addAttribute("user", user);
				
				model.addAttribute("price", 0);

				html = "index.html";

			} else {

				
				
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

	@PostMapping("logout")
	public String logout(Model model, @RequestParam(name = "userid") int userId) {

		UserDB db = new UserDB();

		User user = db.getUserById(userId);
		user.setLogedin(0);

		db.updateUser(user);

		return "welcome.html";
	}

}
