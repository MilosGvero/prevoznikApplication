package test.test;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.test.model.Prevoznik;
import test.test.service.LinijaService;
import test.test.service.PrevoznikService;

@Component
public class TestData {

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Autowired
	private LinijaService linijaService;
	
	
	@PostConstruct
    public void init() {
	
	System.out.println("-------------------------------");
    System.out.println("Svi prevoznici: ");
    List<Prevoznik> prevoznici = prevoznikService.findAll();
    for (Prevoznik prevoznik:prevoznici) {
        System.out.println(prevoznik);
    }
    System.out.println("-------------------------------");
    
    
    
    
    
    
    
    
}
}
