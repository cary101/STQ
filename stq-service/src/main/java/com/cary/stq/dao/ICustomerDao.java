package com.cary.stq.dao;

import com.cary.stq.to.Customer;

import java.util.HashMap;
import java.util.List;

public interface ICustomerDao {

    void insert(Customer record);

    int updateByPK(Customer record);

    int updateByPKSelective(Customer record);

    List<Customer> search(Customer customer);

    Customer getCompanyByPK(HashMap paramMap);

    int deleteByPK(Integer companyId);

}