package devART.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import devART.uca.capas.domain.Expediente;
import devART.uca.capas.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import devART.uca.capas.domain.Materia;
import devART.uca.capas.dao.MateriaDAO;

@Service
public class MateriaServiceImpl implements MateriaService {
	
	@Autowired
	MateriaDAO materiaDAO;

	@Autowired
	MateriaRepository materiaRepo;

	@Override
	public List<Materia> findAll() throws DataAccessException {
		return materiaRepo.mostrarTodo();
	}

	@Override
	public List<Materia> findAllActiva() throws DataAccessException {
		return materiaRepo.mostrarTodoActiva();
	}
	
	@Override
	@Transactional
	public void insert(Materia materia) throws DataAccessException {
		materiaRepo.save(materia);
	}

	@Override
	@Transactional
	public void delete(Materia materia) throws DataAccessException {
		materiaDAO.delete(materia);
	}

	@Override
	public Materia findOne(String codigo) throws DataAccessException {
		return materiaRepo.getOne(codigo);
	}


}
