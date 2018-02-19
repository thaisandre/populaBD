package br.com.populaBD.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.populaBD.models.Ferramenta;

public interface FerramentaRepository extends CrudRepository<Ferramenta, Long>  {
	
	Ferramenta findByNome(String nome);
	List<Ferramenta> findByCategoria(String categoria);

}
