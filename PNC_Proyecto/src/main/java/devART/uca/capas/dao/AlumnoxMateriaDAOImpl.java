package devART.uca.capas.dao;

import devART.uca.capas.domain.AlumnoxMateria;
import devART.uca.capas.domain.Expediente;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class AlumnoxMateriaDAOImpl implements AlumnoxMateriaDAO{
    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    @Override
    public List<AlumnoxMateria> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM public.alumnoxmateria");
        Query query = entityManager.createNativeQuery(sb.toString(), AlumnoxMateria.class);
        List<AlumnoxMateria> resultset = query.getResultList();

        return resultset;
    }

    @Override
    public List<AlumnoxMateria> findOneEstudiante(Integer id) throws DataAccessException {
        return null;
    }

    @Override
    public void insert(AlumnoxMateria alumnoxMateria) throws DataAccessException {
        try {
            if(alumnoxMateria.getC_alumnoxmateria()==null)
                entityManager.persist(alumnoxMateria);
            else {
                entityManager.merge(alumnoxMateria);
                entityManager.flush();
            }
        }catch(Throwable e) {
            e.printStackTrace();
        }

    }
}
