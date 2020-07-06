package devART.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devART.uca.capas.dao.AppUserDAO;
import devART.uca.capas.domain.AppUser;

@Service
public class AppUserServiceImpl implements AppUserService{
	
	@Autowired
	AppUserDAO userDAO;

	@Override
	public List<AppUser> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void insert(AppUser user) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void delete(AppUser user) throws DataAccessException {
		userDAO.insert(user);
		
	}

	@Override
	@Transactional
	public AppUser findOne(String userName) throws DataAccessException {
		return userDAO.findUserAccount(userName);
	}

}
