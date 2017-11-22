package com.sap.employeeslist.persistence.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * This is the base {@link MappedSuperclass} for all the entities in the
 * application. It implements the Id and the created and modified time stamps.
 * It also includes the callback methods for populating the timestamp values,
 * and implements hashcode and equals based on the id.
 * <p/>
 * The abstract {@link BaseEntity#getDisplayText()} method provides a nice way
 * to get a textual representation of who or what the entity is (course or
 * person name). Since it is baked into the superclass, it will be available for
 * all entity classes.
 * 
 * 
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@Column(name = "\"id\"")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "\"createdOn\"")
	private Date createdOn;

	@Column(name = "\"modifiedOn\"")
	private Date modifiedOn;

	/**
	 * Empty Constructor for BaseEntity.
	 */
	public BaseEntity() {

	}

	/**
	 * Gets the id field.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id field.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the createdOn field.
	 * 
	 * @return the createdOn field
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * Sets the createdOn field.
	 * 
	 * @param createdOn
	 *            the createdOn value to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the modifiedOn field.
	 * 
	 * @return the modifiedOn field
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * Sets the modifiedOn field.
	 * 
	 * @param modifiedOn
	 *            the modifiedOn value to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Transient
	public abstract String getDisplayText();


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDisplayText() == null) ? 0 : getDisplayText().hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
