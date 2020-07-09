package devART.uca.capas.service;

//import com.sun.deploy.security.UserDeclinedException;
import com.sun.deploy.security.UserDeclinedException;
import devART.uca.capas.dao.AppRoleDAO;
import devART.uca.capas.dao.AppUserDAO;
import devART.uca.capas.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AppUserDAO appUserDAO;
 
    @Autowired
    private AppRoleDAO appRoleDAO;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	
        AppUser appUser = this.appUserDAO.findUserAccount(userName);
 
        //verificando si usuario existe
        if (appUser == null) {
            //System.out.println("User not found! " + userName);
            throw new AuthenticationCredentialsNotFoundException("Usuario " + userName + " no se encontro en Base de Datos");
        }
        
        //verificando si usuario es activo
        if(!appUser.isEnabled()) {
        	//System.out.println("User not Active! " + userName);
            throw new AuthenticationCredentialsNotFoundException("La Cuenta " + userName + " no se encuentra activa, pedir activacion a Administrador");
        }
 
        //System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);
 
        return userDetails;
    }
 
}