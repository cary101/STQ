package com.cary.stq.service.impl;

import com.cary.stq.dao.ILinkmanDao;
import com.cary.stq.service.ILinkmanService;
import com.cary.stq.to.Linkman;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("linkmanService")
public class LinkmanService implements ILinkmanService {

    @Resource
    private ILinkmanDao linkmanDao;

    @Override
    public List<Linkman> search(HashMap<String, String> paramMap){

        List<Linkman> linkmanList =  linkmanDao.search(paramMap);
        return  linkmanList;
    }

//    @Override
//    public void insert(Customer customer){
//        customerDao.insert(customer);
//    }
//
//    @Override
//    public int update(Customer customer){
//        return customerDao.updateByPK(customer);
//    }
//
//    @Override
//    public int delete(Integer companyId){
//        return customerDao.deleteByPK(companyId);
//    }
}
