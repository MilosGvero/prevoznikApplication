package test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.test.model.Rezervacija;
import test.test.repository.RezervacijaRepository;
import test.test.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService{

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		return rezervacijaRepository.save(rezervacija);
	}

}
