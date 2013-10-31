package com.cary.stq.dao.impl;

import com.cary.stq.dao.BaseDao;
import com.cary.stq.dao.IApplicationDao;
import com.cary.stq.to.Application;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("applicationDao")
public class ApplicationDaoImpl extends BaseDao implements IApplicationDao {

    public void insert(Application record) {
        insert("application.insert", record);
    }

    public int updateSingle(Application record) {
        int rows = update("application.updateSingle", record);
        return rows;
    }

    public int updateByPKSelective(Application record) {
        int rows = update("application.updateByPKSelective", record);
        return rows;
    }

    public List<Application> search(Application application, int page, int limit) {
        List<Application> records = (List<Application>) queryForList("application.select", application, page, limit);
        return records;
    }

    public Long getTotalCount(Application application){
        return getTotalCount("application.select", application);
    }

    public List<Application> search(Application application) {
        List<Application> records = (List<Application>) queryForList("application.select", application);
        return records;
    }

    public Application getAppSingle(HashMap paramMap){
        return (Application) queryForObject("application.getAppSingle",paramMap);
    }

    public int deleteByPK(String applyId) {
        Application key = new Application();
        key.setApplyId(applyId);
        int rows = delete("application.deleteByPK", key);
        return rows;
    }
}