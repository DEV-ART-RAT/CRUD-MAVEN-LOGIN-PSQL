package devART.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(schema="public", name="materia")
public class Materia {

	@Id
	@Column(name="cod_materia")
	@Size(message = "La materia no debe superar los 6 caracteres", max = 6)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String codigo;
	
	@Column(name="nombre_materia")
	@Size(message = "La materia no debe superar los 50 caracteres", max = 50)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String nombreMateria;

	@Column(name="descripcion_materia")
	@Size(message = "La materia no debe superar los 100 caracteres", max = 100)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String descripcion;

	@Column(name="estado_materia")
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String estado;

//	@OneToMany(mappedBy = "alumnoxmateria", fetch = FetchType.LAZY)
//	private List<AlumnoxMateria> alumnoxMateriaList;
//
//	public List<AlumnoxMateria> getAlumnoxMateriaList() {
//		return alumnoxMateriaList;
//	}
//
//	public void setAlumnoxMateriaList(List<AlumnoxMateria> alumnoxMateriaList) {
//		this.alumnoxMateriaList = alumnoxMateriaList;
//	}


	public Materia() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String materia) {
		this.nombreMateria = materia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
