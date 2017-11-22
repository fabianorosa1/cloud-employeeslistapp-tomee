/**
 * DAO for Domain.
 * @autor fabianorosa
 */
package com.sap.employeeslist.persistence.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.employeeslist.persistence.common.AbstractJpaDAO;
import com.sap.employeeslist.persistence.model.Employee;

@LocalBean
@Stateless
public class EmployeeDao extends AbstractJpaDAO<Employee> {
	private static final Logger logger = LoggerFactory.getLogger(Employee.class);

	/**
	 * Constructor for DomainDao.
	 */
	public EmployeeDao() {
		super();
		setClazz(Employee.class);
	}
}
