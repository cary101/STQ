package com.cary.stq.base.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BaseIBatisDao extends SqlMapClientDaoSupport {

    protected final Log logger = LogFactory.getLog(getClass());

    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    protected void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof LimitSqlExecutor) {
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof SqlMapClientImpl) {
                ReflectUtil.setFieldValue(((SqlMapClientImpl) sqlMapClient)
                        .getDelegate(), "sqlExecutor", SqlExecutor.class,
                        sqlExecutor);
            }
        }
    }

    protected void prepareCountQuery(String statementId) {
        String countStatementId = CountStatementUtil.getCountStatementId(statementId);
        if (logger.isDebugEnabled()) {
            logger.debug("Convert " + statementId + " to " + countStatementId);
        }
        SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
        if (sqlMapClient instanceof SqlMapClientImpl) {
            SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sqlMapClient).getDelegate();
            try {
                delegate.getMappedStatement(countStatementId);
            } catch (SqlMapException e) {
                delegate.addMappedStatement(CountStatementUtil
                        .createCountStatement(delegate
                                .getMappedStatement(statementId)));
            }

        }
    }
}