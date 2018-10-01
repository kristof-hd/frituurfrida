package be.vdab.frituurfrida.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.entities.GastenBoekEntry;
import be.vdab.frituurfrida.services.GastenBoekService;

@Controller
@RequestMapping("gastenboek")
public class GastenBoekController {
	private final GastenBoekService gastenBoekService;
	private static final String VIEW = "gastenboek";
	private static final String REDIRECT_NA_CREATE = "redirect:/gastenboek";
	private static final String REDIRECT_NA_DELETE = "redirect:/gastenboek";
	
	GastenBoekController(GastenBoekService gastenBoekService) {
		this.gastenBoekService = gastenBoekService;
	}

	@GetMapping
	ModelAndView findAll() {
		return new ModelAndView(VIEW, "gastenboek", gastenBoekService.findAll());
	}

	@GetMapping("toevoegen")
	ModelAndView findAllMetToevoegOnderdeel() {
		return new ModelAndView(VIEW, "gastenboek", gastenBoekService.findAll()).addObject(new GastenBoekEntry());
	}

	@PostMapping
	ModelAndView create(@Valid GastenBoekEntry entry, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return new ModelAndView(VIEW, "gastenboek", gastenBoekService.findAll());
		}
		gastenBoekService.create(entry);
		return new ModelAndView(REDIRECT_NA_CREATE);
	}
	
	@PostMapping("verwijderen")
	String delete(long[] verwijderid) {
		if(verwijderid != null) {
			gastenBoekService.delete(verwijderid);
		}
		return REDIRECT_NA_DELETE; 
	}
	
}