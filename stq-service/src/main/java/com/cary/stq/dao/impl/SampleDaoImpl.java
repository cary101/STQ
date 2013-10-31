package com.cary.stq.dao.impl;

import com.cary.stq.dao.BaseDao;
import com.cary.stq.dao.ISampleDao;
import com.cary.stq.to.Sample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("sampleDao")
public class SampleDaoImpl extends BaseDao implements ISampleDao {

    public int insert(Sample sample) {
        return (Integer)getSqlMapClientTemplate().insert("sample.insert", sample);
    }

    public int updateByPK(Sample sample) {
        int rows = getSqlMapClientTemplate().update("sample.updateByPK", sample);
        return 0;
    }

//    public int updateByPKSelective(Customer record) {
//        int rows = getSqlMapClientTemplate().update("customer.updateByPKSelective", record);
//        return rows;
//    }

    public List<Sample> search(HashMap<String, String> paramMap) {
        List<Sample> records = (List<Sample>) getSqlMapClientTemplate().queryForList("sample.search", paramMap);
        return records;
    }

    public Sample getSampleByPK(HashMap<String, String> paramMap){
        Sample result = (Sample) getSqlMapClientTemplate().queryForObject("sample.getSampleByPK", paramMap);
        return result;
    }

//    public int deleteByPK(Integer companyId) {
//        Customer key = new Customer();
//        key.setCompanyId(companyId);
//        int rows = getSqlMapClientTemplate().delete("customer.deleteByPK", key);
//        return rows;
//    }

}