package main.java.co.uk.myhandicap.service;

import java.io.Serializable;

/**
 * Generic Service Methods
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 11/07/14
 * @project MyHandicapApp
 */
public interface GenericService<T, Long extends Serializable> {

    void save(T saveObj);

    void update(T saveObj);

    void delete(T saveObj);

}
