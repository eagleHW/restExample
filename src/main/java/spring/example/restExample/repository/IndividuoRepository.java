package spring.example.restExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.example.restExample.events.Individuo;

public interface IndividuoRepository extends JpaRepository<Individuo,Long> {
	
	Individuo findByNombre(String nombre);
	
}
