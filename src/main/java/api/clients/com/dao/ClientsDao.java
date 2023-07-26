package api.clients.com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import api.clients.com.entity.Client;

public interface ClientsDao extends JpaRepository<Client, Integer>{

	Client getByNumeroDocumentoAndTipoDocumento(String numeroDocumento, String tipoDocumento);
}
