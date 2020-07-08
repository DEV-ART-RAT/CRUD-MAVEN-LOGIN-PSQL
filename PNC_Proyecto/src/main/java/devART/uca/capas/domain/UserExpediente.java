package devART.uca.capas.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="usuarioPersona")
public class UserExpediente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    @Column(name="c_id")
    private Long codigo;

    @Column(name="s_nombre")
    @Size(message = "El nombre no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "El Nombre no puede estar vacio")
    private String nombre;

    @Column(name="s_apellido")
    @Size(message = "El apellido no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String apellido;

    @Column(name="s_direccion")
    @Size(message = "La direccion no debe tener mas de 100 caracteres", max = 100)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String direccion;
    //TODO(9 caracteres alfanumericos)



    @NotNull(message = "El campo Fecha no puede quedar vacio")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "Este campo no puede estar vacio")
    @Column(name = "d_fnacimiento")
    private String fnacimiento;

    @Column(name = "s_edad")
    private String edad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_depto", nullable = false)
    private Dpto dpto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_minicipio", nullable = false)
    private Municipio municipio;




    public UserExpediente() {}


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCodigo() {
         return  codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Dpto getDpto() {
        return dpto;
    }

    public void setDpto(Dpto dpto) {
        this.dpto = dpto;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
