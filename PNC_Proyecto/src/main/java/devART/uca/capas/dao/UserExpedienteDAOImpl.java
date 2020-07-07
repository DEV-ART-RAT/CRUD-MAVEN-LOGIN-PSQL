package devART.uca.capas.dao;

import devART.uca.capas.domain.UserExpediente;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class UserExpedienteDAOImpl implements UserExpedienteDAO {
    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    //	@Override
    public List<UserExpediente> findAllexpediente() throws DataAccessException {
//
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM public.usuarioPersona");
        Query query = entityManager.createNativeQuery(sb.toString(), UserExpediente.class);
        List<UserExpediente> resultset = query.getResultList();

        return resultset;
    }


    @Override
    @Transactional
    public void insert(UserExpediente expediente) throws DataAccessException {

        // TODO Auto-generated method stub
        try {
            if(expediente.getCodigo()==null)
                entityManager.persist(expediente);
            else {
                entityManager.merge(expediente);
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
    public UserExpediente findOne(Integer id) throws DataAccessException {
        return entityManager.find(UserExpediente.class, id);
    }
}
