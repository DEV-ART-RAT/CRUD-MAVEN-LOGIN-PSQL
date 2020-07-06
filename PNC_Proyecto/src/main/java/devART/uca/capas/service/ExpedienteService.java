package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;



public interface ExpedienteService {
	public List<Expediente> findAllExpe() throws DataAccessException;
	

}
