package devART.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import devART.uca.capas.domain.Materia;
import devART.uca.capas.dao.MateriaDAO;

@Service
public class MateriaServiceImpl implements MateriaService {
	
	@Autowired
	MateriaDAO materiaDAO;

	@Override
	public List<Materia> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return materiaDAO.findAll();
	}

	@Override
	@Transactional
	public void insert(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		materiaDAO.insert(materia);
	}

	@Override
	@Transactional
	public void delete(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		materiaDAO.delete(materia);
	}

	@Override
	public Materia findOne(Integer codigo) throws DataAccessException {
		// TODO Auto-generated method stub
		return materiaDAO.findOne(codigo);
	}

}
