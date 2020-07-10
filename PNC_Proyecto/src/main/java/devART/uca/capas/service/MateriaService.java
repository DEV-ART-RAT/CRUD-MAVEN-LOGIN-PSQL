package devART.uca.capas.service;

import java.util.List;

import devART.uca.capas.domain.Expediente;
import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Materia;



public interface MateriaService {
	
	public List<Materia> findAll() throws DataAccessException;
	
	public List<Materia> findAllActiva() throws DataAccessException;

	public void insert(Materia materia) throws DataAccessException;
	
	public void delete(Materia materia) throws DataAccessException;
	
	public Materia findOne(String codigo) throws DataAccessException;

}
