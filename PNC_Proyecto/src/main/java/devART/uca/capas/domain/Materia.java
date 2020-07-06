package devART.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Size(message = "La materia no debe superar los 30 caracteres", max = 30)
	@NotEmpty(message = "Este campo no puede estar vacio")
	private String materia;
	
	/*
	@OneToMany(mappedBy="categoria", fetch = FetchType.EAGER)
	private List<Expediente> expediente;
	*/

	public Materia() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	/*
	public List<Expediente> getLibros() {
		return expediente;
	}

	public void setEspedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
	*/
}
