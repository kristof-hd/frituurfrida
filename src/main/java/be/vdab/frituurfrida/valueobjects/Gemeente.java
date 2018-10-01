package be.vdab.frituurfrida.valueobjects;

public class Gemeente {
	private String naam; 
	private int postcode;

	public Gemeente(String naam, int postcode) {
		this.naam=naam;
		this.postcode=postcode; 
	}

	public Gemeente() {
	}
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
}
