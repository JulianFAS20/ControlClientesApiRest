package api.clients.com.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RequestUpdateClient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = "El tipoDocumento no puede ser nulo")
	@NotEmpty(message = "El tipoDocumento no puede estar vacio")
	@Pattern(regexp = "^(C|P)$", message = "El tipoDocumento solo puede ser C (cedulade ciudadania) y P (Pasaporte)")
	private String tipoDocumento;
	@NotNull(message = "El numeroDocumento no puede ser nulo")
	@NotEmpty(message = "El numeroDocumento no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El numeroDocumento solo debe contener numeros")
	private String numeroDocumento;
	@NotNull(message = "El primerNombre no puede ser nulo")
	@NotEmpty(message = "El primerNombre no puede estar vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$", message = "El primerNombre solo debe contener letras")
	private String primerNombre;
	@NotNull(message = "El segundoNombre no puede ser nulo")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$", message = "El segundoNombre solo debe contener letras")
	private String segundoNombre;
	@NotNull(message = "El primerApellido no puede ser nulo")
	@NotEmpty(message = "El primerApellido no puede estar vacio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$", message = "El primerApellido solo debe contener letras")
	private String primerApellido;
	@NotNull(message = "El segundoApellido no puede ser nulo")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]*$", message = "El segundoApellido solo debe contener letras")
	private String segundoApellido;
	@NotNull(message = "El telefono no puede ser nulo")
	@NotEmpty(message = "El telefono no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El telefono solo debe contener numeros")
	private String telefono;
	@NotNull(message = "La direccion no puede ser nulo")
	@NotEmpty(message = "La direccion no puede estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9\\s#\\-.,áéíóúÁÉÍÓÚñÑ]+( (norte|sur))?$", message = "La direccion debe ser de esta forma Ejemplo: Carrera 10 #45-67 sur")
	private String direccion;
	@NotNull(message = "La ciudadResidencia no puede ser nula")
	@NotEmpty(message = "La ciudadResidencia no puede estar vacia")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$", message = "La ciudad solo puede contener letras")
	private String ciudadResidencia;
}
