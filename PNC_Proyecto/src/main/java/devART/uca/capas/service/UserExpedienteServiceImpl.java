package devART.uca.capas.service;

import devART.uca.capas.dao.UserExpedienteDAO;
import devART.uca.capas.domain.UserExpediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserExpedienteServiceImpl implements UserExpedienteService {

    @Autowired
    UserExpedienteDAO materiaDAO;

    @Override
    public List<UserExpediente> findAll() throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findAllexpediente();
    }

    @Override
    @Transactional
    public void insert(UserExpediente materia) throws DataAccessException {
        // TODO Auto-generated method stub
        materiaDAO.insert(materia);
    }

    @Override
    @Transactional
    public void delete(UserExpediente materia) throws DataAccessException {
        // TODO Auto-generated method stub
        //materiaDAO.delete(materia);
    }

    @Override
    public UserExpediente findOne(Integer codigo) throws DataAccessException {
        // TODO Auto-generated method stub
        return materiaDAO.findOne(codigo);
    }


}