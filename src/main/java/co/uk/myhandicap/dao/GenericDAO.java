package main.java.co.uk.myhandicap.dao;

import java.io.Serializable;

/**
 * Generic DAO for common database operations
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/07/14
 * @project MyHandicapApp
 */
public interface GenericDAO <T, PK extends Serializable> {

    public void save(T saveObj);

    public void update(T updateObj);

    public void delete(T deleteObj);

}
