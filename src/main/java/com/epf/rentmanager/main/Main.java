package com.epf.rentmanager.main;

import java.util.Date;

import javax.naming.spi.ResolveResult;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicule;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import config.AppConfiguration;

public class Main {
    
    public static void main(String[]args){

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		VehicleService vehicleService = context.getBean(VehicleService.class);
		ReservationService reservationService = context.getBean(ReservationService.class);
		
		try {
			System.out.println(clientService.findByChar("z"));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   

}
