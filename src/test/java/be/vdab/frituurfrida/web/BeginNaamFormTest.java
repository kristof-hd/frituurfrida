package be.vdab.frituurfrida.web;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BeginNaamFormTest {
	private Validator validator;

	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void beginNaamOk() {
		Set<ConstraintViolation<BeginNaamForm>> violations = validator.validateValue(BeginNaamForm.class, "beginnaam",
				"d");
		assertTrue(violations.isEmpty());
	}

	@Test
	public void beginNaamMoetVerschillenVanNull() {
		Set<ConstraintViolation<BeginNaamForm>> violations = validator.validateValue(BeginNaamForm.class, "beginnaam",
				null);
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".NotBlank."));
	}

	@Test
	public void vanMoetMinstensEénTekenBevatten() {
		Set<ConstraintViolation<BeginNaamForm>> violations = validator.validateValue(BeginNaamForm.class, "beginnaam", "");
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".NotBlank."));
	}
}
