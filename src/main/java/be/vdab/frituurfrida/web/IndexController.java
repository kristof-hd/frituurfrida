package be.vdab.frituurfrida.web;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.valueobjects.Adres;
import be.vdab.frituurfrida.valueobjects.Gemeente;

@Controller
@RequestMapping("/")
class IndexController {

	@GetMapping
	ModelAndView index() {

		LocalDate vandaag = LocalDate.now();
		DayOfWeek weekdag = vandaag.getDayOfWeek();
		String openGesloten = weekdag == DayOfWeek.MONDAY || weekdag == DayOfWeek.THURSDAY ? "gesloten" : "open";

		//return new ModelAndView("index", "openGesloten", openGesloten);
	
		return new ModelAndView("index", "openGesloten", openGesloten)
				.addObject(new Adres("Grote Markt", "7", new Gemeente("Brussel", 1000)));

	}
}