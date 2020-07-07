package devART.uca.capas.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="dpto")
public class Dpto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementExpediente")
    @GenericGenerator(name = "incrementExpediente", strategy = "increment")
    @Column(name="s_depto")
    private Integer codigo;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_nombre")
    @Size(message = "El nombre no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nombre;

    public Dpto() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
