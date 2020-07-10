package devART.uca.capas.config;

import devART.uca.capas.domain.AppUser;
import devART.uca.capas.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    AppUserService userServices;

    public CustomLogoutSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onLogoutSuccess( HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Authentication authentication) throws IOException, ServletException {
        final String refererUrl = httpServletRequest.getHeader("Referer");
        HttpSession session=httpServletRequest.getSession();
        session.invalidate();
        System.out.println(refererUrl);
        System.out.println(authentication.getName());

        super.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication);


        //Solucion2
//        if (authentication != null && authentication.getDetails() != null) {
//            try {
//                httpServletRequest.getSession().invalidate();
//                System.out.println("User Successfully Logout");
//                //you can add more codes here when the user successfully logs out,
//                //such as updating the database for last active.
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//        //redirect to login
//        httpServletResponse.sendRedirect("/login");


//        ///Solucion 3
//        if(authentication != null) {
//            System.out.println(authentication.getName());
//        }
//        authentication.;
//        //perform other required operation
//        String URL = httpServletRequest.getContextPath() + "/login";
//        httpServletResponse.setStatus(HttpStatus.OK.value());
//        httpServletResponse.sendRedirect(URL);

    }

}