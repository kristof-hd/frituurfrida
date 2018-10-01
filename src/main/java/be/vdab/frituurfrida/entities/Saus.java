package be.vdab.frituurfrida.entities;

import java.util.ArrayList;
import java.util.List;

public class Saus {
	private long id;
	private String naam;
	private List<String> ingredienten = new ArrayList<>();

	public Saus(long id, String naam) {
		this.id = id;
		this.naam = naam;
	}

	public Saus(long id, String naam, List<String> ingredienten) {
		this(id, naam);
		this.ingredienten.addAll(ingredienten);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public List<String> getIngredienten() {
		return ingredienten;
	}

	public void addIngredient(String ingredient) {
		ingredienten.add(ingredient);
	}
}