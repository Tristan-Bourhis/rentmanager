package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicule;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {
	@Autowired
	VehicleService vehicleService;
	
	int id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Vehicule vehicule = vehicleService.findById(id);
	    	request.setAttribute("constructeur", vehicule.getConstructeur());
	    	request.setAttribute("nb_places", vehicule.getNbPlaces());
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			Vehicule vehicule = vehicleService.findById(id);
			vehicule.setConstructeur((request.getParameter("manufacturer"))); 
			vehicule.setNbPlaces((Integer.parseInt(request.getParameter("seats"))));  
			vehicleService.update(vehicule);
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	response.sendRedirect("/rentmanager/cars");
    }
}
