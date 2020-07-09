package devART.uca.capas.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="centroescolar")
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementExpediente")
    @GenericGenerator(name = "incrementExpediente", strategy = "increment")
    @Column(name = "s_institucion")
    private Integer codigo;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name = "s_nombre")
    @Size(message = "El nombre no debe tener mas de 50 caracteres", max = 50)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nombre;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name = "s_descripcion")
    @Size(message = "El nombre no debe tener mas de 500 caracteres", max = 500)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_dpto", nullable = false)
    private Dpto dpto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_municipio", nullable = false)
    private Municipio municipio;

    public Institucion() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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