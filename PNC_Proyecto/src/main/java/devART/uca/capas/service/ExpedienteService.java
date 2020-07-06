package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;
import devART.uca.capas.domain.Materia;



public interface ExpedienteService {
	public List<Expediente> findAllExpe() throws DataAccessException;
	public void insert(Expediente expediente) throws DataAccessException;


}
