package api.clients.com.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ResponseClient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String tipoDocumento;
	private String numeroDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String telefono;
	private String direccion;
	private String ciudadResidencia;
	@JsonIgnore
	private String messageError;
}
