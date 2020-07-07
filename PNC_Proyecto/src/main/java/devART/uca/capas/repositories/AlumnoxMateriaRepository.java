package devART.uca.capas.repositories;

import devART.uca.capas.domain.AlumnoxMateria;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoxMateriaRepository extends JpaRepository<AlumnoxMateria, Integer> {
    @Query(nativeQuery=true, value="SELECT * FROM public.alumnoxmateria")
    public List<AlumnoxMateria> mostrarTodo() throws DataAccessException;

    @Query(nativeQuery=true, value="SELECT * FROM public.alumnoxmateria WHERE c_expediente = ?1 order by codigo ASC")
    public List<AlumnoxMateria> mostrarPorAlumno(Integer cadena) throws DataAccessException;
}
