package devART.uca.capas.service;

import devART.uca.capas.domain.Municipio;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MunicipioService {

    public List<Municipio> findAll() throws DataAccessException;

    public void insert(Municipio materia) throws DataAccessException;

    public void delete(Municipio materia) throws DataAccessException;

    public Municipio findOne(Integer codigo) throws DataAccessException;
}