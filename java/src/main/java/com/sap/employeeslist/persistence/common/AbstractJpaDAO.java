package com.sap.employeeslist.persistence.common;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;

/**
 * How to connect to HANA Database on SAP Cloud: neo.sh open-db-tunnel -h
 * us2.hana.ondemand.com -u S00xxxxxxxx -a d58f9c935 --id feu
 * https://help.sap.com/viewer/d4790b2de2f4429db6f3dff54e4d7b3a/Cloud/en-US/73e8d4c514f14a399c25711dd43f6975.html
 * 
 * @author fabianorosa
 *
 * @param <T>
 *            the type of the DAO
 */
public abstract class AbstractJpaDAO<T extends Serializable> {
	protected Class<T> entityClass;

	//@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Empty constructor for AbstractJpaDAO.
	 */
	public AbstractJpaDAO() {
		try {
			this.entityManager = JpaEntityManagerFactory.getEntityManagerFactory().createEntityManager();
		} catch (NamingException | SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets the Clazz.
	 * 
	 * @param entityClass
	 *            the class of the entity
	 */
	public final void setClazz(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * General method for finding one entity of a class by its id.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return the element
	 */
	public T findOne(final long id) {
		return this.entityManager.find(entityClass, id);
	}

	/**
	 * General method for finding one entity of a class by its id.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	public T findOne(final String id) {
		return this.entityManager.find(entityClass, id);
	}

	/**
	 * General method for finding all entities of a class.
	 * 
	 * @return the entitie's list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.entityClass.getName() + " e").getResultList();
	}

	/**
	 * General method to create an entity of a class.
	 * 
	 * @param entity
	 *            the entity to create
	 */
	public void create(final T entity) {
		this.entityManager.persist(entity);
	}

	/**
	 * General method to update an entity.
	 * 
	 * @param entity
	 *            the entity to update
	 * @return the updated entity
	 */
	public T update(final T entity) {
		return this.entityManager.merge(entity);
	}

	/**
	 * General method to delete an entity.
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	public void delete(final T entity) {
		this.entityManager.remove(entity);
	}

	/**
	 * General method to delete an entity by id.
	 * 
	 * @param entityId
	 *            the id of the entity to delete
	 */
	public void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	/**
	 * Gets the entity Manager.
	 * 
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entityManager for the DAO.
	 * 
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
