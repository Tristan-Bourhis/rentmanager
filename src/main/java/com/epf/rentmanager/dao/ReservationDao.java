package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

	private ReservationDao() {}
	

	/*public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}*/
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATION_QUERY = "SELECT client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) FROM Reservation;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	
	
	public int update(Reservation reservation) throws DaoException {
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(UPDATE_RESERVATION_QUERY);
			
			pstat.setInt(5, reservation.getId());
			pstat.setInt(1, reservation.getClientId());
			pstat.setInt(2, reservation.getVehiculeId());
			pstat.setDate(3, Date.valueOf(reservation.getDebut()));
			pstat.setDate(4, Date.valueOf(reservation.getFin()));
			pstat.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int create(Reservation reservation) throws DaoException {

		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat=con.prepareStatement(CREATE_RESERVATION_QUERY);
			
			pstat.setInt(1,reservation.getClientId());
			
			pstat.setInt(2,reservation.getVehiculeId());

			pstat.setDate(3,Date.valueOf(reservation.getDebut()));

			pstat.setDate(4,Date.valueOf(reservation.getFin()));
			
			pstat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int delete(int id) throws DaoException {
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(DELETE_RESERVATION_QUERY);
			pstat.setInt(1, id);
			pstat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
	}

	
	public Reservation findResaByClientId(int clientId) throws DaoException {	
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstat.setInt(1, clientId);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			int vehicule_id=rs.getInt("vehicle_id");
			LocalDate debut_reservation = rs.getDate("debut").toLocalDate();
			LocalDate fin_reservation = rs.getDate("fin").toLocalDate();
			Reservation reservation = new Reservation(id, clientId, vehicule_id, debut_reservation, fin_reservation);	
			con.close();
			return reservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reservation> findAllResaByClientId(int clientId) throws DaoException {	
		ArrayList<Reservation> liste_res = new ArrayList<Reservation>();
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstat.setInt(1, clientId);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			do {
				int id = rs.getInt("id");
				int vehicule_id=rs.getInt("vehicle_id");
				LocalDate debut_reservation = rs.getDate("debut").toLocalDate();
				LocalDate fin_reservation = rs.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, clientId, vehicule_id, debut_reservation, fin_reservation);	
				liste_res.add(reservation);
			}while(rs.next());
			con.close();
			return liste_res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reservation> findAllResaByVehicleId(int vehicleId) throws DaoException {	
		ArrayList<Reservation> liste_res = new ArrayList<Reservation>();
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstat.setInt(1, vehicleId);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			do {
				int id = rs.getInt("id");
				int client_id=rs.getInt("client_id");
				LocalDate debut_reservation = rs.getDate("debut").toLocalDate();
				LocalDate fin_reservation = rs.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, client_id, vehicleId, debut_reservation, fin_reservation);	
				liste_res.add(reservation);
			}while(rs.next());
			con.close();
			return liste_res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Reservation findResaByVehicleId(int vehicle_id) throws DaoException {	
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstat.setInt(1, vehicle_id);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			int client_id=rs.getInt("client_id");
			LocalDate debut_reservation = rs.getDate("debut").toLocalDate();
			LocalDate fin_reservation = rs.getDate("fin").toLocalDate();
			Reservation reservation = new Reservation(id, client_id, vehicle_id, debut_reservation, fin_reservation);	
			con.close();
			return reservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		 
	}
	
	public Reservation findResaById(int id) throws DaoException {	
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATION_QUERY);
			pstat.setInt(1, id);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			int client_id=rs.getInt("client_id");
			int vehicle_id=rs.getInt("vehicle_id");
			LocalDate debut_reservation = rs.getDate("debut").toLocalDate();
			LocalDate fin_reservation = rs.getDate("fin").toLocalDate();
			Reservation reservation = new Reservation(id, client_id, vehicle_id, debut_reservation, fin_reservation);	
			con.close();
			return reservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		 
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> liste_res = new ArrayList<Reservation>();
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			do {
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				int vehicule_id=rs.getInt("vehicle_id");
				LocalDate debut_res = rs.getDate("debut").toLocalDate();
				LocalDate fin_res = rs.getDate("fin").toLocalDate();
				Reservation res = new Reservation(id, client_id, vehicule_id, debut_res, fin_res);	
				liste_res.add(res); 				
			}
			while(rs.next());
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liste_res;
		 
	}

	public int count(){
		int nb_rents=0;
		try {	
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(COUNT_RESERVATIONS_QUERY);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			nb_rents=rs.getInt(1);
			con.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nb_rents;

	}
}
