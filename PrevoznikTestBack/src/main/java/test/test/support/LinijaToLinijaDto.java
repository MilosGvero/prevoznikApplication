package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Linija;
import test.test.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija l) {
		
		LinijaDTO dto = new LinijaDTO();
		dto.setId(l.getId());
		dto.setBrojMesta(l.getBrojMesta());
		dto.setCenaKarte(l.getCenaKarte());
		dto.setDestinacija(l.getDestinacija());
		dto.setPolazak(l.getPolazak());
		dto.setPrevoznikId(l.getPrevoznik().getId());
		dto.setPrevoznikNaziv(l.getPrevoznik().getNaziv());
		
		
		
		return dto;
	}

	 public List<LinijaDTO> convert(List<Linija> linije){
	        List<LinijaDTO> linijaDTO = new ArrayList<>();

	        for(Linija linija : linije) {
	        	linijaDTO.add(convert(linija));
	        }

	        return linijaDTO;
	    }
	
	
}
