package devART.uca.capas.dao;

import devART.uca.capas.domain.UserExpediente;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserExpedienteDAO {
    public List<UserExpediente> findAllexpediente() throws DataAccessException;

    public UserExpediente findOne(Integer id) throws DataAccessException;
    //
    public void insert(UserExpediente estudiante) throws DataAccessException;
//
//	public void delete(Integer codigo) throws DataAccessException;
}
