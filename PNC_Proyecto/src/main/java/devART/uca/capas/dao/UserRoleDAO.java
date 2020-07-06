package devART.uca.capas.dao;


import devART.uca.capas.domain.UserRole;

public interface UserRoleDAO {
	public UserRole findOne(Integer userID) ;
    //insert and update user
    public void insert(UserRole user);
}
