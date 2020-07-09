package devART.uca.capas.service;

import devART.uca.capas.domain.AlumnoxMateria;
import devART.uca.capas.domain.Expediente;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AlumnoxMateriaService {
    public List<AlumnoxMateria> findAll() throws DataAccessException;
    public void insert(AlumnoxMateria alumnoxMateria) throws DataAccessException;
    public List<AlumnoxMateria> findOneEstudiante(int codigo) throws DataAccessException;
    public AlumnoxMateria filtrarUNO(Integer codigo) throws DataAccessException;

}
