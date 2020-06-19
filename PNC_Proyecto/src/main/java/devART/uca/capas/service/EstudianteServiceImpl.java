package devART.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.domain.Estudiante;
import devART.uca.capas.dto.EstudianteDTO;
import devART.uca.capas.repositories.EstudianteRepository;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	@Autowired
	private EstudianteRepository estudianteRepo;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException {
			return estudianteRepo.mostrarTodo();	
	}

	@Override
	@Transactional
	public void insert(Estudiante estudiante) throws DataAccessException {
		estudianteRepo.save(estudiante);
	}

	@Override
	@Transactional
	public void delete(Integer codigo) throws DataAccessException {

		estudianteRepo.deleteById(codigo);
		
	}

	@Override
	public Estudiante findOne(Integer id) throws DataAccessException {
		return estudianteRepo.getOne(id);
	}
	
	@Override
	public List<Estudiante> filtrarPor(String cadena) throws DataAccessException {
		return estudianteRepo.mostrarPorNombre(cadena);
	}
	
	@Override
	public List<Estudiante> empiezaCon(String cadena) throws DataAccessException {
		return estudianteRepo.findByApellidoStartingWith(cadena);
	}
	
	@Override
	public List<EstudianteDTO> dtoPrueba() throws DataAccessException {
			return estudianteRepo.pruebaDTO();	
	}

}