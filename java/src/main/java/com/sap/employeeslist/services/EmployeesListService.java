package com.sap.employeeslist.services;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet implementation class EmployeesListService
 */
@WebServlet(urlPatterns = "/employeeslist.svc/*", loadOnStartup = 2, initParams = {
		@WebInitParam(name= "javax.ws.rs.Application", value="org.apache.olingo.odata2.core.rest.app.ODataApplication"),
		@WebInitParam(name= "org.apache.olingo.odata2.service.factory", value= "com.sap.employeeslist.odata.EmployeesListServiceFactory"),}
)
public class EmployeesListService extends org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet {
	private static final long serialVersionUID = 5689120501367518278L;
}