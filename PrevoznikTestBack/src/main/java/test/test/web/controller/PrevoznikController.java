package test.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Prevoznik;
import test.test.service.LinijaService;
import test.test.service.PrevoznikService;
import test.test.support.LinijaToLinijaDto;
import test.test.support.PrevoznikDtoToPrevoznik;
import test.test.support.PrevoznikToPrevoznikDto;
import test.test.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/prevoznici", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class PrevoznikController {

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
    private PrevoznikDtoToPrevoznik toPrevoznik;

    @Autowired
    private PrevoznikToPrevoznikDto toPrevoznikDto;
	
    @Autowired
    private LinijaToLinijaDto toLinijaDto;
    
    
    @GetMapping
    public ResponseEntity<List<PrevoznikDTO>> getAll(){

        List<Prevoznik> prevoznici = prevoznikService.findAll();

        return new ResponseEntity<>(toPrevoznikDto.convert(prevoznici), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrevoznikDTO> create(@Valid @RequestBody PrevoznikDTO prevoznikDTO){
        Prevoznik pr = toPrevoznik.convert(prevoznikDTO);
        Prevoznik sacuvan = prevoznikService.save(pr);

        return new ResponseEntity<>(toPrevoznikDto.convert(sacuvan), HttpStatus.CREATED);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	
}
