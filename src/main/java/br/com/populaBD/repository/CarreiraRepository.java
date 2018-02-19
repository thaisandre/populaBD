package br.com.populaBD.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import br.com.populaBD.models.Carreira;
import br.com.populaBD.models.Ferramenta;



public interface CarreiraRepository extends CrudRepository<Carreira, Long>  {
	
	Set<Carreira> findByFerramentasIn(List<Ferramenta> ferramentas);

}
