package devART.uca.capas.repositories;

import devART.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MateriaRepository extends JpaRepository<Materia, Integer> {
	@Query(nativeQuery=true, value="SELECT * FROM public.materia")
	public List<Materia> mostrarTodo() throws DataAccessException;
}
