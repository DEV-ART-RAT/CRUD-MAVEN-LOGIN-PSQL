package devART.uca.capas.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import devART.uca.capas.domain.*;
import devART.uca.capas.domain.Dpto;
import devART.uca.capas.domain.Municipio;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
 
public class WebUtils {

    public static String getRole(User user){
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            //sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                return  a.getAuthority();
            }
        }
        return "";
    }
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
        sb.append(" State: ").append(user.isEnabled());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public static void removeMeUser(List<AppUser> user, String sho){
        for (AppUser u :user  ) {
            if(u.getUserName().equals(sho)){
                user.remove(u);
                break;
            }
        }

    }
     
}