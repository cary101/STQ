package com.cary.stq.service;

import com.cary.stq.to.Customer;
import com.cary.stq.to.Linkman;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chen_haibing
 * Date: 12-9-26
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public interface ILinkmanService {
    List<Linkman> search(HashMap<String, String> paramMap);

//    void insert(Customer customer);
//
//    int update(Customer customer);
//
//    int delete(Integer companyId);
}
