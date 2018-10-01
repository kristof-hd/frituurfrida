package be.vdab.frituurfrida.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.entities.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import be.vdab.frituurfrida.services.SnackService;

@Controller
@RequestMapping("snacks")
class SnackController {
	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final String ALFABET_VIEW = "alfabet";
	private static final String BEGIN_NAAM_VIEW = "beginnaam";
	private static final String WIJZIGEN_VIEW = "snackwijzigen";
	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/";
	private static final String REDIRECT_URL_BIJ_SNACK_NIET_GEVONDEN = "snacknietgevonden";

	private final SnackService snackService;

	SnackController(SnackService snackService) {
		this.snackService = snackService;
	}

	@GetMapping("alfabet")
	ModelAndView alfabet() {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET);
	}

	@GetMapping(params = "beginletter")
	ModelAndView findByBeginletter(char beginletter) {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET).addObject("snacks",
				snackService.findByBeginNaam(String.valueOf(beginletter)));
	}

	@GetMapping("beginnaam")
	ModelAndView beginNaam() {
		return new ModelAndView(BEGIN_NAAM_VIEW).addObject(new BeginNaamForm());
	}

	@GetMapping(params = "beginnaam")
	ModelAndView beginNaam(@Valid BeginNaamForm beginNaamForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(BEGIN_NAAM_VIEW);
		if (bindingResult.hasErrors()) {
			return modelAndView;
		}
		List<Snack> snacks = snackService.findByBeginNaam(beginNaamForm.getBeginnaam());
		if (snacks.isEmpty()) {
			bindingResult.reject("geenSnacks");
		} else {
			modelAndView.addObject("snacks", snacks);
		}
		return modelAndView;
	}

	@GetMapping("{id}/wijzigen")
	ModelAndView wijzigen(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(WIJZIGEN_VIEW);
		snackService.read(id).ifPresent(snack -> modelAndView.addObject(snack));
		return modelAndView;
	}

	@PostMapping("{id}/wijzigen")
	String wijzigen(@Valid Snack snack, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		try {
			snackService.update(snack);
			return REDIRECT_URL_NA_WIJZIGEN;
		} catch (SnackNietGevondenException ex) {
			return REDIRECT_URL_BIJ_SNACK_NIET_GEVONDEN;
		}
	}

}