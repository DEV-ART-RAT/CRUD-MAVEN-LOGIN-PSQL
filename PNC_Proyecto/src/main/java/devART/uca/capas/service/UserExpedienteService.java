package devART.uca.capas.service;

import devART.uca.capas.domain.UserExpediente;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserExpedienteService {

    public List<UserExpediente> findAll() throws DataAccessException;

    public void insert(UserExpediente materia) throws DataAccessException;

    public void delete(UserExpediente materia) throws DataAccessException;

    public UserExpediente findOne(Integer codigo) throws DataAccessException;
}