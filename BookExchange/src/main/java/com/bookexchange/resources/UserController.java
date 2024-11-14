package com.bookexchange.resources;

import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookexchange.entity.User;
import com.bookexchange.entity.UserRequest;
import com.bookexchange.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String homePage() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    if (authentication instanceof AnonymousAuthenticationToken) return "home";

	    return "login";
	}

	@GetMapping("/home")
	public String home(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("userdetail", userDetails);
		return "welcome";
	}

	@GetMapping("/login")
	public String login(Model model, UserRequest userDto) {

		model.addAttribute("user", userDto);
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model, UserRequest userDto) {
		model.addAttribute("user", userDto);
		return "register";
	}

	@PostMapping("/register")
	public String registerSave(@ModelAttribute("user") UserRequest userDto, Model model) {
		User user = userService.findByEmail(userDto.getEmail());
		if (user != null) {
			model.addAttribute("Userexist", user);
			return "register";
		}  
        String emailRegex = "^(.+)@(.+)$";  
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMather = emailPattern.matcher(userDto.getEmail());
		if (emailMather.matches() == false) {
			model.addAttribute("EmailNotValid", userDto);
			return "register";
		}
		if (userDto.getPassword().length() < 8) {
			model.addAttribute("PwdNotValidLength", userDto);
			return "register";
		}
		String passwordRegex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		Pattern pwdPattern = Pattern.compile(passwordRegex);
        Matcher pwdMather = pwdPattern.matcher(userDto.getPassword());
		if (pwdMather.matches() == false) {
			model.addAttribute("PwdNotValid", userDto);
			return "register";
		}
		userService.save(userDto);
		return "redirect:/register?success";
	}
}