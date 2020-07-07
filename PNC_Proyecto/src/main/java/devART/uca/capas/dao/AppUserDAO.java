package devART.uca.capas.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import devART.uca.capas.domain.Dpto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import devART.uca.capas.domain.AppUser;

import java.util.List;


public interface AppUserDAO {
 
 
    public AppUser findUserAccount(String userName) ;
    
    //insert and update user
    public void insert(AppUser user);

    public List<AppUser> findAllexpediente() throws DataAccessException;
	
    
    
 
}