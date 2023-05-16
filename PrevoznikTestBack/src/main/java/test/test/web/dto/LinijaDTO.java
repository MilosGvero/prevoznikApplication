package test.test.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class LinijaDTO {
	
	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;
	
	@Positive 
	private int brojMesta;
	 
	private double cenaKarte;
	
	private String polazak;
	
	@NotBlank
	private String destinacija;
	
	private Long prevoznikId;
	
	private String prevoznikNaziv;

	public LinijaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public String getPolazak() {
		return polazak;
	}

	public void setPolazak(String polazak) {
		this.polazak = polazak;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}

	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}
	
	
	
	
}
