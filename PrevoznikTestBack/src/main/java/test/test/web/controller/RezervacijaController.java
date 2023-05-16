package test.test.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Linija;
import test.test.model.Rezervacija;
import test.test.service.RezervacijaService;
import test.test.support.RezervacijaDtoToRezervacija;
import test.test.support.RezervacijaToRezervacijaDto;
import test.test.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {


	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RezervacijaDtoToRezervacija toRezervacija;
	
	@Autowired
	private RezervacijaToRezervacijaDto toRezervacijaDto;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rezervacijaDTO){
        Rezervacija rezervacija = toRezervacija.convert(rezervacijaDTO);

        if(rezervacija.getLinija() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Linija linija = rezervacija.getLinija();
        linija.smanjiBrojMesta();

        Rezervacija sacuvan = rezervacijaService.save(rezervacija);

        return new ResponseEntity<>(toRezervacijaDto.convert(sacuvan), HttpStatus.CREATED);
    }
	
	
	
	
}
