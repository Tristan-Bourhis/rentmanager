package com.epf.rentmanager.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicule;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class VehicleDao {

	private VehicleDao() {
	}


	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";

	public int update(Vehicule vehicule) throws DaoException {
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(UPDATE_VEHICLE_QUERY);
			System.out.println(vehicule);
			pstat.setInt(3, vehicule.getId());			
			pstat.setString(1,vehicule.getConstructeur());			
			pstat.setInt(2,vehicule.getNbPlaces());						
			pstat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int create(Vehicule vehicule) throws DaoException {
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(CREATE_VEHICLE_QUERY);

			pstat.setString(1, vehicule.getConstructeur());

			pstat.setInt(2, vehicule.getNbPlaces());

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
			PreparedStatement pstat = con.prepareStatement(DELETE_VEHICLE_QUERY);
			pstat.setInt(1, id);
			pstat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Optional<Vehicule> findById(int id) throws DaoException {
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_VEHICLE_QUERY);
			pstat.setInt(1, id);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			String constructeur = rs.getString("constructeur");
			int nbPlace = rs.getInt("nb_places");
			Vehicule v = new Vehicule(id, constructeur, nbPlace);
			con.close();
			return Optional.of(v);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public List<Vehicule> findAll() throws DaoException {
		List<Vehicule> liste_vehicule = new ArrayList<Vehicule>();
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(FIND_VEHICLES_QUERY);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			do {
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");
				Vehicule v = new Vehicule(id, constructeur, nbPlaces);
				liste_vehicule.add(v);
			} while (rs.next());
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste_vehicule;

	}

	public int count() {
		int nb_v = 0;
		try {
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pstat = con.prepareStatement(COUNT_VEHICLES_QUERY);
			ResultSet rs = pstat.executeQuery();
			rs.next();
			nb_v = rs.getInt(1);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nb_v;
	}

}
