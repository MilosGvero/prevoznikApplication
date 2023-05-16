package test.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Prevoznik;
import test.test.service.PrevoznikService;
import test.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter<PrevoznikDTO,Prevoznik>{

	@Autowired
	private PrevoznikService prevoznikService;
	
	
	
	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		
		Prevoznik entity;
		
		 if(dto.getId() == null) {
	            entity = new Prevoznik();
	        }else {
	            entity = prevoznikService.findOne(dto.getId());
	        }
		 if(entity != null) {
	            entity.setNaziv(dto.getNaziv());
	            entity.setAdresa(dto.getAdresa());
	            entity.setPib(dto.getPib());
	        }

	        return entity;
		
		
	}

}
