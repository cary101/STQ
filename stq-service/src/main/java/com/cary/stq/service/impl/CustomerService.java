package com.cary.stq.service.impl;

import com.cary.stq.dao.ILinkmanDao;
import com.cary.stq.dao.ICustomerDao;
import com.cary.stq.service.ICustomerService;
import com.cary.stq.to.Customer;
import com.cary.stq.to.Linkman;
import com.cary.stq.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("customerService")
public class CustomerService implements ICustomerService {

    @Resource(name = "customerDao")
    private ICustomerDao customerDao;

    @Resource
    private ILinkmanDao linkmanDao;

    @Override
    public List<Customer> search(Customer customer){
        List<Customer> customers =  customerDao.search(customer);
        if(null != customers && customers.size() > 0){
            HashMap<String, String> paramMap = new HashMap<String, String>();
            for(Customer customerTemp : customers){
                if(!StringUtils.isEmpty(customerTemp.getLinkmanIds())){
                    paramMap.put("linkmanIds", customerTemp.getLinkmanIds());
                    List<Linkman> linkmans=  linkmanDao.search(paramMap);
                    customerTemp.setLinkmen(linkmans);
                }
            }
        }
        return  customers;
    }

    @Override
    public void insert(Customer customer){
        customerDao.insert(customer);
    }

    @Override
    public int update(Customer customer){
        return customerDao.updateByPK(customer);
    }

    @Override
    public int delete(Integer companyId){
        return customerDao.deleteByPK(companyId);
    }
}
