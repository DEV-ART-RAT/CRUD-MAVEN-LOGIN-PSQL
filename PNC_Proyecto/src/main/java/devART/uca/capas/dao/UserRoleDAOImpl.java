package devART.uca.capas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import devART.uca.capas.domain.UserRole;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO{
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;

	@Override
	@Transactional
	public UserRole findOne(Integer userID) {
		try {
            String sql = "Select e from " + UserRole.class.getName() + " e " //
                    + " Where e.appUser = :userID order by e.codigo ASC";
 
            Query query = entityManager.createQuery(sql, UserRole.class);
            query.setParameter("userID", userID);
 
            return (UserRole) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	@Transactional
	public void insert(UserRole user) {
		try {
			if(user.getId()==null)
				entityManager.persist(user);
			else {
				entityManager.merge(user);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}

}
