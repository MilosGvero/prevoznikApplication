package test.test.service;

import org.springframework.data.domain.Page;

import test.test.model.Linija;

public interface LinijaService {

	 Page<Linija> findAll(int pageNo);
	 Linija findOne(Long id);
	 Linija save(Linija linija);
	 Linija update(Linija linija);
	 Linija delete(Long id);
	 Page<Linija> find(Integer brojMestaOd, Integer brojMestaDo, Double cenaKarteOd, Double cenaKarteDo, String destinacija, Long prevoznikId, int pageNo);

	 
	
	
}
