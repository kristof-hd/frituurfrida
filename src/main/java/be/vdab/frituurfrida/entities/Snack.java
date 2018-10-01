package be.vdab.frituurfrida.entities;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Snack {
	private long id;
	@NotBlank private String naam;
	@NotNull @Min(0) private BigDecimal prijs;

	public Snack(long id, String naam, BigDecimal prijs) {
		this.id=id;
		this.naam=naam;
		this.prijs=prijs; 		
	}

	public Snack(String naam, BigDecimal prijs) {
		this.naam=naam;
		this.prijs=prijs; 		
	}	

	public Snack() {
	}	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}
}
