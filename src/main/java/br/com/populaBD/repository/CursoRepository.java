package br.com.populaBD.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.populaBD.models.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
	
	
}
