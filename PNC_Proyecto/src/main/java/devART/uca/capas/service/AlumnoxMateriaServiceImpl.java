package devART.uca.capas.service;

import devART.uca.capas.domain.AlumnoxMateria;
import devART.uca.capas.repositories.AlumnoxMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoxMateriaServiceImpl implements AlumnoxMateriaService{

    @Autowired
    private AlumnoxMateriaRepository alumnoxMateriaRepository;


    @Override
    public List<AlumnoxMateria> findAll() throws DataAccessException {
        return alumnoxMateriaRepository.mostrarTodo();
    }

    @Override
    @Transactional
    public void insert(AlumnoxMateria alumnoxMateria) throws DataAccessException {
        alumnoxMateriaRepository.saveAndFlush(alumnoxMateria);
    }

    @Override
    public List<AlumnoxMateria> findOneEstudiante(int cadena) throws DataAccessException {
        return alumnoxMateriaRepository.mostrarPorAlumno(cadena);
    }



}
