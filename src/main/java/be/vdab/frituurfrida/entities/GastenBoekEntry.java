package be.vdab.frituurfrida.entities;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class GastenBoekEntry {
	private long id; 
	@NotBlank private String naam; 
	@NotNull @DateTimeFormat(style="S-") private LocalDateTime datumTijd=LocalDateTime.now(); 
	@NotBlank private String bericht;

	public GastenBoekEntry(long id, String naam, LocalDateTime datumTijd, String bericht) {
		this.id=id;
		this.naam=naam;
		this.datumTijd=datumTijd; 
		this.bericht=bericht; 		
	}

	public GastenBoekEntry(String naam, LocalDateTime datumTijd, String bericht) {
		this.naam=naam;
		this.datumTijd=datumTijd; 
		this.bericht=bericht; 	 
	}
	
	public GastenBoekEntry() {
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
		this.naam=naam;
	}
	public LocalDateTime getDatumTijd() {
		return datumTijd;
	}
	public String getBericht() {
		return bericht;
	}
	public void setBericht(String bericht) {
		this.bericht=bericht;
	}
}
