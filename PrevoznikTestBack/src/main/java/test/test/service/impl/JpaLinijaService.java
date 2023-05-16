package test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import test.test.model.Linija;
import test.test.repository.LinijaRepository;
import test.test.service.LinijaService;
@Service
public class JpaLinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;
	 
	
	@Override
	public Page<Linija> findAll(int pageNo) {
		return linijaRepository.findAll(PageRequest.of(pageNo, 4));
	}

	@Override
	public Linija findOne(Long id) {
		return linijaRepository.findOneById(id);
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Linija linija = findOne(id);
        if(linija != null){
        	linija.getPrevoznik().getLinije().remove(linija);
        	linijaRepository.delete(linija);
            return linija;
        }
        return null;
	}

	

	@Override
	public Page<Linija> find(Integer brojMestaOd, Integer brojMestaDo, Double cenaKarteOd, Double cenaKarteDo,
	        String destinacija, Long prevoznikId, int pageNo) {
	    
	    if (cenaKarteOd == null || cenaKarteOd < 0) {
	        cenaKarteOd = 0.0;
	    }

	    if (cenaKarteDo == null || cenaKarteDo < cenaKarteOd) {
	        cenaKarteDo = Double.MAX_VALUE;
	    }

	    if (brojMestaOd == null || brojMestaOd < 0) {
	        brojMestaOd = 0;
	    }

	    if (brojMestaDo == null || brojMestaDo < brojMestaOd) {
	        brojMestaDo = Integer.MAX_VALUE;
	    }
	    
	    return linijaRepository.search(brojMestaOd, brojMestaDo, cenaKarteOd, cenaKarteDo, destinacija, prevoznikId, PageRequest.of(pageNo, 4));
	}

	
}
