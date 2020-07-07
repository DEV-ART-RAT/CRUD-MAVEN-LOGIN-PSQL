package devART.uca.capas.dao;

import devART.uca.capas.domain.Dpto;
import devART.uca.capas.domain.Municipio;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MunicipioDAOImpl implements MunicipioDAO {
    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    //	@Override
    public List<Municipio> findAllexpediente() throws DataAccessException {
//
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM public.dpto");
        Query query = entityManager.createNativeQuery(sb.toString(), Municipio.class);
        List<Municipio> resultset = query.getResultList();

        return resultset;
    }


    @Override
    @Transactional
    public void insert(Municipio dpto) throws DataAccessException {

        // TODO Auto-generated method stub
        try {
            if(dpto.getCodigo()==null)
                entityManager.persist(dpto);
            else {
                entityManager.merge(dpto);
                entityManager.flush();
            }
        }catch(Throwable e) {
            e.printStackTrace();
        }

    }
//
//	@Override
//	@Transactional
//	public void delete(Integer codigo) throws DataAccessException {
//
//		Estudiante estudiante = entityManager.find(Estudiante.class, codigo);
//		entityManager.remove(estudiante);
//
//	}
//
//
//	@Override
//	public Estudiante findOne(Integer id) throws DataAccessException {
//		return entityManager.find(Estudiante.class, id);
//	}


    @Override
    public Municipio findOne(Integer id) throws DataAccessException {
        return entityManager.find(Municipio.class, id);
    }
}
