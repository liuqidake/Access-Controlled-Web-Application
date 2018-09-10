package com.qiliu.springcloud.simplesocialmediaapplicationserver.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qiliu.springcloud.simplesocialmediaapplicationserver.Model.User;
import com.qiliu.springcloud.simplesocialmediaapplicationserver.Service.UserService;

@Controller
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/","/login"})
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		
		return model;
	}
	
	@GetMapping("/registration")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("registration");
		return model;
	}
	
	@PostMapping("/registration")
	public ModelAndView register(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userFound = userService.findUserByUsername(user.getUserName());
		if(userFound!=null) {
			bindingResult.rejectValue("username", "error.user", "username exists");
		} else if (bindingResult.hasErrors()) {
			model.setViewName("/registeration");
		} else {
			userService.saveUser(user);
			model.addObject("msg","Successfully registered your account!");
			model.setViewName("/login");
		}
		
		return model;
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("username", auth.getName());
		model.setViewName("home");
		return model;
	}
	
	
}
