package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class UserDetailServlet extends HttpServlet{
	
	int id;
	
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	try {
    		int nbResa=0;
    		ArrayList <Reservation>list_resa = new ArrayList<>();
			id = Integer.parseInt(request.getParameter("id"));
    		list_resa = reservationService.findAllResaByClientId(id);
			request.setAttribute("user", clientService.findById(id));
			request.setAttribute("listResa", list_resa);
			request.setAttribute("listVehicule", vehicleService.findAll());
			if(list_resa != null) {
				nbResa = list_resa.size();
			}
			request.setAttribute("nbResa", nbResa);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
