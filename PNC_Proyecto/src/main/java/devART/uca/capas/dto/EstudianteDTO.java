package devART.uca.capas.dto;

//Entidad, Tabla

public class EstudianteDTO {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="c_usuario")
//	private Integer codigo;
	private String nombre;
	private String apellido;
	public EstudianteDTO() {
	}


//	public Integer getCodigo() {
//		return codigo;
//	}
//
//
//	public void setCodigo(Integer codigo) {
//		this.codigo = codigo;
//	}


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


	
}