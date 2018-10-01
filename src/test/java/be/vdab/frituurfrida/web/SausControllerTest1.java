package be.vdab.frituurfrida.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SausControllerTest1 {
	private SausController1 controller;

	@Before
	public void before() {
		controller = new SausController1();
	}

	@Test
	public void sauzenWerktSamenMetSauzenDotJsp() {
		assertEquals("sauzen1", controller.sauzen().getViewName());
	}

	@Test
	public void sauzenGeeftSauzenDoor() {
		assertTrue(controller.sauzen().getModel().containsKey("sauzen"));
	}
}