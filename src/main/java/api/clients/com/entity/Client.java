package api.clients.com.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "client_seq_generator", sequenceName = "client_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_generator")
	private Integer id;
	@NotNull
	@NotEmpty
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	@NotNull
	@Column(name = "numero_documento")
	private String numeroDocumento;
	@NotNull
	@NotEmpty
	@Column(name = "primer_nombre")
	private String primerNombre;
	@NotNull
	@Column(name = "segundo_nombre")
	private String segundoNombre;
	@NotNull
	@NotEmpty
	@Column(name = "primer_apellido")
	private String primerApellido;
	@NotNull
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	@NotNull
	private String telefono;
	@NotNull
	@NotEmpty
	private String direccion;
	@NotNull
	@NotEmpty
	@Column(name = "ciudad_residencia")
	private String ciudadResidencia;
}
