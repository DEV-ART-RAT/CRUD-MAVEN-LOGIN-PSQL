package devART.uca.capas.service;

import devART.uca.capas.domain.Dpto;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DptoService {

    public List<Dpto> findAll() throws DataAccessException;

    public void insert(Dpto materia) throws DataAccessException;

    public void delete(Dpto materia) throws DataAccessException;

    public Dpto findOne(Integer codigo) throws DataAccessException;
}
