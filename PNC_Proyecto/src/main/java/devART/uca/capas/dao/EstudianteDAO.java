package devART.uca.capas.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Estudiante;

public interface EstudianteDAO {
	
	public List<Estudiante> findAll() throws DataAccessException;
	
	public Estudiante findOne(Integer id) throws DataAccessException;
	
	public void insert(Estudiante estudiante) throws DataAccessException;
	
	public void delete(Integer codigo) throws DataAccessException;

}