package br.com.populaBD.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.populaBD.models.Carreira;
import br.com.populaBD.models.Ferramenta;

@Repository
public interface CarreiraRepository extends CrudRepository<Carreira, Long>  {
	
	Set<Carreira> findByFerramentasIn(List<Ferramenta> ferramentas);
	Carreira findByNome(String nome);

}
