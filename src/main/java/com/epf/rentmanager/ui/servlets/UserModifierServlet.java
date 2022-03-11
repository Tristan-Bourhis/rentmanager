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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/modifier")
public class UserModifierServlet extends HttpServlet {

	@Autowired
	ClientService clientService;
	
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
			Client client = clientService.findById(id);
	    	request.setAttribute("nom", client.getNom());
	    	request.setAttribute("prenom", client.getPrenom());
	    	request.setAttribute("email", client.getEmail());
	    	request.setAttribute("dateMax", LocalDate.now().withYear(LocalDate.now().getYear()-18));
	    	request.setAttribute("naissance", client.getNaissance());
	    	
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/modifier.jsp").forward(request, response);
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Client client = clientService.findById(id);
			client.setNom((request.getParameter("last_name"))); 
			client.setPrenom((request.getParameter("first_name"))); 
			client.setEmail((request.getParameter("email"))); 	
			client.setNaissance(LocalDate.parse(request.getParameter("naissance")));
			clientService.modifier(client);
		} catch (NumberFormatException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	response.sendRedirect("/rentmanager/users");
    }
}
