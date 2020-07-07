package devART.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import devART.uca.capas.domain.Expediente;


public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {
	@Query(nativeQuery=true, value="SELECT * FROM public.expediente order by c_expediente ASC")
	public List<Expediente> mostrarTodo() throws DataAccessException;

	@Query(nativeQuery=true, value="SELECT * FROM public.expediente WHERE c_expediente = ?1 order by codigo ASC")
	public List<Expediente> mostrarPorID(Integer cadena) throws DataAccessException;

	@Query(nativeQuery=true, value="SELECT * FROM public.expediente WHERE s_nombre like %?1% order by codigo ASC")
	public List<Expediente> mostrarPorNombre(String cadena) throws DataAccessException;

	@Query(nativeQuery=true, value="SELECT * FROM public.expediente WHERE s_apellido like %?1% order by codigo ASC")
	public List<Expediente> mostrarPorApellido(String cadena) throws DataAccessException;

}
