package be.vdab.frituurfrida.web;

import javax.validation.constraints.NotBlank;

public class BeginNaamForm {
	@NotBlank private String beginnaam;

	public String getBeginnaam() {
		return beginnaam;
	}

	public void setBeginnaam(String beginnaam) {
		this.beginnaam = beginnaam;
	}
}
