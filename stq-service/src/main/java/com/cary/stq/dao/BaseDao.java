package com.cary.stq.dao;

import com.cary.stq.base.ibatis.BaseIBatisDao;
import com.cary.stq.base.ibatis.CountStatementUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import java.sql.SQLException;
import java.util.List;

public class BaseDao extends BaseIBatisDao {

    public List<?> queryForList(String statementName) {
        return getSqlMapClientTemplate().queryForList(statementName);
    }

    public List<?> queryForList(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
    }

    public List<?> queryForList(String statementName, Object parameterObject,
                                       int startRow, int endRow) {
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return getSqlMapClientTemplate().queryForList(statementName, parameterObject, startRow,
                endRow);
    }

    public Object queryForObject(String statementName, Object parameterObject) {
        return getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
    }

    public int update(String statementName, Object parameterObject){
        return getSqlMapClientTemplate().update(statementName, parameterObject);
    }

    public Object insert(String statementName, Object parameterObject){
        return getSqlMapClientTemplate().insert(statementName, parameterObject);
    }

    public int delete(String statementName, Object parameterObject){
        return getSqlMapClientTemplate().delete(statementName, parameterObject);
    }
    /**
     * 批量插入数据
     * @param statementName
     * @param list
     */
    public void batchInsert(final String statementName, final List<?> list) {
        try {
            if (list != null) {
                this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
                    public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                        executor.startBatch();
                        for (int i = 0, n = list.size(); i < n; i++) {
                            executor.insert(statementName, list.get(i));
                        }
                        executor.executeBatch();
                        return null;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (logger.isDebugEnabled()) {
                e.printStackTrace();
                logger.debug("batchInsert error: id [" + statementName + "], parameterObject ["+ list + "].  Cause: "+ e.getMessage());
            }

        }
    }

    public long getTotalCount(String statementId, Object parameterObject) {
        prepareCountQuery(statementId);
        return (Long) getSqlMapClientTemplate().queryForObject(
                CountStatementUtil.getCountStatementId(statementId),
                parameterObject);
    }
}
