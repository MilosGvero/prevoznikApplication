package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Rezervacija;
import test.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDto implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija r) {
		
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(r.getId());
		dto.setLinijaId(r.getLinija().getId());
		
		return dto;
	}
	
	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacije){
        List<RezervacijaDTO> rezervacijaDTO = new ArrayList<>();

        for(Rezervacija rezervacija : rezervacije) {
        	rezervacijaDTO.add(convert(rezervacija));
        }

        return rezervacijaDTO;
    }
	
}
