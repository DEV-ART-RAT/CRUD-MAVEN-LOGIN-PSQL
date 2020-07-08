package devART.uca.capas.dao;

import devART.uca.capas.domain.Municipio;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MunicipioDAO {
    public List<Municipio> findAllexpediente() throws DataAccessException;

    public Municipio findOne(Integer id) throws DataAccessException;
    //
    public void insert(Municipio municipio) throws DataAccessException;
//
//	public void delete(Integer codigo) throws DataAccessException;
}
