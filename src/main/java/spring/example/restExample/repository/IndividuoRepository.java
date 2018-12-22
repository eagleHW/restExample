package spring.example.restExample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.example.restExample.events.Individuo;

@Repository
public interface IndividuoRepository extends JpaRepository<Individuo,Long> {
	
	Individuo findByNombre(String nombre);
	
	List<Individuo> findAll();
	
}
