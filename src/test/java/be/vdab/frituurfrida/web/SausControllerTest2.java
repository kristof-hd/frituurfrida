package be.vdab.frituurfrida.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.vdab.frituurfrida.services.SausService;

@RunWith(MockitoJUnitRunner.class)
public class SausControllerTest2 {
	@Mock private SausService sausService; 
	private SausController2 controller;

	@Before
	public void before() {
		controller = new SausController2(sausService);
	}

	@Test
	public void sauzenWerktSamenMetSauzenDotJsp() {
		assertEquals("sauzen2", controller.sauzen().getViewName());
	}

	@Test
	public void sauzenGeeftJuisteDataAanJSP() {
		assertTrue(controller.sauzen().getModel().containsKey("sauzen"));
		verify(sausService).findAll(); 
	}
}