package devART.uca.capas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import devART.uca.capas.domain.AppUser;
 
@Repository
@Transactional
public class AppUserDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    //insert and update user
    public void insert(AppUser user) throws DataAccessException {
    	try {
			if(user.getUserId()==null)
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