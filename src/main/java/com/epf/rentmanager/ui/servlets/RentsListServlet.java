package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/rents")
public class RentsListServlet extends HttpServlet {

	@Autowired
    ReservationService reservationService;
	@Autowired
    ClientService clientService;
	@Autowired
    VehicleService vehicleService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("listRents", this.reservationService.findAll());
            request.setAttribute("listVehicule", this.vehicleService.findAll());
            request.setAttribute("listClient", this.clientService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
        } catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			reservationService.delete(Integer.parseInt(request.getParameter("id")));
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	doGet(request,response);
    }
    
}
