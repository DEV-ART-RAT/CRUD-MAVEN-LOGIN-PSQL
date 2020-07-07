package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;


public interface ExpedienteService {
	public List<Expediente> findAllExpe() throws DataAccessException;
	public void insert(Expediente expediente) throws DataAccessException;
	public void delete(Integer codigo) throws DataAccessException;
	public Expediente filtrarUNO(Integer codigo) throws DataAccessException;
	public List<Expediente> filtrarPorID(Integer cadena) throws DataAccessException;
	public List<Expediente> filtrarPorNombre(String cadena) throws DataAccessException;
	public List<Expediente> filtrarPorApellido(String cadena) throws DataAccessException;
}