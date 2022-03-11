package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/create2")
public class UserCreate2Servlet extends HttpServlet {
	@Autowired
	ClientService clientService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("dateMax", LocalDate.now().withYear(LocalDate.now().getYear()-18));
		request.setAttribute("visibility", "");
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create2.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LocalDate naissance_client = LocalDate.parse(request.getParameter("naissance")); 
    	Client client = new Client(request.getParameter("last_name"), request.getParameter("first_name"), request.getParameter("email"), naissance_client);    	
        try {
        	if(clientService.mailDoesntExist(client)) {
        		clientService.create(client);
        		response.sendRedirect("/rentmanager/users");
        	}else {
        		response.sendRedirect("/rentmanager/users/create2");
        	}        		
    	} catch (ServiceException e) {
    			// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
}
