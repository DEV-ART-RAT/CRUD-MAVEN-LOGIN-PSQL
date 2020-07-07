package devART.uca.capas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.domain.AppUser;

@Repository
public class AppUserDAOImpl  implements AppUserDAO{
 
	@PersistenceContext(unitName="capas")
    private EntityManager entityManager;
 
    @Override
    @Transactional
    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName order by e.userId ASC";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    //insert and update user
    @Override
    @Transactional
    public void insert(AppUser user) throws DataAccessException {
    	System.out.println("aqui stoy en DAO" + user.getUserName()+ user.getUserId());
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