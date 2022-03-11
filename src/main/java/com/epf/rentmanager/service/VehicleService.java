package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicule;
import com.epf.rentmanager.dao.VehicleDao;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
//	public static VehicleService instance;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	/*public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}*/
	
	public int update(Vehicule vehicule) throws ServiceException {
		try {
			return this.vehicleDao.update(vehicule);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int create(Vehicule vehicle) throws ServiceException {
		try {
			return this.vehicleDao.create(vehicle);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
	}
	
	public int delete(int id) throws ServiceException {
		try {
			return this.vehicleDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Vehicule findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public List<Vehicule> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int count(){
		return this.vehicleDao.count();
	}
	
}
