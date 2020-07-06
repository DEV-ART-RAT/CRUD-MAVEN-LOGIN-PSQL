package devART.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
 
@Entity
@Table(schema = "public" ,name = "App_User")
public class AppUser {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    @Column(name = "User_Id", nullable = false)
    private Long userId;
 
	
    @Column(name = "User_Name", length = 36, nullable = false)
    @NotNull(message = "Este campo no puede estar vacio")
	@NotEmpty(message = "Este campo no puede estar vacio")
	@Size(message = "El Usuario no debe tener menos de 3 caracteres", min = 3)
    private String userName;
 

    @Column(name = "Encryted_Password", length = 128, nullable = false)
	@NotNull(message = "Este campo no puede estar vacio")
	@NotEmpty(message = "Este campo no puede estar vacio")
	@Size(message = "La Contrasena no debe tener menos de 3 caracteres", min = 3)
    private String encrytedPassword;
 
	
	@NotNull(message = "Este campo no puede estar vacio")
    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
 
}