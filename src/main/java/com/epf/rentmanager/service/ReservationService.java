package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

@Service
public class ReservationService {
	
	private ReservationDao reservationDao;
	
	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	public int update(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.update(reservation);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int create(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.create(reservation);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteClientAssociation(int id) throws ServiceException {
		while(this.findResaByClientId(id)!=null) {
			this.delete(this.findResaByClientId(id).getId());
		}
		return 0;
	}
	
	public int deleteVehicleAssociation(int id) throws ServiceException {
		while(this.findResaByVehicleId(id)!=null) {
			this.delete(this.findResaByVehicleId(id).getId());
		}
		return 0;
	}
	
	public int delete(int id) throws ServiceException {
		try {
			return this.reservationDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Reservation findResaByClientId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByClientId(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reservation> findAllResaByClientId(int clientId) throws ServiceException {
		try {
			return this.reservationDao.findAllResaByClientId(clientId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reservation> findAllResaByVehicleId(int vehicleId) throws ServiceException {
		try {
			return this.reservationDao.findAllResaByVehicleId(vehicleId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Reservation findResaByVehicleId(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaByVehicleId(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Reservation findResaById(int id) throws ServiceException {
		try {
			return this.reservationDao.findResaById(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int count() {
		return this.reservationDao.count();
	}
}
