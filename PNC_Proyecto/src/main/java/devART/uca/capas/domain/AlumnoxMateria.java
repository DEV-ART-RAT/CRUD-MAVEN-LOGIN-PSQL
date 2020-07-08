package devART.uca.capas.domain;

import devART.uca.capas.domain.Expediente;
import devART.uca.capas.domain.Materia;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="alumnoxmateria")
public class AlumnoxMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementalumnoxmateria")
    @GenericGenerator(name = "incrementalumnoxmateria", strategy = "increment")
    @Column(name="c_alumnoxmateria")
    private Integer c_alumnoxmateria;

    @ManyToOne
    @JoinColumn(name = "c_expediente", nullable = false)
    private Expediente expediente;

    @Transient
    private Integer c_expediente;

    @ManyToOne
    @JoinColumn(name = "cod_materia", nullable = false)
    private Materia materia ;

    @Transient
    private String cod_materia;

    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_nota")
    @Size(message = "La nota no debe tener mas de 3 caracteres", max = 3)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nota;

    @Column(name = "s_estado")
    private String estado;

    public AlumnoxMateria(){}

    public Integer getC_alumnoxmateria() {
        return c_alumnoxmateria;
    }

    public void setC_alumnoxmateria(Integer c_alumnoxmateria) {
        this.c_alumnoxmateria = c_alumnoxmateria;
    }

    public Integer getC_expediente() {
        return c_expediente;
    }

    public Integer setC_expediente(Integer c_expediente) {
        this.c_expediente = c_expediente;
        return c_expediente;
    }

    public String getCod_materia() {
        return cod_materia;
    }

    public String setCod_materia(String cod_materia) {
        this.cod_materia = cod_materia;
        return cod_materia;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
