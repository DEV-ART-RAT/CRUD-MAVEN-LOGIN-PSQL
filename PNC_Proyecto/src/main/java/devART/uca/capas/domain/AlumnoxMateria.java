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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementExpediente")
    @GenericGenerator(name = "incrementExpediente", strategy = "increment")
    @Column(name="c_alumnoxmateria")
    private Integer codigo;

    @Transient
    private Integer c_materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_materia", nullable = false)
    private Materia materia;


    @Transient
    private Integer c_expediente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_expediente", nullable = false)
    private Expediente expediente;


    @NotNull(message = "Este campo no puede estar vacio")
    @Column(name="s_nota")
    @Size(message = "La nota no debe tener mas de 3 caracteres", max = 3)
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String nota;

    @Column(name = "s_estado")
    private String estado;

    public AlumnoxMateria(){}
    public Integer getC_materia() { return c_materia; }
    public void setC_materia(Integer c_materia) {
        this.c_materia = c_materia;
    }



    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Integer getC_expediente() {
        return c_expediente;
    }

    public void setC_expediente(Integer c_expediente) {
        this.c_expediente = c_expediente;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
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
