package devART.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import devART.uca.capas.domain.Materia;

@Repository
public class MateriaDAOImpl implements MateriaDAO{
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

	@Override
	public List<Materia> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM materia");
		Query query = entityManager.createNativeQuery(sb.toString(), Materia.class);
		List<Materia> resultset = query.getResultList();
		
		return resultset;
	}

	@Override
	@Transactional
	public Materia findOne(String codigo) throws DataAccessException {
		// TODO Auto-generated method stub
		return  entityManager.find(Materia.class, codigo);
	}

	@Override
	@Transactional
	public void insert(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			if(materia.getCodigo()==null)
				entityManager.persist(materia);
			else {
				entityManager.merge(materia);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	@Transactional
	public void delete(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		entityManager.remove(materia);
	}


}
