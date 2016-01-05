package org.jrue.poc.jdbc.service;

import java.util.List;

public interface Repository<T> {

	List<T> find(String key);
	boolean save(T record);
	boolean update(T record);
	boolean delete(T record);
	
}
