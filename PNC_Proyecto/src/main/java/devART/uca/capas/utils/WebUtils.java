package devART.uca.capas.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import devART.uca.capas.domain.*;
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

    public static List<UsuarioManager> getListUsers(List<AppUser> user, List<UserExpediente> userExp, List<Dpto>dptos, List<Municipio>municipios,String a){
        List<UsuarioManager> list = new ArrayList<>();
        UserExpediente ex=null;

        int i=0;
        System.out.println(a + " :user");
        for (AppUser u:user) {
        //if(true){
            System.out.println("user: "+u.getUserName());
            if(!u.getUserName().equals(a)){
                ex=userExp.get(i);
                //System.out.println("nombre: "+ex.getNombre());
                list.add(new UsuarioManager(u,userExp.get(i),dptos.get(ex.getDptoId()-1).getNombre(), municipios.get(ex.getMunicipioId()-1).getNombre()));
            }
           i++;
        }
        return list;
    }
     
}