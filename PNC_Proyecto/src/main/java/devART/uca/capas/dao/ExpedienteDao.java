package devART.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;

public interface ExpedienteDao {
	
	public List<Expediente> findAllexpediente() throws DataAccessException;
	
	public Expediente findOne(Integer id) throws DataAccessException;
//	
	public void insert(Expediente estudiante) throws DataAccessException;
//	
//	public void delete(Integer codigo) throws DataAccessException;
}
