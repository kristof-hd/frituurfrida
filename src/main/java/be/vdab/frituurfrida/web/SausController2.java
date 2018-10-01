package be.vdab.frituurfrida.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.services.SausService;

@Controller
@RequestMapping("sauzen2")
class SausController2 {
	private static final String SAUZEN_VIEW = "sauzen2";
	private final SausService sausService;

	SausController2(SausService sausService) {
		this.sausService = sausService;
	}

	@GetMapping
	ModelAndView sauzen() {
		return new ModelAndView(SAUZEN_VIEW, "sauzen", sausService.findAll());
	}
}