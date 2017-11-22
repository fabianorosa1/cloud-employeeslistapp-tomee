package com.sap.employeeslist.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.employeeslist.facade.common.BaseSessionFacade;
import com.sap.employeeslist.persistence.dao.EmployeeDao;
import com.sap.employeeslist.persistence.model.Employee;

/**
 * Façade for core methods.
 * 
 * @author fabianorosa
 *
 */
@LocalBean
@Stateless
public class CoreFacade extends BaseSessionFacade {
	private static final long serialVersionUID = -43587395734957L;
	private static final Logger logger = LoggerFactory.getLogger(CoreFacade.class);

	@EJB
	private EmployeeDao employeeDao;	

	/**
	 * Constructor for CoreFacade.
	 */
	public CoreFacade() {
		super();
		logger.debug("[ENTER] Constructor CoreFacade");

		logger.debug("[EXIT] Constructor CoreFacade");
	}

	/**
	 * Retrieves a list of global parameters given a nameID.
	 * 
	 * @param nameID
	 *            the nameId of the parameter
	 * @return list of parameters
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Employee> getEmployees() {
		logger.trace("[ENTER] getEmployees");

		try {
			return this.employeeDao.findAll();
		} finally {
			logger.trace("[EXIT] getEmployees");
		}
	}
}
