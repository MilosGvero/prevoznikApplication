package test.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Rezervacija;
import test.test.service.LinijaService;
import test.test.service.RezervacijaService;
import test.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		
		Rezervacija rezervacija;
		
		rezervacija = new Rezervacija();
		
		if (rezervacija != null ) {
			rezervacija.setLinija(linijaService.findOne(dto.getLinijaId()));
		}
        
		return rezervacija;
		
	}

}
