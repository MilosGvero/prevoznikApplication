package test.test.web.dto;

import javax.validation.constraints.Positive;

public class RezervacijaDTO {

	@Positive(message = "Id mora biti pozitivan broj.")
	private Long id;
	
	private Long linijaId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}
	
	
	
}
