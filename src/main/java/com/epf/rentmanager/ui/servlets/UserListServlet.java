package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@WebServlet("/users")
public class UserListServlet extends HttpServlet{

	@Autowired
    ClientService clientService;
	@Autowired
    ReservationService reservationService;
    private static final long serialVersionID = 11;
    
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            request.setAttribute("listUsers", this.clientService.findAll());
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
        }
        catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response, String recherche) throws ServletException, IOException{
        	try {
        		if(recherche != null) {
        			request.setAttribute("listUsers", this.clientService.findByChar(recherche));
        		}else {
        			request.setAttribute("listUsers", this.clientService.findAll());
        		}				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	try {
    		int id = Integer.parseInt(request.getParameter("id"));
    		if(id!=0) {
    			if(reservationService.findAllResaByClientId(id) != null) {
            		reservationService.deleteClientAssociation(id);
    			}
    			clientService.delete(id);
    		}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        doGet(request, response, request.getParameter("nom"));
    }
}
