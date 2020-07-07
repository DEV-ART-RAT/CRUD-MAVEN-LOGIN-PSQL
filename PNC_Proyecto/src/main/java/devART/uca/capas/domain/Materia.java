package devART.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
