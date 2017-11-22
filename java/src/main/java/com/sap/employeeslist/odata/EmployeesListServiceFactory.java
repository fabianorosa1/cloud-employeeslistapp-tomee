
package com.sap.employeeslist.odata;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

import com.sap.employeeslist.persistence.common.JpaEntityManagerFactory;

public class EmployeesListServiceFactory extends ODataJPAServiceFactory {
	private static final String PERSISTENCE_UNIT_NAME = "pu-cf";

	@Override
	public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
		ODataJPAContext oDataJPAContext = this.getODataJPAContext();

		try {
			EntityManagerFactory emf = JpaEntityManagerFactory.getEntityManagerFactory();
			oDataJPAContext.setEntityManagerFactory(emf);
			oDataJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

			return oDataJPAContext;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
