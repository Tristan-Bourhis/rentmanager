package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents/update")
public class RentsUpdateServlet extends HttpServlet {
	
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	
	int id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);		
	}
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				request.setAttribute("listVehicule", vehicleService.findAll());
				request.setAttribute("listClient", clientService.findAll());
				id = Integer.parseInt(request.getParameter("id"));
				Reservation reservation = reservationService.findResaById(id);
		    	request.setAttribute("reservation", reservation);
		    	request.setAttribute("vehicule", vehicleService.findById(reservation.getVehiculeId()));
		    	request.setAttribute("client", clientService.findById(reservation.getClientId()));
		    	request.setAttribute("debut", reservation.getDebut());
		    	request.setAttribute("fin", reservation.getFin());
			} catch (NumberFormatException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp").forward(request, response);
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	try {
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    		int client_id = Integer.parseInt(request.getParameter("client"));
	        	int vehicle_id = Integer.parseInt(request.getParameter("car"));
	        	LocalDate debut = LocalDate.parse(request.getParameter("begin"));
	        	LocalDate fin = LocalDate.parse(request.getParameter("end"));
	        	Reservation reservation = new Reservation(id, client_id, vehicle_id, debut, fin);
	        	
	        	reservationService.update(reservation);
			} catch (NumberFormatException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
	    	response.sendRedirect("/rentmanager/rents");
	    }
}
