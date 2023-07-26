package api.clients.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.clients.com.dao.ClientsDao;
import api.clients.com.entity.Client;
import api.clients.com.requests.RequestClient;
import api.clients.com.requests.RequestUpdateClient;
import api.clients.com.response.ResponseClient;
import api.clients.com.service.interfaces.ClientsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientsImplement implements ClientsService {

	@Autowired
	ClientsDao clientsDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ResponseClient>  getClients() {
		try {
			List<Client> clients = new ArrayList<>();
			List<ResponseClient> response = new ArrayList<>();
			clients.addAll(clientsDao.findAll());
			if (!clients.isEmpty()) {
				for (Client client : clients) {
					ResponseClient rTicketAux = convertClientToResponseClient(client);
					response.add(rTicketAux);
				}
			}
			return response;
		} catch (Exception e) {
			// Si la consulta de los tiquetes se totea devolvemos null
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseClient getClientById(int id) {
		try {
			Client ticket = clientsDao.findById(id).orElse(new Client());
			return convertClientToResponseClient(ticket);
		} catch (Exception e) {
			// Si la consulta de los tiquetes se totea devolvemos null
			return null;
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseClient getClientByTypeAndNumberDocument(RequestClient request) {
		try {
			Client clientExist = clientsDao.getByNumeroDocumentoAndTipoDocumento(request.getNumeroDocumento(), request.getTipoDocumento());
			return convertClientToResponseClient(clientExist);
		} catch (Exception e) {
			// Si la consulta de los tiquetes se totea devolvemos null
			return null;
		}
	}

	@Override
	@Transactional
	public ResponseClient createClient(RequestUpdateClient request) {
		try {			
			ResponseClient response = new ResponseClient();
			Client clientAux = convertRequestUpdateClientToClient(request);
			Client clientExist = clientsDao.getByNumeroDocumentoAndTipoDocumento(clientAux.getNumeroDocumento(), clientAux.getTipoDocumento());
			
			if (clientExist != null) {
				log.error("El cliente con tipo de documento " + clientExist.getTipoDocumento() + " y numero " + clientExist.getNumeroDocumento() + " ya existe");
				response.setMessageError("El cliente con tipo de documento " + clientExist.getTipoDocumento() + " y numero " + clientExist.getNumeroDocumento() + " ya existe");
				return response;
			}
			
			clientsDao.save(clientAux);
			clientAux = clientsDao.getByNumeroDocumentoAndTipoDocumento(clientAux.getNumeroDocumento(), clientAux.getTipoDocumento());
			response = convertClientToResponseClient(clientAux);
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public ResponseClient updateClient(RequestUpdateClient request) {
		try {
			ResponseClient response = new ResponseClient();
			Client clientAux = new Client();
			String msgError = "";
			msgError = "El cliente con tipo de documento " + request.getTipoDocumento() + " y numero " 
			+ request.getNumeroDocumento() + " no fue encontrado en la BD, por favor verifique su existencia";
			clientAux = clientsDao.getByNumeroDocumentoAndTipoDocumento(request.getNumeroDocumento(), request.getTipoDocumento());
			if(clientAux == null) {
				log.error(msgError);
				response.setMessageError(msgError);
				return response;
			} 
			
			clientAux = convertRequestUpdateClientToClient(request);			
			clientsDao.save(clientAux);
			clientAux = clientsDao.getByNumeroDocumentoAndTipoDocumento(clientAux.getNumeroDocumento(), clientAux.getTipoDocumento());
			response = convertClientToResponseClient(clientAux);
			return response;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteClientById(int id) {
		try {
			clientsDao.deleteById(id);
			return true;
		} catch (Exception e) {
			// No pudo eliminarse el ticket
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteClient(RequestClient request) {
		try {
			Client clientExist = clientsDao.getByNumeroDocumentoAndTipoDocumento(request.getNumeroDocumento(), request.getTipoDocumento());
			clientsDao.delete(clientExist);
			return true;
		} catch (Exception e) {
			// No pudo eliminarse el ticket
			return false;
		}
	}
	
	public Client convertRequestUpdateClientToClient(RequestUpdateClient request) {
		try {
			Client convert = new Client();
			convert.setId(request.getId());
			convert.setTipoDocumento(request.getTipoDocumento());
			convert.setNumeroDocumento(request.getNumeroDocumento());
			convert.setPrimerNombre(request.getPrimerNombre());
			convert.setSegundoNombre(request.getSegundoNombre());
			convert.setPrimerApellido(request.getPrimerApellido());
			convert.setSegundoApellido(request.getSegundoApellido());
			convert.setTelefono(request.getTelefono());
			convert.setDireccion(request.getDireccion());
			convert.setCiudadResidencia(request.getCiudadResidencia());
			return convert;
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResponseClient convertClientToResponseClient(Client client) {
		try {
			ResponseClient convert = new ResponseClient();
			convert.setId(client.getId());
			convert.setTipoDocumento(client.getTipoDocumento());
			convert.setNumeroDocumento(client.getNumeroDocumento());
			convert.setPrimerNombre(client.getPrimerNombre());
			convert.setSegundoNombre(client.getSegundoNombre());
			convert.setPrimerApellido(client.getPrimerApellido());
			convert.setSegundoApellido(client.getSegundoApellido());
			convert.setTelefono(client.getTelefono());
			convert.setDireccion(client.getDireccion());
			convert.setCiudadResidencia(client.getCiudadResidencia());
			return convert;
		} catch (Exception e) {
			return null;
		}
	}
}
