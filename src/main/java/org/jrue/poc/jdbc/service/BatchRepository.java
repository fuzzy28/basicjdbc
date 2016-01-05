package org.jrue.poc.jdbc.service;

import java.util.List;

public interface BatchRepository<T> {

	boolean save(List<T> record);
	boolean update(List<T> record);
	
}
