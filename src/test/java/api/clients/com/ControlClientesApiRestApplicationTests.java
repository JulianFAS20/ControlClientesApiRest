package api.clients.com;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.clients.com.response.ResponseClient;
import api.clients.com.service.interfaces.ClientsService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ControlClientesApiRestApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	@Autowired
	private ClientsService clientService;
	
	@Test
	void contextLoads() {
	}
	
	/**
	 * Esta prueba unitaria fallara si no se ha ejecutado el docker-compose.yml
	 * por lo cual no me podra traer el cliente especificado de la BD del contenedor
	 */
	@Test
    public void getClientById() throws Exception {
		// Realiza una solicitud GET a /api/mi-metodo y espera un codigo de estado 200
        MvcResult result = mockMvc.perform(get("/ClientsApi/getClientById/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Verifica el contenido de la respuesta
        String content = result.getResponse().getContentAsString();
        // Consultamos exactamente el mismo usuario perno a traves del consumo del servicio
        // sino llamando el metodo de su interfaz
        ResponseClient client1 = clientService.getClientById(1);
	    // Convertir el JSON a Objeto
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseClient clientToCompare = objectMapper.readValue(content, ResponseClient.class);
        // Asegurate de que el contenido sea el esperado
        assertEquals(client1.getPrimerNombre(), clientToCompare.getPrimerNombre());
    }
}
