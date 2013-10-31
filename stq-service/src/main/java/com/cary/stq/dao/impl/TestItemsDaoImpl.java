package com.cary.stq.dao.impl;

import com.cary.stq.dao.ITestItemsDao;
import com.cary.stq.to.Testitems;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testitemsDao")
public class TestItemsDaoImpl extends SqlMapClientDaoSupport implements ITestItemsDao {

    public TestItemsDaoImpl() {
        super();
    }

    public void insert(Testitems record) {
        getSqlMapClientTemplate().insert("testitems.insert", record);
    }

    public int updateByPK(Testitems record) {
        int rows = getSqlMapClientTemplate().update("testitems.updateByPK", record);
        return rows;
    }

    public int updateByPKSelective(Testitems record) {
        int rows = getSqlMapClientTemplate().update("testitems.updateByPKSelective", record);
        return rows;
    }

    public List<Testitems> search(Testitems user) {
        List<Testitems> records = (List<Testitems>) getSqlMapClientTemplate().queryForList("testitems.select", user);
        return records;
    }

    public List<Testitems> getItems() {
        List<Testitems> records = (List<Testitems>) getSqlMapClientTemplate().queryForList("testitems.getItems");
        return records;
    }

    public int deleteByPK(Integer itemId) {
        Testitems key = new Testitems();
        key.setTid(itemId);
        int rows = getSqlMapClientTemplate().delete("testitems.deleteByPK", key);
        return rows;
    }

}