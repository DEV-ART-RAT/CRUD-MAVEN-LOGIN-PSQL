package devART.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.dao.UserRoleDAO;
import devART.uca.capas.domain.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	UserRoleDAO users;

	@Override
	public List<UserRole> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllName(Long userID) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void insert(UserRole user) throws DataAccessException {
		users.insert(user);
		
	}

	@Override
	public void delete(UserRole user) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public UserRole findOne(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return users.findOne(id);
	}

}
