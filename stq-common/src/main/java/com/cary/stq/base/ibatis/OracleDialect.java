package com.cary.stq.base.ibatis;


public class OracleDialect implements Dialect {

	protected static final String SQL_END_DELIMITER = ";";
	private static final int LENGTH_NUM = 100;

	public String getLimitString(String sql, int startRow, int endRow) {

		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + LENGTH_NUM);
		sb.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		sb.append(sql);
		sb.append(" )A WHERE ROWNUM <=");
		sb.append(endRow);
		sb.append(")WHERE RN >=");
		if (startRow >= 0) {
			sb.append(startRow);
		}
		return sb.toString();
	}

	public boolean supportsLimit() {
		return true;
	}

	private String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0, sql.length() - 1
					- SQL_END_DELIMITER.length());
		}
		return sql;
	}

}
