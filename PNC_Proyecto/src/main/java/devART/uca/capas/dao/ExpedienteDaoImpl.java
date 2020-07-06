package devART.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.Expediente;

public class ExpedienteDaoImpl {
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

//	@Override
	public List<Expediente> findAllexpediente() throws DataAccessException {
//		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM public.expediente");
		Query query = entityManager.createNativeQuery(sb.toString(), Expediente.class);
		List<Expediente> resultset = query.getResultList();
		
		return resultset;
	}


//	@Override
//	@Transactional
//	public void insert(Estudiante estudiante) throws DataAccessException {
//		
//		entityManager.persist(estudiante);		
//		
//	}
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
}
