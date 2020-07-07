package devART.uca.capas.service;

import devART.uca.capas.dao.DptoDAO;
import devART.uca.capas.domain.Dpto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DptoServiceImpl implements DptoService {

    @Autowired
    DptoDAO materiaDAO;

    @Override
    public List<Dpto> findAll() throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findAllexpediente();
    }

    @Override
    @Transactional
    public void insert(Dpto materia) throws DataAccessException {
        // TODO Auto-generated method stub
        materiaDAO.insert(materia);
    }

    @Override
    @Transactional
    public void delete(Dpto materia) throws DataAccessException {
        // TODO Auto-generated method stub
        //materiaDAO.delete(materia);
    }

    @Override
    public Dpto findOne(Integer codigo) throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findOne(codigo);
    }


}