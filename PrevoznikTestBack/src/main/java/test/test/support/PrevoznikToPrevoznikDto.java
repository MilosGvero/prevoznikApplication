package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Prevoznik;
import test.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO> {

	@Override
	public PrevoznikDTO convert(Prevoznik pr) {
		PrevoznikDTO dto = new PrevoznikDTO();
		
		dto.setId(pr.getId());
		dto.setNaziv(pr.getNaziv());
		dto.setAdresa(pr.getAdresa());
		dto.setPib(pr.getPib());
		
		return dto;
	}

	 public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
	        List<PrevoznikDTO> prevoznikDTO = new ArrayList<>();

	        for(Prevoznik pr : prevoznici) {
	        	prevoznikDTO.add(convert(pr));
	        }

	        return prevoznikDTO;
	    }
	
}
