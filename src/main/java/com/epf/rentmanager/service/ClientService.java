package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.dao.ClientDao;

@Service
public class ClientService {

	private ClientDao clientDao;
//	public static ClientService instance;
	
	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	/*public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}*/
	
	
	public boolean mailDoesntExist(Client client) throws ServiceException {   
		ArrayList <Client> liste_client = new ArrayList<Client>();
		liste_client = (ArrayList<Client>) this.findAll();
		for(Client clt : liste_client) {
			if(clt.getEmail().equals(client.getEmail())) {
				return false;
			}
		}		
		return true;
	}
	
	public int modifier(Client client) throws ServiceException {
		try {
			return this.clientDao.modifier(client);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int create(Client client) throws ServiceException {
		try {
			return this.clientDao.create(client);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}

	public int delete(int id) throws ServiceException {
		try {
			return this.clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	public List<Client> findByChar(String chaine) throws ServiceException {
		try {
			ArrayList <Client>liste_cherche = new ArrayList<>();
			ArrayList <Client>liste_client = new ArrayList<>();
			liste_client = (ArrayList<Client>) this.clientDao.findAll();			
			for(Client client : liste_client) {
				if(client.getNom().contains(chaine) || client.getPrenom().contains(chaine)) {
					liste_cherche.add(client);
				}
			}
			return liste_cherche;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

	public int count(){
		return this.clientDao.count();
	}
	
}
