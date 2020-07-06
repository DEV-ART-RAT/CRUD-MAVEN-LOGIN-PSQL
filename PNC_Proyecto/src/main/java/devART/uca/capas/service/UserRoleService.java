package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.UserRole;

public interface UserRoleService {
	public List<UserRole> findAll() throws DataAccessException;
	
	public List<String> findAllName(Long userID) throws DataAccessException;
	
	public void insert(UserRole user) throws DataAccessException;
	
	public void delete(UserRole user) throws DataAccessException;
	
	public UserRole findOne(int id) throws DataAccessException;
}
