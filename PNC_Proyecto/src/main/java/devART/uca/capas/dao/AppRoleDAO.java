package devART.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.domain.AppRole;
import devART.uca.capas.domain.Materia;
import devART.uca.capas.domain.UserRole;
 
public interface AppRoleDAO {
	
    public List<String> getRoleNames(Long userId) ;
    
    public AppRole findOne(Long roleId) throws DataAccessException;
 
}
