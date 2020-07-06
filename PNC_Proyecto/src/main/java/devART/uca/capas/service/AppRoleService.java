package devART.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import devART.uca.capas.domain.AppRole;

public interface AppRoleService {
	public List<AppRole> findAll() throws DataAccessException;
	
	public List<String> findAllName(Long userID) throws DataAccessException;
	
	public void insert(AppRole user) throws DataAccessException;
	
	public void delete(AppRole user) throws DataAccessException;
	
	public AppRole findOne(String codigo) throws DataAccessException;
}
