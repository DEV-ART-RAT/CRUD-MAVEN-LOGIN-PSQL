package devART.uca.capas.dao;

import devART.uca.capas.domain.Dpto;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DptoDAOImpl implements DptoDAO {
    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    //	@Override
    public List<Dpto> findAllexpediente() throws DataAccessException {
//
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM public.dpto");
        Query query = entityManager.createNativeQuery(sb.toString(), Dpto.class);
        List<Dpto> resultset = query.getResultList();

        return resultset;
    }


    @Override
    @Transactional
    public void insert(Dpto dpto) throws DataAccessException {

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
    public Dpto findOne(Integer id) throws DataAccessException {
        return entityManager.find(Dpto.class, id);
    }
}

