package api.clients.com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.clients.com.requests.RequestClient;
import api.clients.com.requests.RequestUpdateClient;
import api.clients.com.response.ResponseClient;
import api.clients.com.service.interfaces.ClientsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("/ClientsApi") // Ruta base del controlador
public class ClientsController {
	@Autowired
	ClientsService clientService;
	
	@GetMapping("/getClients") // Ruta especifica del endpoint de primer nivel
	public ResponseEntity<?> getClients(){
		List<ResponseClient> list = new ArrayList<>();
		try {
			log.info("Consultando la lista de clientes");
			list = clientService.getClients();
			if (list.isEmpty()) {
				return new ResponseEntity<List<ResponseClient>>(list, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<List<ResponseClient>>(list, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getClientById/{id}")
	public ResponseEntity<?> getClientById(@PathVariable Integer id){
		ResponseClient response = new ResponseClient();
		try {
			log.info("Consultando cliente");
			response = clientService.getClientById(id);
			if (response == null) {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getClientByTypeAndNumberDocument")
	public ResponseEntity<?> getClientByTypeAndNumberDocument(@Valid @RequestBody RequestClient request, BindingResult result){
		if (result.hasErrors()) {
			return new ResponseEntity<String>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		ResponseClient response = new ResponseClient();
		try {
			response = clientService.getClientByTypeAndNumberDocument(request);
			if (response == null) {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createClient")
	public ResponseEntity<?> createClient(@Valid @RequestBody RequestUpdateClient request, BindingResult result){
		if (result.hasErrors()) {
			return new ResponseEntity<String>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		try {
			ResponseClient response = clientService.createClient(request);
			if (response == null) {
				return new ResponseEntity<String>("Ocurrio un error durante la creacion del cliente", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else if(response.getMessageError() != null && !response.getMessageError().isEmpty()){
				return new ResponseEntity<String>(response.getMessageError(), HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@PutMapping("/updateClient")
	public ResponseEntity<?> updateClient(@Valid @RequestBody RequestUpdateClient request, BindingResult result){
		if (result.hasErrors()) {
			return new ResponseEntity<String>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		try {
			ResponseClient response = clientService.updateClient(request);
			if (response == null) {
				return new ResponseEntity<String>("Ocurrio un error durante la actualizacion del cliente", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else if(response.getMessageError() != null && !response.getMessageError().isEmpty()){
				return new ResponseEntity<String>(response.getMessageError(), HttpStatus.BAD_REQUEST);
			}
			else {
				return new ResponseEntity<ResponseClient>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteClientById/{id}")
	public ResponseEntity<?> deleteClientById(@PathVariable Integer id){
		try {
			log.info("Eliminando cliente por id");
			boolean delete = clientService.deleteClientById(id);
			if (delete) {
				return new ResponseEntity<String>("Cliente Eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No se pudo eliminar el cliente", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteClient")
	public ResponseEntity<?> deleteClient(@Valid @RequestBody RequestClient request, BindingResult result){
		if (result.hasErrors()) {
			return new ResponseEntity<String>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		try {
			log.info("Eliminando cliente");
			boolean delete = clientService.deleteClient(request);
			if (delete) {
				return new ResponseEntity<String>("Cliente Eliminado", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("No se pudo eliminar el Cliente", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
