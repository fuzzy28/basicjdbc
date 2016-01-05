package org.jrue.poc.jdbc.constants;

public enum LogicalDelete {

	DELETED(1),
	NORMAL(0);
	
	private int flag;
	LogicalDelete(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}		
}
