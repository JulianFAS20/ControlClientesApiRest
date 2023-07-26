package api.clients.com.service.interfaces;

import java.util.List;

import api.clients.com.requests.RequestClient;
import api.clients.com.requests.RequestUpdateClient;
import api.clients.com.response.ResponseClient;

public interface ClientsService {
	List<ResponseClient> getClients();
	
	ResponseClient getClientById(int id);
	
	ResponseClient getClientByTypeAndNumberDocument(RequestClient request);
	
	ResponseClient createClient(RequestUpdateClient request);
	
	ResponseClient updateClient(RequestUpdateClient request);
	
	boolean deleteClientById(int id);
	
	boolean deleteClient(RequestClient request);
}
