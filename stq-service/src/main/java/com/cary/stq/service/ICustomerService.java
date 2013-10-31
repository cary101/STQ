package com.cary.stq.service;

import com.cary.stq.to.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chen_haibing
 * Date: 12-9-26
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public interface ICustomerService {
    List<Customer> search(Customer customer);

    void insert(Customer customer);

    int update(Customer customer);

    int delete(Integer companyId);
}
