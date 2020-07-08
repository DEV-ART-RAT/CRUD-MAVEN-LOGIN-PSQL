package devART.uca.capas.dao;


import org.springframework.dao.DataAccessException;



import devART.uca.capas.domain.AppUser;

import java.util.List;


public interface AppUserDAO {
 
 
    public AppUser findUserAccount(String userName) ;
    
    //insert and update user
    public void insert(AppUser user);

    public List<AppUser> findAllexpediente() throws DataAccessException;
	
    
    
 
}