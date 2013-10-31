package com.cary.stq.dao.impl;

import com.cary.stq.dao.BaseDao;
import com.cary.stq.dao.ICustomerDao;
import com.cary.stq.to.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDao implements ICustomerDao {

    public void insert(Customer record) {
        getSqlMapClientTemplate().insert("customer.insert", record);
    }

    public int updateByPK(Customer record) {
        int rows = getSqlMapClientTemplate().update("customer.updateByPK", record);
        return rows;
    }

    public int updateByPKSelective(Customer record) {
        int rows = getSqlMapClientTemplate().update("customer.updateByPKSelective", record);
        return rows;
    }

    public List<Customer> search(Customer customer) {
        List<Customer> records = (List<Customer>) getSqlMapClientTemplate().queryForList("customer.search", customer);
        return records;
    }

    public Customer getCompanyByPK(HashMap paramMap) {
        Customer result = (Customer) getSqlMapClientTemplate().queryForObject("customer.getCompanyByPK", paramMap);
        return result;
    }

    public int deleteByPK(Integer companyId) {
        Customer key = new Customer();
        key.setCompanyId(companyId);
        int rows = getSqlMapClientTemplate().delete("customer.deleteByPK", key);
        return rows;
    }

}