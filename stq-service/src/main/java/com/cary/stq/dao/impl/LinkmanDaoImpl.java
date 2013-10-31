package com.cary.stq.dao.impl;

import com.cary.stq.dao.BaseDao;
import com.cary.stq.dao.ILinkmanDao;
import com.cary.stq.to.Linkman;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("linkmanDao")
public class LinkmanDaoImpl extends BaseDao implements ILinkmanDao {

//    public void insert(Customer record) {
//        getSqlMapClientTemplate().insert("customer.insert", record);
//    }
//
//    public int updateByPK(Customer record) {
//        int rows = getSqlMapClientTemplate().update("customer.updateByPK", record);
//        return rows;
//    }
//
//    public int updateByPKSelective(Customer record) {
//        int rows = getSqlMapClientTemplate().update("customer.updateByPKSelective", record);
//        return rows;
//    }

    public List<Linkman> search(HashMap<String, String> paramMap) {
        List<Linkman> records = (List<Linkman>) getSqlMapClientTemplate().queryForList("linkman.search", paramMap);
        return records;
    }

    public Linkman getLinkmanByPK(HashMap<String, String> paramMap){
        Linkman record = (Linkman) getSqlMapClientTemplate().queryForObject("linkman.getLinkmanByPK", paramMap);
        return record;
    }

//    public int deleteByPK(Integer companyId) {
//        Customer key = new Customer();
//        key.setCompanyId(companyId);
//        int rows = getSqlMapClientTemplate().delete("customer.deleteByPK", key);
//        return rows;
//    }

}