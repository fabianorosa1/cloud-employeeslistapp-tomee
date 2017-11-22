/**
 * Services for getting data from ContentCards, GlobalParameters and Domain.
 * 
 * @author fabiano.a.rosa
 *
 */
package com.sap.employeeslist.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.dbcp2.BasicDataSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.employeeslist.facade.CoreFacade;
import com.sap.employeeslist.persistence.model.Employee;
import com.sap.employeeslist.services.common.BaseService;

public class CoreService extends BaseService {
	private static final long serialVersionUID = -5262342342342L;
	//private static final Logger logger = LoggerFactory.getLogger(CoreService.class);

	@EJB
	private final CoreFacade coreFacade;

	/**
	 * Constructor for CoreService.
	 * 
	 * @throws NamingException
	 * 
	 */
	public CoreService() throws NamingException {
		System.out.println("[ENTER] Constructor CoreService");

		// lookup the EJBs references for use in service
		this.coreFacade = this.lookupEJBFacade(CoreFacade.class);

		System.out.println("[EXIT] Constructor CoreService");
	}

	/**
	 * https://p1943057769trial-trial-dev-employeeslist-java.cfapps.eu10.hana.ondemand.com/core/employees
	 * 
	 * @return
	 */
	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees() {
		System.out.println("SERVICE employees!!!!");
		return this.coreFacade.getEmployees();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		System.out.println("SERVICE test !!!!");
		List<String> returns = new ArrayList<String>();

		try {
			System.out.println("$$$$$$$$$$$$$$$$ processDatabase JDBC $$$$$$$$$$$$$$$$");
			processDatabase(getConnJDBC());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on loading processDatabase JDBC: " + e.getMessage());
			returns.add(e.getMessage());
		}

		try {
			System.out.println("$$$$$$$$$$$$$$$$ processDatabase DS $$$$$$$$$$$$$$$$");
			processDatabase(getConnDS());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on loading processDatabase DS: " + e.getMessage());
			returns.add(e.getMessage());
		}

		try {
			System.out.println("$$$$$$$$$$$$$$$$ Create entity in JPA $$$$$$$$$$$$$$$$");
			Employee employee = new Employee();
			employee.setFirstName("Service teste");
			employee.setLastName(this.request.getSession().getId());
			
			this.coreFacade.createEmployees(employee);
			System.out.println("Employees list: " + this.coreFacade.getEmployees());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Create entity in JPA: " + e.getMessage());
			returns.add(e.getMessage());
		}

		
		return returns.toString();
	}

	/**
	 * @throws SQLException
	 */
	private void processDatabase(Connection connection) throws SQLException {
		System.out.println("### connection: " + connection);
		System.out.println("### getClientInfo: " + connection.getClientInfo());

		DatabaseMetaData databaseMetaData = connection.getMetaData();
		System.out.println("### getURL: " + databaseMetaData.getURL());

		// Print TABLE_TYPE "TABLE"
		System.out.println("### Print TABLE_TYPE TABLE");

		ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
		System.out.println("Printing TABLE_TYPE \"TABLE\" ");
		System.out.println("----------------------------------");
		while (resultSet.next()) {
			// Print
			System.out.println("### TABLE_NAME: " + resultSet.getString("TABLE_NAME"));
		}

		System.out.println("### Print TABLE_TYPE SYSTEM TABLE");

		resultSet = databaseMetaData.getTables(null, null, null, new String[] { "SYSTEM TABLE" });
		System.out.println("Printing TABLE_TYPE \"SYSTEM TABLE\" ");
		System.out.println("----------------------------------");
		while (resultSet.next()) {
			// Print
			System.out.println("### SYS TABLE_NAME: " + resultSet.getString("TABLE_NAME"));
		}

//		System.out.println("### PS 1");
//		PreparedStatement ps = connection.prepareStatement("SELECT 1 FROM \"DUMMY\"");
//		System.out.println("### PreparedStatement: " + ps);
//		ResultSet rs = ps.executeQuery();
//		if (rs.next()) {
//			System.out.println("### ResultSet: " + rs.getString(1));
//		}
//		ps.close();
//		rs.close();
//
//		System.out.println("### PS 2");
//		ps = connection.prepareStatement(
//				"CREATE TABLE \"com.sap.employeeslist::EmployeeTeste\" (\"id\" INTEGER NOT NULL, \"firstName\" VARCHAR(100), \"lastName\" VARCHAR(100), PRIMARY KEY (\"id\"))");
//		System.out.println("### PreparedStatement: " + ps);
//		ps.execute();
//		ps.close();
	}

	/**
	 * The package name which contains all the model classes.
	 */
	private static JsonNode readCredentialsFromEnvironment() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("### VCAP_SERVICES: " + System.getenv("VCAP_SERVICES"));

		JsonNode actualObj = mapper.readTree(System.getenv("VCAP_SERVICES"));
		return actualObj.get("hanatrial").get(0).get("credentials");
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 */
	private Connection getConnJDBC() throws NamingException, IOException, SQLException {

		BasicDataSource ds = new BasicDataSource();

		JsonNode credentials = readCredentialsFromEnvironment();
		ds.setDriverClassName(credentials.get("driver").asText());
		ds.setUrl(credentials.get("url").asText());
		ds.setUsername(credentials.get("user").asText());
		ds.setPassword(credentials.get("password").asText());

		Connection connection = ds.getConnection();		
		ds.close();
		
		return connection;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	private Connection getConnDS() throws NamingException, SQLException {
		InitialContext ctx = new InitialContext();
		Context envContext = (Context) ctx.lookup("java:/comp/env");
		DataSource dsHana = (DataSource) envContext.lookup("jdbc/DefaultDB");

		Connection connection = dsHana.getConnection();

		return connection;
	}
}
