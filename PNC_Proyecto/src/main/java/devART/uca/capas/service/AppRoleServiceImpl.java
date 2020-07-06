package devART.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.dao.AppRoleDAO;
import devART.uca.capas.domain.AppRole;

@Service
public class AppRoleServiceImpl implements AppRoleService{
	@Autowired
	AppRoleDAO roles;

	@Override
	public List<AppRole> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(AppRole user) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AppRole user) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public AppRole findOne(String codigo) throws DataAccessException {
		// TODO Auto-generated method stub
		return roles.findOne(codigo);
	}

	@Override
	@Transactional
	public List<String> findAllName(Long userID) throws DataAccessException {
		// TODO Auto-generated method stub
		return roles.getRoleNames(userID);
	}

}
