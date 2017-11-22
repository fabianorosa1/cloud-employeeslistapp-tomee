package com.sap.employeeslist.services.common;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.employeeslist.facade.common.BaseSessionFacade;

/**
 * Implements general methods for Dash usermgmt services.
 * 
 * @author fabianorosa
 *
 */
public abstract class BaseService implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	private static final long serialVersionUID = -870481631880942356L;
	protected static final Response RESPONSE_OK = Response.ok("ok").build();
	protected static final Response RESPONSE_BAD = Response.status(Status.BAD_REQUEST).build();

	@javax.ws.rs.core.Context
	protected HttpServletRequest request;

	@javax.ws.rs.core.Context
	protected HttpServletResponse response;

	public BaseService() {		
	}

	/**
	 * Looks up EJB for a facade.
	 * 
	 * @param clazz
	 *            the clazz of the facade
	 * @return the EJB
	 * @throws NamingException
	 *             if the name of the EJB does not exist
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseSessionFacade> T lookupEJBFacade(Class<T> clazz) throws NamingException {
		logger.trace("EJB Facade Class name: " + clazz.getSimpleName());

		InitialContext ic = new InitialContext();
		return (T) ic.lookup("java:comp/env/ejb/" + clazz.getSimpleName());
	}
}
