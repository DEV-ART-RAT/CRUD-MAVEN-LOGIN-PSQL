package devART.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.domain.AppRole;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.UserRole;

@Repository
public class AppRoleDAOImpl implements AppRoleDAO {
 
	@PersistenceContext(unitName="capas")
    private EntityManager entityManager;
 
    @Override
    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.appUser.userId = :userId ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

	@Override
	public AppRole findOne(String codigo) throws DataAccessException {
		return  entityManager.find(AppRole.class, codigo);
	}
    
    
 
}