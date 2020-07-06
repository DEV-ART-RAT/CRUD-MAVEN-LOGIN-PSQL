package devART.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;

public interface ExpedienteDao {
	
	public List<Expediente> findAllexpediente() throws DataAccessException;
	
//	public Estudiante findOne(Integer id) throws DataAccessException;
//	
//	public void insert(Estudiante estudiante) throws DataAccessException;
//	
//	public void delete(Integer codigo) throws DataAccessException;
}
