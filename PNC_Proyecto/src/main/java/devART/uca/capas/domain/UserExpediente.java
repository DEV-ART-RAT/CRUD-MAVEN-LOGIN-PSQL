package devART.uca.capas.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="usuarioPersona")
public class UserExpediente {

    @Id
    @Column(name="c_id")
    private Integer codigo;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_nombre")
    @Size(message = "El nombre no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String s_nombre;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_apellido")
    @Size(message = "El apellido no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String s_apellido;
    //TODO(9 caracteres alfanumericos)


    @NotNull(message = "El campo Fecha no puede quedar vacio")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "d_fnacimiento")
    private String d_fnacimiento;

    @Column(name = "s_edad")
    private String s_edad;

    @Column(name = "s_depto")
    private Integer dptoId;

    @Column(name = "s_minicipio")
    private Integer municipioId;



    public UserExpediente() {}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getS_apellido() {
        return s_apellido;
    }

    public void setS_apellido(String s_apellido) {
        this.s_apellido = s_apellido;
    }

    public String getD_fnacimiento() {
        return d_fnacimiento;
    }

    public void setD_fnacimiento(String d_fnacimiento) {
        this.d_fnacimiento = d_fnacimiento;
    }

    public String getS_edad() {
        return s_edad;
    }

    public void setS_edad(String s_edad) {
        this.s_edad = s_edad;
    }

    public Integer getDptoId() {
        return dptoId;
    }

    public void setDptoId(Integer dptoId) {
        this.dptoId = dptoId;
    }

    public Integer getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Integer municipioId) {
        this.municipioId = municipioId;
    }
}
