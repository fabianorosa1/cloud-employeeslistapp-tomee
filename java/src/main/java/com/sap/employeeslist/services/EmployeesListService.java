package com.sap.employeeslist.services;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeesListService
 */
@WebServlet(urlPatterns = "/employeeslist.svc/*", loadOnStartup = 2, initParams = {
		@WebInitParam(name= "javax.ws.rs.Application", value="org.apache.olingo.odata2.core.rest.app.ODataApplication"),
		@WebInitParam(name= "org.apache.olingo.odata2.service.factory", value= "com.sap.employeeslist.odata.EmployeesListServiceFactory"),}
)
@ServletSecurity(@HttpConstraint(rolesAllowed = {"Display"}))

public class EmployeesListService extends HttpServlet {
	private static final long serialVersionUID = 5689120501367518278L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CoreService c;
		try {
			c = new CoreService();
			c.getEmployees();
		
		
		response.getWriter().println(c.getEmployees()); //request.getUserPrincipal()
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}