/**
 * Base Facade for session.
 * @author fabianorosa
 *
 */

package com.sap.employeeslist.facade.common;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.employeeslist.persistence.common.AbstractJpaDAO;

public abstract class BaseSessionFacade implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(BaseSessionFacade.class);
	private static final long serialVersionUID = -9034236482364921L;

	/**
	 * Constructor for BaseSessionFacade.
	 */
	public BaseSessionFacade() {
		logger.debug("[ENTER] Constructor BaseSessionFacade");

		logger.debug("[EXIT] Constructor BaseSessionFacade");
	}

	/**
	 * Looks up a EJB for a DAO.
	 * 
	 * @param clazz
	 *            the class
	 * @return the DAO
	 * @throws NamingException
	 *             if the dao name is incorrect
	 */
	@SuppressWarnings("unchecked")
	public <T extends AbstractJpaDAO<?>> T lookupEJBDao(Class<T> clazz) throws NamingException {
		logger.trace("DAO Class name: " + clazz.getSimpleName());

		InitialContext ic = new InitialContext();
		return (T) ic.lookup("java:comp/env/ejb/" + clazz.getSimpleName());
	}
}
