package com.cary.stq.base.ibatis;

/**
 * Dialect
 * 
 * @author 
 * @version 1.0
 */
public interface Dialect {

	boolean supportsLimit();

	String getLimitString(String sql, int startRow, int endRow);
}
