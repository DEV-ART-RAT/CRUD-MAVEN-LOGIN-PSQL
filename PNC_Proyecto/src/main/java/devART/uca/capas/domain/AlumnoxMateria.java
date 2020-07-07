package devART.uca.capas.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="alumnoxmateria")
public class AlumnoxMateria {

    @Id
    @Column(name="cod_materia")
    @Size(message = "La materia no debe superar los 6 caracteres", max = 6)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String codigoMateria;

    @Column(name="nombre_materia")
    @Size(message = "La materia no debe superar los 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nombreMateria;

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementExpediente")
    @GenericGenerator(name = "incrementExpediente", strategy = "increment")
    @Column(name="c_expediente")
    private Integer codigo;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_nota")
    @Size(message = "La nota no debe tener mas de 3 caracteres", max = 3)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nota;

    @Column(name = "s_estado")
    private String estado;

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
