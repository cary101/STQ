package com.cary.stq.base.ibatis;


public class MySqlDialect implements Dialect {

    protected static final String SQL_END_DELIMITER = ";";
    private static final int LENGTH_NUM = 20;

    public String getLimitString(String sql, int page, int rows) {

        sql = trim(sql);
        StringBuffer sb = new StringBuffer(sql.length() + LENGTH_NUM);
        sb.append(sql);
        if (page >= 0 && rows > 0) {
            sb.append(" LIMIT ")
                    .append((page - 1) * rows)
                    .append(" , ")
                    .append(rows);
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
