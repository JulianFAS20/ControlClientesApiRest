package api.clients.com.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RequestClient implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@NotNull(message = "El numeroDocumento no puede estar nulo")
	@NotEmpty(message = "El numeroDocumento no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El numeroDocumento solo puede contener numeros")
	private String numeroDocumento;
	@NotNull(message = "El tipoDocumento no puede ser nulo")
	@NotEmpty(message = "El tipoDocumento no puede estar vacio")
	@Pattern(regexp = "^(C|P)$", message = "El tipoDocumento solo puede ser C (cedulade ciudadania) y P (Pasaporte)")
	private String tipoDocumento;
}
