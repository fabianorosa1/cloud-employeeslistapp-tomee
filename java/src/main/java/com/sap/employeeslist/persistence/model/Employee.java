package com.sap.employeeslist.persistence.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "\"MainEntities.Employee\"")
public class Employee implements Serializable {
	private static final long serialVersionUID = 12153453425L;

	@Id
	@SequenceGenerator(name="Employee_GENERATOR", sequenceName="\"employeeSeqId\"", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Employee_GENERATOR")
	@Column(name = "\"id\"")
	private int id;

	@Column(name = "\"firstName\"", length = 100)
	private String firstName;

	@Column(name = "\"lastName\"", length = 100)
	private String lastName;

	public Employee() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}