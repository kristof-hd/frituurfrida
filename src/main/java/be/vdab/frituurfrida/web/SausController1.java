package be.vdab.frituurfrida.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.entities.Saus;

@Controller
@RequestMapping("sauzen1")
class SausController1 {
	private List<Saus> sauzen = Arrays.asList(new Saus(3L, "cocktail", Arrays.asList("mayonaise", "ketchup", "cognac")),
			new Saus(6L, "mayonaise", Arrays.asList("ei", "mosterd")),
			new Saus(7L, "mosterd", Arrays.asList("mosterd", "azijn", "witte wijn")),
			new Saus(12L, "tartare", Arrays.asList("mayonaise", "augurk", "tabasco")),
			new Saus(44L, "vinaigrette", Arrays.asList("olijfolie", "mosterd", "azijn")));
	private static final String SAUZEN_VIEW = "sauzen1";

	@GetMapping
	ModelAndView sauzen() {
		return new ModelAndView(SAUZEN_VIEW, "sauzen", sauzen);
	}
}