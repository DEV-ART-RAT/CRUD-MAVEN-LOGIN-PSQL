package devART.uca.capas.dao;

import devART.uca.capas.domain.Dpto;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DptoDAO {
    public List<Dpto> findAllexpediente() throws DataAccessException;

    public Dpto findOne(Integer id) throws DataAccessException;
    //
    public void insert(Dpto dpto) throws DataAccessException;
//
//	public void delete(Integer codigo) throws DataAccessException;
}
