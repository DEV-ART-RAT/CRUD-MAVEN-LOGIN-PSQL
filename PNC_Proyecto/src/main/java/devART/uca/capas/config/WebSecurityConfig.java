package devART.uca.capas.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import devART.uca.capas.service.UserDetailsServiceImpl;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    private DataSource dataSource;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    
    	
    	
    	//limitando sesiones por usuario
    	http.sessionManagement().maximumSessions(1);
    	
    	
        http.csrf().disable();
 
        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/userCoordinador").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/NuevoExpediente").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/Cursadas").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/editarexpedienteeditar").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/expediente").access("hasAnyRole('ROLE_USER')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/ingresarMateria").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/editarMateria").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/materiasLista").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/administarUsuario").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/usuarioDesactive").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/usuarioActive").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/ingresarInstitucion").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/editarInstitucion").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/institucionLista").access("hasRole('ROLE_ADMIN')");

 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        
        
        // Config Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
 
    }
 
    

	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
   
    
  /*
   //remember me 
 // Token stored in Table (Persistent_Logins)
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(this.dataSource);
        return db;
    }
     
    // Token stored in Memory (Of Web Server).
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
 */
    
}