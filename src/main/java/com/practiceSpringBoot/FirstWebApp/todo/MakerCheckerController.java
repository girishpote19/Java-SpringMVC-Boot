package com.practiceSpringBoot.FirstWebApp.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MakerCheckerController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "makerChecker.htm", method = RequestMethod.GET)
	public String showMakerCheckerForm(Model model) {
		List<String> productNames = productService.getAllProductNames();
		List<String> languages = parameterService.getAllLanguages();

		model.addAttribute("productNames", productNames);
		model.addAttribute("languages", languages);
		model.addAttribute("kfsTemplate", new KfsTemplate());

		return "makerCheckerForm";
	}

	@RequestMapping(value = "makerChecker.htm", method = RequestMethod.POST)
	public String saveKfsTemplate(@ModelAttribute("kfsTemplate") KfsTemplate kfsTemplate, Model model) {
		String kfsTextContent = kfsTemplate.getKfsTextContent();
		// Process kfsTextContent (e.g., save to database)

		// Reload form data after saving
		List<String> productNames = productService.getAllProductNames();
		List<String> languages = parameterService.getAllLanguages();

		model.addAttribute("productNames", productNames);
		model.addAttribute("languages", languages);
		model.addAttribute("kfsTemplate", kfsTemplate);
		model.addAttribute("message", "Template saved successfully!");

		return "makerCheckerForm";
	}
}
