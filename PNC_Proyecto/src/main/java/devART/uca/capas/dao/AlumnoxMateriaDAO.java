package devART.uca.capas.dao;

import devART.uca.capas.domain.AlumnoxMateria;
import devART.uca.capas.domain.Expediente;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlumnoxMateriaDAO {
    public List<AlumnoxMateria> findAll() throws DataAccessException;

    public List<AlumnoxMateria> findOneEstudiante(Integer id) throws DataAccessException;

    public void insert(AlumnoxMateria alumnoxMateria) throws DataAccessException;

}
