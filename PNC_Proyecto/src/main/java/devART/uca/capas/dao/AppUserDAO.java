package devART.uca.capas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import devART.uca.capas.domain.AppUser;
 

public interface AppUserDAO {
 
 
    public AppUser findUserAccount(String userName) ;
    
    //insert and update user
    public void insert(AppUser user);
	
    
    
 
}