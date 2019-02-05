package com.example.AES;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class HealtSportApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealtSportApplication.class, args);
	}
	
	@RequestMapping("/greeting")
	public String greeting(Model model, @RequestParam String name) {
		int [] array = {1,2,3,4};
		model.addAttribute("pepin", name);
		model.addAttribute("array", array);
		return "greeting_template";
	}

}

