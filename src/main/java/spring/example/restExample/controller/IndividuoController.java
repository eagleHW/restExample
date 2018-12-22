package spring.example.restExample.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.example.restExample.events.Individuo;
import spring.example.restExample.repository.IndividuoRepository;

@RestController
public class IndividuoController {

	
	public final IndividuoRepository individuoRepository;

	@Autowired
	public IndividuoController(IndividuoRepository individuoRepository) {
		super();
		this.individuoRepository = individuoRepository;
	}
	
	
	@GetMapping(value="/individuos", produces = {"application/hal+json"})
	Resources<Resource<Individuo>> all(){
		
		List<Resource<Individuo>> individuos = individuoRepository.findAll().stream()
				.map(individuo -> new Resource<>(individuo, 
				              linkTo(methodOn(IndividuoController.class).findById(individuo.getId())).withSelfRel(),
				              linkTo(methodOn(IndividuoController.class).all()).withRel("individuos")))
		        .collect(Collectors.toList());
		
		return new Resources<>(individuos,
				               linkTo(methodOn(IndividuoController.class).all()).withSelfRel());
	}
	
	
	@GetMapping(value="/individuo/{id}", produces = {"application/hal+json"})
	Resource<Individuo> findById(@PathVariable Long id){
		
		Individuo individuo = individuoRepository.findById(id).orElse(null);

		return new Resource<>(individuo, 
				              linkTo(methodOn(IndividuoController.class).findById(id)).withSelfRel()); 
		
		
	}
			
	
	
}
