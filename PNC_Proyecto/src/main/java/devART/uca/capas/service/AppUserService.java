package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.AppUser;

public interface AppUserService {
	public List<AppUser> findAll() throws DataAccessException;
	
	public void insert(AppUser user) throws DataAccessException;
	
	public void delete(AppUser user) throws DataAccessException;
	
	public AppUser findOne(String userName) throws DataAccessException;
}
