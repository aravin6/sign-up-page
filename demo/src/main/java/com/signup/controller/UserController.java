package com.signup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.signup.dao.UserDao;
import com.signup.model.User;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/")
	public String showSignUpDuringStart() {
		return "signup";
	}

	@GetMapping("/signup")
	public String showSignupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signUpUser(@ModelAttribute("user") User user) {
		userDao.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public ModelAndView showDashboardPage() {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		List<User> userList = userDao.findAll();
		modelAndView.addObject("userList", userList);
		return modelAndView;
	}

	@PostMapping("/dashboard/edit")
	public String editUserDetails(@ModelAttribute("user") User user) {
		userDao.save(user);
		return "dashboard";
	}

}
