package test.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Linija;
import test.test.service.LinijaService;
import test.test.service.PrevoznikService;
import test.test.support.LinijaDtoToLinija;
import test.test.support.LinijaToLinijaDto;
import test.test.web.dto.LinijaDTO;

@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {

	
	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaDtoToLinija toLinija;
	
	@Autowired
	private LinijaToLinijaDto toLinijaDto;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDTO){
        Linija linija = toLinija.convert(linijaDTO);

        if(linija.getPrevoznik() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Linija sacuvan = linijaService.save(linija);

        return new ResponseEntity<>(toLinijaDto.convert(sacuvan), HttpStatus.CREATED);
    }
	
	@PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){

        if(!id.equals(linijaDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Linija linija = toLinija.convert(linijaDTO);

        if(linija.getPrevoznik() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Linija sacuvan = linijaService.update(linija);

        return new ResponseEntity<>(toLinijaDto.convert(sacuvan),HttpStatus.OK);
    }
	 
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Linija obrisanLinija = linijaService.delete(id);

        if(obrisanLinija != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
        Linija linija = linijaService.findOne(id);

        if(linija != null) {
            return new ResponseEntity<>(toLinijaDto.convert(linija), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
    @GetMapping
    public ResponseEntity<List<LinijaDTO>> getAll(
    		@RequestParam(required=false) Integer brojMestaOd,
            @RequestParam(required=false) Integer brojMestaDo,
            @RequestParam(required=false) Double cenaKarteOd,
            @RequestParam(required=false) Double cenaKarteDo, 
            @RequestParam(required=false) String destinacija,
            @RequestParam(required = false) Long prevoznikId,
    		@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

    	 Page<Linija> page;
    	 try{
    		 page = linijaService.find(brojMestaOd, brojMestaDo, cenaKarteOd, cenaKarteDo, destinacija, prevoznikId, pageNo);
         }catch (Exception e){
        	 page = linijaService.findAll(pageNo);
         }
    	 HttpHeaders headers = new HttpHeaders();
         headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

        return new ResponseEntity<>(toLinijaDto.convert(page.getContent()), headers, HttpStatus.OK);
    }
	 
	
	
}
