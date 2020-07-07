package devART.uca.capas.service;

import devART.uca.capas.dao.MateriaDAO;
import devART.uca.capas.dao.MunicipioDAO;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.Municipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MunicipioServiceImpl implements MunicipioService {

    @Autowired
    MunicipioDAO materiaDAO;

    @Override
    public List<Municipio> findAll() throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findAllexpediente();
    }

    @Override
    @Transactional
    public void insert(Municipio materia) throws DataAccessException {
        // TODO Auto-generated method stub
        materiaDAO.insert(materia);
    }

    @Override
    @Transactional
    public void delete(Municipio materia) throws DataAccessException {
        // TODO Auto-generated method stub
        //materiaDAO.delete(materia);
    }

    @Override
    public Municipio findOne(Integer codigo) throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findOne(codigo);
    }


}