package com.practiceSpringBoot.FirstWebApp.loginController;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	// creating login form
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	}

	//for removing hardcoded username on line no 18
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}

}
